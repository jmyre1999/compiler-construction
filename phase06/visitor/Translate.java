package visitor;

import tree.*;
import syntax.*;
import sparc.*;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


public final class Translate implements SyntaxTreeVisitor <LazyIRTree>  {
   /* TODO: Frag list */
   public HashMap<String, MiniJavaClass> symbolTable = null;
   public MiniJavaClass currentClass;
   public MiniJavaMethod currentMethod;
   public Frame currentFrame;
   public List<Fragment> fragments = new ArrayList<Fragment>();

   public Translate(HashMap<String, MiniJavaClass> st) {
      symbolTable = st;
   }

   // Subcomponents of Program:  MainClass m; List<ClassDecl> cl;
   public LazyIRTree visit (Program n) {
      // System.out.println(n.getClass().getName());
      if(n != null && symbolTable != null) {
         n.m.accept(this);
         for (ClassDecl c: n.cl) {
            c.accept(this);
         }
      }
      return null;
   }
  
   // Subcomponents of MainClass:  Identifier i1, i2; Statement s;
   public LazyIRTree visit (MainClass n) {
      // System.out.println(n.getClass().getName());
      String id_string = n.i1.s;

      currentClass = symbolTable.get(id_string);

      currentMethod = currentClass.methodTable.get("main");
      currentFrame = new Frame(currentMethod, currentClass, symbolTable);

      Stm body = null;
      LABEL prelude_end = new LABEL(new NameOfLabel(id_string,"main","preludeEnd"));
      JUMP epilog_begin = new JUMP(new NameOfLabel(id_string,"main","epilogBegin"));
      Stm s = n.s.accept(this).asStm();

      List <Stm> stm_list = new ArrayList<Stm>();
      stm_list.add(prelude_end);
      stm_list.add(s);
      stm_list.add(epilog_begin);
      body = SEQ.fromList(stm_list.toArray(new Stm[0]));

      fragments.add(new Fragment(body, currentFrame));

      currentFrame = null;
      currentMethod = null;
      currentClass = null;
      return null;
   }

   // Subcomponents of SimpleClassDecl: Identifier i; List<FieldDecl> vl; List<MethodDecl> ml;
   public LazyIRTree visit (final SimpleClassDecl n) {
      // System.out.println(n.getClass().getName());
      String id_string = n.i.s;

      currentClass = symbolTable.get(id_string);

      for (MethodDecl m: n.methods) {
         m.accept(this);
      }

      currentClass = null;

      return null;
   }
 
   // Subcomponents of ExtendingClassDecl: Identifier i, j; List<FieldDecl> fields; List<MethodDecl> methods;
   public LazyIRTree visit (final ExtendingClassDecl n) {
      // System.out.println(n.getClass().getName());
      String id_string = n.i.s;

      currentClass = symbolTable.get(id_string);

      for (MethodDecl m: n.methods) {
         m.accept(this);
      }

      currentClass = null;

      return null;
   }

   // Subcomponents of MethodDecl:
   // Type t; Identifier i; List<FormalDecl> fl; List<LocalDecl> locals; List<Statement>t sl; Expression e;
   public LazyIRTree visit (final MethodDecl n) {
      // System.out.println(n.getClass().getName());
      String id_string = n.i.s;

      currentMethod = currentClass.methodTable.get(id_string);
      currentFrame = new Frame(currentMethod, currentClass, symbolTable);

      int num_statements = n.sl.size();
      Stm body = null;
      List <Stm> stm_list = new ArrayList<Stm>();

      LABEL prelude_end = new LABEL(new NameOfLabel(currentClass.id,id_string,"preludeEnd"));
      JUMP epilog_begin = new JUMP(new NameOfLabel(currentClass.id,id_string,"epilogBegin"));

      stm_list.add(prelude_end);
      for (Statement s: n.sl) {
         // System.out.println("staement loop");
         stm_list.add(s.accept(this).asStm());
         // System.out.println("loop next");
      }
      // System.out.println("end loop");
      Exp return_exp = n.e.accept(this).asExp();
      stm_list.add(new MOVE(currentFrame.thisPointer, return_exp));
      stm_list.add(epilog_begin);

      body = SEQ.fromList(stm_list.toArray(new Stm[0]));

      fragments.add(new Fragment(body, currentFrame));

      currentFrame = null;
      currentMethod = null;

      return (new StmIRTree(body));
   }

   //////////// NOT NEEDED ////////////

