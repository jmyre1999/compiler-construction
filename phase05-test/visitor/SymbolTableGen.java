package visitor;

import syntax.*;
import java.util.HashMap;
import java.util.Set;

/*
symbolTable
{'id': id, 'fields': field_table, 'methods': methodTale}

fieldTable
{'id': id, 'type': type}

methodTable
{'id': id, 'type': type, 'params': paramTable, 'locals': localTable}

paramTable
{'id': id, 'type': type,}

localTable
{'id': id, 'type': type,}
*/

public final class SymbolTableGen implements SyntaxTreeVisitor <Void>  {
   public HashMap<String, MiniJavaClass> symbolTable = new HashMap<String, MiniJavaClass>();
   public MiniJavaClass currentClass;
   public MiniJavaMethod currentMethod;
   public int num_errors = 0;
   public static String file_name = "";

   public SymbolTableGen(String f) {
      file_name = f;
   }

   public Void printTable () {
      Set<String> class_ids = symbolTable.keySet();
      Set<String> method_ids;
      String method_type;
      Set<String> field_ids;
      String field_type;
      for (String id: class_ids) {
         System.out.println(id);
         currentClass = symbolTable.get(id);
         System.out.println(" FIELDS");
         field_ids = currentClass.fieldTable.keySet();
         for (String field_id: field_ids) {
            field_type = currentClass.fieldTable.get(field_id);
            System.out.println("    " + field_id + " " + field_type);
         }
         System.out.println(" METHODS");
         method_ids = currentClass.methodTable.keySet();
         for (String method_id: method_ids) {
            currentMethod = currentClass.methodTable.get(method_id);
            method_type = currentMethod.type;
            System.out.println("    " + method_id + " " + method_type);
            System.out.println("       PARAMS");
            field_ids = currentMethod.paramTable.keySet();
            for (String field_id: field_ids) {
               field_type = currentMethod.paramTable.get(field_id);
               System.out.println("          " + field_id + " " + field_type);
            }
            System.out.println("       LOCALS");
            field_ids = currentMethod.localTable.keySet();
            for (String field_id: field_ids) {
               field_type = currentMethod.localTable.get(field_id);
               System.out.println("          " + field_id + " " + field_type);
            }
         }
      }
      return null;
   }

   public Void reportError (int l, int c, String message) {
      System.err.println(file_name + ":" + l + "." + c + ": Semantic Error: " + message);
      num_errors++;
      return null;
   }

   // Subcomponents of Program:  MainClass m; List<ClassDecl> cl;
   public Void visit (Program n) {
      if(n != null) {
         n.m.accept(this);
         for (ClassDecl c: n.cl) {
            c.accept(this);
         }
      }
      return null;
   }
  
   // Subcomponents of MainClass:  Identifier i1, i2; Statement s;
   public Void visit (MainClass n) {
      String id_string = n.i1.s;

      MiniJavaClass new_class = new MiniJavaClass(id_string);
      currentClass = new_class;

      MiniJavaMethod new_method = new MiniJavaMethod("main", "void");
      currentClass.methodTable.put("main", new_method);
      new_method.paramTable.put(n.i2.s, "String[]");

      symbolTable.put(id_string, new_class);

      currentClass = null;

      return null;
   }

   // Subcomponents of SimpleClassDecl: Identifier i; List<FieldDecl> vl; List<MethodDecl> ml;
   public Void visit (final SimpleClassDecl n) {
      String id_string = n.i.s;

      if (symbolTable.containsKey(id_string)) {
         reportError(n.i.lineNumber, n.i.columnNumber, "Class already declared with ID " + id_string);
         return null;
      }

      MiniJavaClass new_class = new MiniJavaClass(id_string);
      currentClass = new_class;
      symbolTable.put(id_string, new_class);

      n.i.accept(this);
      for (FieldDecl v: n.fields) {
         v.accept(this);
      }
      for (MethodDecl m: n.methods) {
         m.accept(this);
      }

      currentClass = null;

      return null;
   }
 
   // Subcomponents of ExtendingClassDecl: Identifier i, j; List<FieldDecl> fields; List<MethodDecl> methods;
   public Void visit (final ExtendingClassDecl n) {
      String id_string = n.i.s;

      if (symbolTable.containsKey(id_string)) {
         /* Error: Same class defined twice */
         reportError(n.i.lineNumber, n.i.columnNumber, "Class already declared with ID " + id_string);
         return null;
      }

      MiniJavaClass new_class = new MiniJavaClass(id_string);
      new_class.extend_id = n.j.s;
      currentClass = new_class;
      symbolTable.put(id_string, new_class);

      for (FieldDecl v: n.fields) {
         v.accept(this);
      }
      for (MethodDecl m: n.methods) {
         m.accept(this);
      }

      currentClass = null;

      return null;
   }

