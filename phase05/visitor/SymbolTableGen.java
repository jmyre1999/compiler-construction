package syntax;

import java.io.PrintWriter;

public final class PrettyPrint implements SyntaxTreeVisitor <Void>  {
   private final PrintWriter pw;
   public PrettyPrint (PrintWriter pw) {
      this.pw = pw;
   }
   public PrettyPrint () { this (new PrintWriter(System.out));}

   private int tab = 0;
   private void tab () {
      for (int i=0; i<tab; i++) pw.print ("   ");
   }

   private void print   (int i)    { pw.print (i); }
   private void print   (String s) { pw.print (s); }
   private void println (String s) { pw.println (s); }
   private void println ()         { pw.println ();}


   // Subcomponents of Program:  MainClass m; List<ClassDecl> cl;
   public Void visit (Program n) {
      tab = 0;
      if (n==null) {
         print ("// null Program!!");
      } else if (n.m==null) {
         print ("// null Main class!!");
      } else {
         n.m.accept (this);
         for (ClassDecl c: n.cl) c.accept (this);
         assert tab==0;
      }
      pw.flush();
      return null;
   }
  
   // Subcomponents of MainClass:  Identifier i1, i2; Statement s;
   public Void visit (MainClass n) {
      tab ();
      print ("class ");
      n.i1.accept (this);  // identifier:  name of class
      println (" {");  tab++;
      tab();
      print ("public static void main (String [] ");
      n.i2.accept (this);  // identifier:  name of arguments
      print (")");

      println (" {"); tab++;
      n.s.accept (this);   // statement:  body of main 
      tab--; tab(); println ("}");
      tab--; tab(); println ("}");
      return null;
   }

   // Subcomponents of SimpleClassDecl: Identifier i; List<FieldDecl> vl; List<MethodDecl> ml;
   public Void visit (final SimpleClassDecl n) {
      tab ();
      print ("class ");
      n.i.accept (this);
      println (" {");  tab++;
      for (FieldDecl v: n.fields) v.accept (this);
      for (MethodDecl m: n.methods) m.accept (this);
      tab--; tab (); println ("}");
      // Does end with a newline
      return null;
   }
 
   // Subcomponents of ExtendingClassDecl: Identifier i, j; List<FieldDecl> vl; List<MethodDecl> ml;
   public Void visit (final ExtendingClassDecl n) {
      tab ();
      print ("class ");
      n.i.accept (this);
      print (" extends ");
      n.j.accept (this);
      println (" {");  tab++;
      for (final FieldDecl v: n.fields) v.accept (this);
      for (final MethodDecl m: n.methods) m.accept (this);
      tab--; tab (); println ("}");
      // Does end with a newline
      return null;
   }

   // Subcomponents of MethodDecl:
   // Type t; Identifier i; List<FormalDecl> fl; List<LocalDecl> locals; List<Statement>t sl; Expression e;
   public Void visit (final MethodDecl n) {
      tab ();
      print ("public ");
      print (n.t, n.i);
      print (" (");

      if (n.fl.size()>0) {
         n.fl.get (0).accept (this);
         // Loop over all actuals excluding the first one
         for (final FormalDecl f: n.fl.subList(1, n.fl.size())) {
            print (", ");
            f.accept (this);
         }
      }
      print (")");
      println (" {"); tab++;
      for (final LocalDecl v: n.locals) v.accept (this);
      for (final Statement s: n.sl) s.accept(this);

      // Return statement
      tab ();
      print ("return ");
      n.e.accept (this);      // Expression e: no new line
      println (";");
      tab--; tab(); println ("}");

      // Does end with a newline
      return null;
   }

   public Void visit (FieldDecl n) { printT (n.t, n.i); return null; }
   public Void visit (LocalDecl n) { printT (n.t, n.i); return null; }

   private void printT (final Type t, final Identifier i) {
      tab ();           // Indent the prevailing amount
      print (t, i);
      println (";");    // Colon terminator
   }

   private void print (final Type t, final Identifier i) {
      print (t.toString());  // We do not call accept
      print (" ");
      print (i.toString());  // We do not call accept
   }

   // Subcomponents of FormalDecl:  Type t; Identifier i;
   public Void visit (FormalDecl n) {
      print (n.t, n.i);
      // Does not end with a newline
      return null;
   }

   public Void visit (IntArrayType n) {
      print ("int[]");
      // Does not end with a newline
      return null;
   }

   public Void visit (BooleanType n) {
      print ("boolean");
      return null;
   }

   public Void visit (IntegerType n) {
      print ("int");
      return null;
   }

   public Void visit (VoidType n) {
      print ("void");
      return null;
   }

   // String s;
   public Void visit (IdentifierType n) {
      print (n.s);
      return null;
   }