   // Type t; Identifier i
   public LazyIRTree visit (FieldDecl n) {
      // System.out.println(n.getClass().getName());
      return null;
   }
   public LazyIRTree visit (LocalDecl n) {
      // System.out.println(n.getClass().getName());
      return null;
   }

   // Subcomponents of FormalDecl:  Type t; Identifier i;
   public LazyIRTree visit (FormalDecl n) {
      // System.out.println(n.getClass().getName());
      return null;
   }

   public LazyIRTree visit (IntArrayType n) {
      // System.out.println(n.getClass().getName());
      return null;
   }

   public LazyIRTree visit (BooleanType n) {
      // System.out.println(n.getClass().getName());
      return null;
   }

   public LazyIRTree visit (IntegerType n) {
      // System.out.println(n.getClass().getName());
      return null;
   }

   public LazyIRTree visit (VoidType n) {
      // System.out.println(n.getClass().getName());
      return null;
   }

   // String s;
   public LazyIRTree visit (IdentifierType n) {
      // System.out.println(n.getClass().getName());
      return null;
   }

   ////////////////////////////////////

   // Subcomponents of Block statement:  StatementList sl;
   public LazyIRTree visit (final Block n) {
      // System.out.println(n.getClass().getName());
      int num_statements = n.sl.size();
      Stm statement = null;
      List <Stm> stm_list = new ArrayList<Stm>();

      if (num_statements == 0) {
         statement = new EVAL(new CONST(0));
      }
      else if (num_statements == 1) {
         statement = n.sl.get(0).accept(this).asStm();
      }
      else {
         for (Statement s: n.sl) {
            stm_list.add(s.accept(this).asStm());
         }
         statement = SEQ.fromList(stm_list.toArray(new Stm[0]));
      }
      return (new StmIRTree(statement));
   }

   // Subcomponents of If statement: Expression e; Statement s1,s2;
   public LazyIRTree visit (final If n) {
      // System.out.println(n.getClass().getName());
      LazyIRTree c = n.e.accept(this);
      LazyIRTree thenClause = n.s1.accept(this);
      LazyIRTree elseClause = n.s2.accept(this);
      return (new IfThenElseExp(c, thenClause, elseClause));
   }

   // Subcomponents of While statement: Expression e, Statement s
   public LazyIRTree visit (final While n) {
      // System.out.println(n.getClass().getName());
      LazyIRTree c = n.e.accept(this);
      LazyIRTree doClause = n.s.accept(this);
      return (new WhileExp(c, doClause));
   }

   // Subcomponents of Print statement:  Expression e;
   public LazyIRTree visit (final Print n) {
      // System.out.println(n.getClass().getName());
      NAME name = new NAME("print_int");
      Exp e = n.e.accept(this).asExp();
      return (new ExpIRTree(new CALL(name, e)));
   }
  
   // subcomponents of Assignment statement:  Identifier i; Expression e;
   public LazyIRTree visit (final Assign n) {
      // System.out.println(n.getClass().getName());
      Exp d = currentFrame.findID(n.i.s);
      Exp s = n.e.accept(this).asExp();
      // System.out.println("creating move");
      return (new StmIRTree(new MOVE(d, s)));
   }

   // Subcomponents of ArrayAssign:  Identifier nameOfArray; Expression indexInArray, Expression e;
   public LazyIRTree visit (ArrayAssign n) {
      // System.out.println(n.getClass().getName());
      Exp array_location = currentFrame.findID(n.nameOfArray.s);
      Exp indexInArray = n.indexInArray.accept(this).asExp();
      Exp s = n.e.accept(this).asExp();
      return (new StmIRTree(
               new MOVE(
                  new BINOP(BINOP.PLUS, 
                            array_location, 
                            new BINOP(BINOP.MUL, indexInArray, new CONST(4))), 
                  s
               )
            )
         );
   }

   // Expression e1,e2;
   public LazyIRTree visit (final And n) {
      // System.out.println(n.getClass().getName());
      Exp e1 = n.e1.accept(this).asExp();
      Exp e2 = n.e2.accept(this).asExp();
      return (new CondIRTree(e1, e2, CJUMP.EQ));
   }

   // Expression e1,e2;
   public LazyIRTree visit (final LessThan n) {
      // System.out.println(n.getClass().getName());
      Exp e1 = n.e1.accept(this).asExp();
      Exp e2 = n.e2.accept(this).asExp();
      return (new CondIRTree(e1, e2, CJUMP.LT));
   }