   // Subcomponents of MethodDecl:
   // Type t; Identifier i; List<FormalDecl> fl; List<LocalDecl> locals; List<Statement>t sl; Expression e;
   public Void visit (final MethodDecl n) {
      String id_string = n.i.s;
      String type_string = n.t.getName();

      if (currentClass.methodTable.containsKey(id_string)) {
         /* Error: Method already exists in class */
         reportError(n.i.lineNumber, n.i.columnNumber, "Method already declared with ID " + id_string + " in class " + currentClass.id);
         return null;
      }

      MiniJavaMethod new_method = new MiniJavaMethod(id_string, type_string);
      currentClass.methodTable.put(id_string, new_method);
      currentMethod = new_method;

      for (FormalDecl f: n.fl) {
         f.accept(this);
      }
      for (LocalDecl local: n.locals) {
         local.accept(this);
      }

      currentMethod = null;

      return null;
   }

   // Type t; Identifier i
   public Void visit (FieldDecl n) {
      String id_string = n.i.s;
      String type_string = n.t.getName();

      if (currentClass.fieldTable.containsKey(id_string)) {
         /* Error: Field already exists in class */
         reportError(n.i.lineNumber, n.i.columnNumber, "Field already declared with ID " + id_string + " in class " + currentClass.id);
         return null;
      }

      currentClass.fieldTable.put(id_string, type_string);

      return null;
   }
   public Void visit (LocalDecl n) { 
      String id_string = n.i.s;
      String type_string = n.t.getName();

      if (currentMethod.localTable.containsKey(id_string)) {
         /* Error: Local var already exists in method */
         reportError(n.i.lineNumber, n.i.columnNumber, "Local variable already declared with ID " + id_string + " in method " + currentMethod.id);
         return null;
      }

      currentMethod.localTable.put(id_string, type_string);

      return null;
   }

   // Subcomponents of FormalDecl:  Type t; Identifier i;
   public Void visit (FormalDecl n) {
      String id_string = n.i.s;
      String type_string = n.t.getName();

      if (currentMethod.paramTable.containsKey(id_string)) {
         /* Error: Field already exists in method */
         reportError(n.i.lineNumber, n.i.columnNumber, "Parameter already declared with ID " + id_string + " in method " + currentMethod.id);
         return null;
      }

      currentMethod.paramTable.put(id_string, type_string);

      return null;
   }

   public Void visit (IntArrayType n) {
      return null;
   }

   public Void visit (BooleanType n) {
      return null;
   }

   public Void visit (IntegerType n) {
      return null;
   }

   public Void visit (VoidType n) {
      return null;
   }

   // String s;
   public Void visit (IdentifierType n) {
      return null;
   }

   // Subcomponents of Block statement:  StatementList sl;
   public Void visit (final Block n) {
      return null;
   }

   // Subcomponents of If statement: Expression e; Statement s1,s2;
   public Void visit (final If n) {
      return null;
   }

   // Subcomponents of While statement: Expression e, Statement s
   public Void visit (final While n) {
      return null;
   }

   // Subcomponents of Print statement:  Expression e;
   public Void visit (final Print n) {
      return null;
   }
  
   // subcomponents of Assignment statement:  Identifier i; Expression e;
   public Void visit (final Assign n) {
      return null;
   }

   // Subcomponents of ArrayAssign:  Identifier nameOfArray; Expression indexInArray, Expression e;
   public Void visit (ArrayAssign n) {
      return null;
   }

   // Expression e1,e2;
   public Void visit (final And n) {
      return null;
   }

   // Expression e1,e2;
   public Void visit (final LessThan n) {
      return null;
   }

   // Expression e1,e2;
   public Void visit (final Plus n) {
      return null;
   }

   // Expression e1,e2;
   public Void visit (final Minus n) {
      return null;
   }

   // Expression e1,e2;
   public Void visit (final Times n) {
      return null;
   }

   // Expression expressionForArray, indexInArray;
   public Void visit (final ArrayLookup n) {
      return null;
   }

   // Expression expressionForArray;
   public Void visit (final ArrayLength n) {
      return null;
   }


   // Subcomponents of Call:  Expression e; Identifier i; ExpressionList el;
   public Void visit (Call n) {
      return null;
   }

   public Void visit (True n) {
      return null;
   }

   public Void visit (False n) {
      return null;
   }

   public Void visit (IntegerLiteral n) {
      return null;
   }

   // Subcompoents of identifier statement: String:s
   public Void visit (IdentifierExp n) {
      return null;
   }

   public Void visit (This n) {
      return null;
   }

   // Expression e;
   public Void visit (NewArray n) {
      n.e.accept (this);
      return null;
   }

   // Identifier i;
   public Void visit (NewObject n) {
      return null;
   }

   // Expression e;
   public Void visit (Not n) {
      return null;
   }

   // String s;
   public Void visit (Identifier n) {
      return null;
   }
}