   // Subcomponents of Block statement:  StatementList sl;
   public Void visit (final Block n) {
      tab(); println ("{"); tab++;
      for (Statement s: n.sl) s.accept (this);
      tab--; tab(); println ("}");
      return null;
   }

   // Subcomponents of If statement: Expression e; Statement s1,s2;
   public Void visit (final If n) {
      tab();
      print ("if (");
      n.e.accept (this);
      print (")");
      printT (n.s1);  // "then" block
      printC (n.s2);  // "else" block (not optional in MiniJava)
      return null;
   }

   private void printT (final Statement s) {
      if (s instanceof Block) {
         println (" {"); tab++;
         for (Statement ss: ((Block)s).sl) ss.accept (this);
         tab--; tab(); print ("} else");
      } else {
         println (); tab++;
         s.accept (this);
         tab--; tab(); print ("else");
      }
   }


   private void printC (final Statement s) {
      if (s instanceof Block) {
         println (" {"); tab++;
         for (Statement ss: ((Block)s).sl) ss.accept (this);
         tab--; tab(); println ("}");
      } else {
         println (); tab++;
         s.accept (this);
         tab--;
      }
   }

   // Subcomponents of While statement: Expression e, Statement s
   public Void visit (final While n) {
      tab ();
      print ("while (");
      n.e.accept (this);
      print (")");
      printC (n.s);
      return null;
   }

   // Subcomponents of Print statement:  Expression e;
   public Void visit (final Print n) {
      tab ();
      print ("System.out.println (");
      n.e.accept (this);
      println (");");
      return null;
   }
  
   // subcomponents of Assignment statement:  Identifier i; Expression e;
   public Void visit (final Assign n) {
      tab ();
      n.i.accept (this);
      print (" = ");
      n.e.accept (this);
      println (";");
      return null;
   }

   // Subcomponents of ArrayAssign:  Identifier nameOfArray; Expression indexInArray, Expression e;
   public Void visit (ArrayAssign n) {
      tab ();
      n.nameOfArray.accept(this);
      print ("[");
      n.indexInArray.accept(this);
      print ("] = ");
      n.e.accept(this);
      println (";");
      return null;
   }

   // Expression e1,e2;
   public Void visit (final And n) {
      print("(");
      n.e1.accept(this);
      print(" && ");
      n.e2.accept(this);
      print(")");
      return null;
   }

   // Expression e1,e2;
   public Void visit (final LessThan n) {
      print("(");
      n.e1.accept(this);
      print(" < ");
      n.e2.accept(this);
      print(")");
      return null;
   }

   // Expression e1,e2;
   public Void visit (final Plus n) {
      print("(");
      n.e1.accept(this);
      print(" + ");
      n.e2.accept(this);
      print(")");
      return null;
   }

   // Expression e1,e2;
   public Void visit (final Minus n) {
      print("(");
      n.e1.accept(this);
      print(" - ");
      n.e2.accept(this);
      print(")");
      return null;
   }

   // Expression e1,e2;
   public Void visit (final Times n) {
      print("(");
      n.e1.accept(this);
      print(" * ");
      n.e2.accept(this);
      print(")");
      return null;
   }

   // Expression expressionForArray, indexInArray;
   public Void visit (final ArrayLookup n) {
      n.expressionForArray.accept(this);
      print("[");
      n.indexInArray.accept(this);
      print("]");
      return null;
   }

   // Expression expressionForArray;
   public Void visit (final ArrayLength n) {
      n.expressionForArray.accept(this);
      print(".length");
      return null;
   }


   // Subcomponents of Call:  Expression e; Identifier i; ExpressionList el;
   public Void visit (Call n) {
      n.e.accept (this);
      print (".");
      n.i.accept (this);
      print ("(");

      if (n.el.size()>0) {
         n.el.get (0).accept (this);

         // Loop over all actuals excluding the first one
         for (Expression e: n.el.subList(1, n.el.size())) {
            print (", ");
            e.accept (this);
         }
      }
      print (")");
      return null;
   }

   public Void visit (True n) {
      print ("true");
      return null;
   }

   public Void visit (False n) {
      print ("false");
      return null;
   }

   public Void visit (IntegerLiteral n) {
      print (n.i);
      return null;
   }

   // Subcompoents of identifier statement: String:s
   public Void visit (IdentifierExp n) {
      print(n.s);
      return null;
   }

   public Void visit (This n) {
      print ("this");
      return null;
   }

   // Expression e;
   public Void visit (NewArray n) {
      print ("new int [");
      n.e.accept (this);
      print ("]");
      return null;
   }

   // Identifier i;
   public Void visit (NewObject n) {
      print ("new ");
      print (n.i.s);
      print ("()");
      return null;
   }

   // Expression e;
   public Void visit (Not n) {
      print ("!");
      n.e.accept (this);
      return null;
   }

   // String s;
   public Void visit (Identifier n) {
      print (n.s);
      return null;
   }
}