   // Expression e1,e2;
   public LazyIRTree visit (final Plus n) {
      // System.out.println(n.getClass().getName());
      Exp le = n.e1.accept(this).asExp(); 
      Exp re = n.e2.accept(this).asExp();

      return (new ExpIRTree(new BINOP(BINOP.PLUS, le, re)));
   }

   // Expression e1,e2;
   public LazyIRTree visit (final Minus n) {
      // System.out.println(n.getClass().getName());
      Exp le = n.e1.accept(this).asExp(); 
      Exp re = n.e2.accept(this).asExp();

      return (new ExpIRTree(new BINOP(BINOP.MINUS, le, re)));
   }

   // Expression e1,e2;
   public LazyIRTree visit (final Times n) {
      // System.out.println(n.getClass().getName());
      Exp le = n.e1.accept(this).asExp(); 
      Exp re = n.e2.accept(this).asExp();

      return (new ExpIRTree(new BINOP(BINOP.MUL, le, re)));
   }

   // Expression expressionForArray, indexInArray;
   public LazyIRTree visit (final ArrayLookup n) {
      // System.out.println(n.getClass().getName());
      Exp e = n.expressionForArray.accept(this).asExp();
      Exp i = n.indexInArray.accept(this).asExp();
      return (
         new ExpIRTree(
            new MEM(
                  new BINOP(BINOP.PLUS, new MEM(e), 
                     new BINOP(BINOP.MUL, new BINOP(BINOP.PLUS, i, CONST.ONE), new CONST(4)
                     )
                  )
            )
         )
      );
   }

   // Expression expressionForArray;
   public LazyIRTree visit (final ArrayLength n) {
      // System.out.println(n.getClass().getName());
      Exp e = n.expressionForArray.accept(this).asExp();
      return (new ExpIRTree(new MEM(e)));
   }


   // Subcomponents of Call:  Expression e; Identifier i; ExpressionList el;
   public LazyIRTree visit (Call n) {
      // System.out.println(n.getClass().getName());
      String class_id = n.getReceiverClassName();
      String method_id = n.i.s;
      NAME new_name = new NAME(class_id + "$" + method_id);
      List<Exp> parameters = new ArrayList<Exp>();
      parameters.add(n.e.accept(this).asExp());
      for (Expression e:n.el) {
         parameters.add(e.accept(this).asExp());
      }
      return (new BooleanIRTree(new CALL(new_name, parameters)));
   }

   public LazyIRTree visit (True n) {
      // System.out.println(n.getClass().getName());
      return (new BooleanIRTree(CONST.TRUE));
   }

   public LazyIRTree visit (False n) {
      // System.out.println(n.getClass().getName());
      return (new BooleanIRTree(CONST.FALSE));
   }

   public LazyIRTree visit (IntegerLiteral n) {
      // System.out.println(n.getClass().getName());
      return (new ExpIRTree(new CONST(n.i)));
   }

   // Subcompoents of identifier statement: String:s
   public LazyIRTree visit (IdentifierExp n) {
      // System.out.println(n.getClass().getName());
      // System.out.println(n.s);
      if (currentFrame == null) {
         // System.out.println("frame is null woah thats CRAZY man");
      }
      return (new BooleanIRTree(currentFrame.findID(n.s)));
   }

   public LazyIRTree visit (This n) {
      // System.out.println(n.getClass().getName());
      return (new ExpIRTree(currentFrame.thisPointer));
   }

   // Expression e;
   public LazyIRTree visit (NewArray n) {
      // System.out.println(n.getClass().getName());
      NAME name = new NAME("malloc");
      Exp e = n.e.accept(this).asExp();
      return (new ExpIRTree(new CALL(name, new BINOP(BINOP.MUL, new BINOP(BINOP.PLUS, e, new CONST(1)), new CONST(4)))));
   }

   // Identifier i;
   public LazyIRTree visit (NewObject n) {
      // System.out.println(n.getClass().getName());
      NAME name = new NAME("malloc");
      MiniJavaClass obj_class = symbolTable.get(n.i.s);
      return (new ExpIRTree(new CALL(name, new CONST(4*obj_class.getNumFields(symbolTable)))));
   }

   // Expression e;
   public LazyIRTree visit (Not n) {
      // System.out.println(n.getClass().getName());
      Exp e = n.e.accept(this).asExp();
      return (new CondIRTree(e, CONST.FALSE, CJUMP.EQ));
   }

   // String s;
   public LazyIRTree visit (Identifier n) {
      // System.out.println(n.getClass().getName());
      return (new ExpIRTree(currentFrame.findID(n.s)));
   }
}
