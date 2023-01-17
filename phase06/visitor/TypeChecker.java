package visitor;

import syntax.*;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public final class TypeChecker implements SyntaxTreeVisitor <String>  {
   public HashMap<String, MiniJavaClass> symbolTable = null;
   public MiniJavaClass currentClass;
   public MiniJavaMethod currentMethod;
   public int currentLine = 0;
   public int currentCol = 0;
   public int num_errors = 0;
   public static String file_name = "";

   public static String bool_type = "boolean";
   public static String int_type = "int";
   public static String int_array_type = "int[]";
   public static String void_type = "void";

   public TypeChecker(String f, HashMap<String, MiniJavaClass> st) {
      file_name = f;
      symbolTable = st;
   }

   public Void reportError (int l, int c, String message) {
      System.err.println(file_name + ":" + l + "." + c + ": Semantic Error: " + message);
      num_errors++;
      return null;
   }

   public boolean compareTypes(String given_type, String expected_type) {
      if (given_type.equals(expected_type)) {
         return true;
      }
      MiniJavaClass given_class = null;
      List<String> visited_class_ids = new ArrayList<String>();
      if (symbolTable.containsKey(given_type)) {
         given_class = symbolTable.get(given_type);
         if (given_class.extend_id != null) {
            visited_class_ids.add(given_type);
            return compareTypes(given_class.extend_id, expected_type, visited_class_ids);
         }
      }
      return false;
   }

   public boolean compareTypes(String given_type, String expected_type, List<String> visited_class_ids) {
      if (visited_class_ids.contains(given_type)) {
         return false;
      }
      if (given_type.equals(expected_type)) {
         return true;
      }
      MiniJavaClass given_class = null;
      if (symbolTable.containsKey(given_type)) {
         given_class = symbolTable.get(given_type);
         if (given_class.extend_id != null) {
            visited_class_ids.add(given_type);
            return compareTypes(given_class.extend_id, expected_type, visited_class_ids);
         }
      }
      return false;
   }

   public MiniJavaMethod findMethod(MiniJavaClass mini_class, String id_string, String original_class_id) {
      if (mini_class.methodTable.containsKey(id_string)) {
         return mini_class.methodTable.get(id_string);
      }
      else {
         if (mini_class.extend_id != null) {
            if (symbolTable.containsKey(mini_class.extend_id) && !original_class_id.equals(mini_class.extend_id)) {
               List<String> visited_class_ids = new ArrayList<String>();
               visited_class_ids.add(original_class_id);
               return findMethod(symbolTable.get(mini_class.extend_id), id_string, visited_class_ids);
            }
         }
      }
      return null;
   }

   public MiniJavaMethod findMethod(MiniJavaClass mini_class, String id_string, List<String> visited_class_ids) {
      if (mini_class.methodTable.containsKey(id_string)) {
         return mini_class.methodTable.get(id_string);
      }
      else {
         if (mini_class.extend_id != null) {
            if (symbolTable.containsKey(mini_class.extend_id) && !visited_class_ids.contains(mini_class.extend_id)) {
               visited_class_ids.add(mini_class.id);
               return findMethod(symbolTable.get(mini_class.extend_id), id_string, visited_class_ids);
            }
         }
      }
      return null;
   }

   public String findFieldType(MiniJavaClass mini_class, String id_string, String original_class_id) {
      if (mini_class.fieldTable.containsKey(id_string)) {
         return mini_class.fieldTable.get(id_string);
      }
      else {
         if (mini_class.extend_id != null) {
            if (symbolTable.containsKey(mini_class.extend_id) && !original_class_id.equals(mini_class.extend_id)) {
               List<String> visited_class_ids = new ArrayList<String>();
               visited_class_ids.add(original_class_id);
               return findFieldType(symbolTable.get(mini_class.extend_id), id_string, visited_class_ids);
            }
         }
      }
      return null;
   }

   public String findFieldType(MiniJavaClass mini_class, String id_string, List<String> visited_class_ids) {
      if (mini_class.fieldTable.containsKey(id_string)) {
         return mini_class.fieldTable.get(id_string);
      }
      else {
         if (mini_class.extend_id != null) {
            if (symbolTable.containsKey(mini_class.extend_id) && !visited_class_ids.contains(mini_class.extend_id)) {
               visited_class_ids.add(mini_class.id);
               return findFieldType(symbolTable.get(mini_class.extend_id), id_string, visited_class_ids);
            }
         }
      }
      return null;
   }

   public Void inheritanceChecker(MiniJavaClass mini_class) {
      String id_string = mini_class.id;
      String extend_id_string = mini_class.extend_id;
      if (extend_id_string == null) {
         return null;
      }
      if (id_string.equals(extend_id_string)) {
         reportError(currentLine, currentCol, "Class " + id_string + " and parent class " + extend_id_string + " in circular class hierarchy");
      }
      else {
         if (symbolTable.containsKey(extend_id_string)) {
            List<String> visited_class_ids = new ArrayList<String>();
            visited_class_ids.add(id_string);
            return inheritanceChecker(symbolTable.get(extend_id_string), visited_class_ids);
         }
         else {
            reportError(currentLine, currentCol, "Symbol " + extend_id_string + " is not an extendable class");
         }
      }
      return null;
   }

   public Void inheritanceChecker(MiniJavaClass mini_class, List<String> visited_class_ids) {
      String id_string = mini_class.id;
      String extend_id_string = mini_class.extend_id;
      if (extend_id_string == null) {
         return null;
      }
      if (id_string.equals(extend_id_string)) {
         reportError(currentLine, currentCol, "Class " + id_string + " and parent class " + extend_id_string + " in circular class hierarchy");
      }
      else {
         if (symbolTable.containsKey(extend_id_string)) {
            if (visited_class_ids.contains(extend_id_string)) {
               reportError(currentLine, currentCol, "Circular class hierarchy involving " + extend_id_string);
               return null;
            }
            visited_class_ids.add(id_string);
            return inheritanceChecker(symbolTable.get(extend_id_string), visited_class_ids);
         }
         else {
            reportError(currentLine, currentCol, "Symbol " + extend_id_string + " is not an extendable class");
         }
      }
      return null;
   }

   // Subcomponents of Program:  MainClass m; List<ClassDecl> cl;
   public String visit (Program n) {
      if(n != null && symbolTable != null) {
         n.m.accept(this);
         for (ClassDecl c: n.cl) {
            c.accept(this);
         }
      }
      return void_type;
   }
  
   // Subcomponents of MainClass:  Identifier i1, i2; Statement s;
   public String visit (MainClass n) {
      String id_string = n.i1.s;

      currentClass = symbolTable.get(id_string);

      currentMethod = currentClass.methodTable.get("main");
      n.s.accept(this);

      currentMethod = null;
      currentClass = null;

      return void_type;
   }

   // Subcomponents of SimpleClassDecl: Identifier i; List<FieldDecl> vl; List<MethodDecl> ml;
   public String visit (final SimpleClassDecl n) {
      String id_string = n.i.s;

      currentClass = symbolTable.get(id_string);

      for (MethodDecl m: n.methods) {
         m.accept(this);
      }

      currentClass = null;

      return void_type;
   }
 
   // Subcomponents of ExtendingClassDecl: Identifier i, j; List<FieldDecl> fields; List<MethodDecl> methods;
   public String visit (final ExtendingClassDecl n) {
      String id_string = n.i.s;

      currentClass = symbolTable.get(id_string);

      currentLine = n.j.lineNumber;
      currentCol = n.j.columnNumber;
      inheritanceChecker(currentClass);

      for (MethodDecl m: n.methods) {
         m.accept(this);
      }

      currentClass = null;

      return void_type;
   }

   // Subcomponents of MethodDecl:
   // Type t; Identifier i; List<FormalDecl> fl; List<LocalDecl> locals; List<Statement>t sl; Expression e;
   public String visit (final MethodDecl n) {
      String id_string = n.i.s;
      String type_string = n.t.getName();

      currentMethod = currentClass.methodTable.get(id_string);

      for (Statement statement: n.sl) {
         statement.accept(this);
      }

      String return_type = n.e.accept(this);
      if (!return_type.equals(type_string)) {
         reportError(n.e.lineNumber, n.e.columnNumber, "Return type " + return_type + " does not match method type " + type_string);
      }

      currentMethod = null;

      return type_string;
   }

   // Type t; Identifier i
   public String visit (FieldDecl n) {
      return void_type;
   }
   public String visit (LocalDecl n) { 
      return void_type;
   }

   // Subcomponents of FormalDecl:  Type t; Identifier i;
   public String visit (FormalDecl n) {
      return void_type;
   }

   public String visit (IntArrayType n) {
      String type_string = int_array_type;
      return type_string;
   }

   public String visit (BooleanType n) {
      String type_string = bool_type;
      return type_string;
   }

   public String visit (IntegerType n) {
      String type_string = int_type;
      return type_string;
   }

   public String visit (VoidType n) {
      String type_string = void_type;
      return type_string;
   }

   // String s;
   public String visit (IdentifierType n) {
      String type_string = n.s;
      return type_string;
   }

   // Subcomponents of Block statement:  StatementList sl;
   public String visit (final Block n) {
      for (Statement s: n.sl) {
         s.accept(this);
      }
      return void_type;
   }

   // Subcomponents of If statement: Expression e; Statement s1,s2;
   public String visit (final If n) {
      String expression_type = n.e.accept(this);
      if (!expression_type.equals(bool_type)) {
         reportError(n.e.lineNumber, n.e.columnNumber, "If statement expression must be type boolean, found type " + expression_type);
      }
      n.s1.accept(this);
      n.s2.accept(this);
      return void_type;
   }

   // Subcomponents of While statement: Expression e, Statement s
   public String visit (final While n) {
      String expression_type = n.e.accept(this);
      if (!expression_type.equals(bool_type)) {
         reportError(n.e.lineNumber, n.e.columnNumber, "While statement expression must be type boolean, found type " + expression_type);
      }
      n.s.accept(this);
      return void_type;
   }

   // Subcomponents of Print statement:  Expression e;
   public String visit (final Print n) {
      String expression_type = n.e.accept(this);
      if (!expression_type.equals(int_type))  {
         reportError(n.e.lineNumber, n.e.columnNumber, "Print statement expression must be type int, found type " + expression_type);
      }
      return void_type;
   }
  
   // subcomponents of Assignment statement:  Identifier i; Expression e;
   public String visit (final Assign n) {
      String id_string = n.i.s;
      String id_type;
      if (currentMethod.paramTable.containsKey(id_string)) {
         id_type = currentMethod.paramTable.get(id_string);
      }
      else if (currentMethod.localTable.containsKey(id_string)) {
         id_type = currentMethod.localTable.get(id_string);
      }
      else if (currentClass.fieldTable.containsKey(id_string)) {
         id_type = currentClass.fieldTable.get(id_string);
      }
      else {
         id_type = findFieldType(currentClass, id_string, currentClass.id);
         if (id_type == null) {
            reportError(n.i.lineNumber, n.i.columnNumber, "Symbol " + id_string + " not found in this scope");
            n.e.accept(this);
            return void_type;
         }
      }
      /* TODO: Check parent types */
      String expression_type = n.e.accept(this);
      if (!compareTypes(expression_type, id_type)) {
         reportError(n.i.lineNumber, n.i.columnNumber, "Expected type " + id_type + ", found " + expression_type);
      }
      return void_type;
   }

   // Subcomponents of ArrayAssign:  Identifier nameOfArray; Expression indexInArray, Expression e;
   public String visit (ArrayAssign n) {
      String id_string = n.nameOfArray.s;
      String id_type;
      if (currentMethod.paramTable.containsKey(id_string)) {
         id_type = currentMethod.paramTable.get(id_string);
      }
      else if (currentMethod.localTable.containsKey(id_string)) {
         id_type = currentMethod.localTable.get(id_string);
      }
      else if (currentClass.fieldTable.containsKey(id_string)) {
         id_type = currentClass.fieldTable.get(id_string);
      }
      else {
         id_type = findFieldType(currentClass, id_string, currentClass.id);
         if (id_type == null) {
            reportError(n.nameOfArray.lineNumber, n.nameOfArray.columnNumber, "Symbol " + id_string + " not found in this scope");
         }
      }
      String expression_type;
      expression_type = n.indexInArray.accept(this);
      if (!expression_type.equals(int_type)) {
         reportError(n.indexInArray.lineNumber, n.indexInArray.columnNumber, "Array index expects type int, found " + expression_type);
      }
      expression_type = n.e.accept(this);
      if (!expression_type.equals(int_type)) {
         reportError(n.e.lineNumber, n.e.columnNumber, "Integer array expects type int, found " + expression_type);
      }
      return void_type;
   }

   // Expression e1,e2;
   public String visit (final And n) {
      String expression1_type = n.e1.accept(this);
      String expression2_type = n.e2.accept(this);
      if (!expression1_type.equals(bool_type)) {
         reportError(n.e1.lineNumber, n.e1.columnNumber, "And expression expects type boolean, found " + expression1_type);
      }
      if (!expression2_type.equals(bool_type)) {
         reportError(n.e2.lineNumber, n.e2.columnNumber, "And expression expects type boolean, found " + expression2_type);
      }
      return bool_type;
   }

   // Expression e1,e2;
   public String visit (final LessThan n) {
      String expression1_type = n.e1.accept(this);
      String expression2_type = n.e2.accept(this);
      if (!expression1_type.equals(int_type)) {
         reportError(n.e1.lineNumber, n.e1.columnNumber, "Less Than expression expects type int, found " + expression1_type);
      }
      if (!expression2_type.equals(int_type)) {
         reportError(n.e2.lineNumber, n.e2.columnNumber, "Less Than expression expects type int, found " + expression2_type);
      }
      return bool_type;
   }

   // Expression e1,e2;
   public String visit (final Plus n) {
      String expression1_type = n.e1.accept(this);
      String expression2_type = n.e2.accept(this);
      if (!expression1_type.equals(int_type)) {
         reportError(n.e1.lineNumber, n.e1.columnNumber, "Plus expression expects type int, found " + expression1_type);
      }
      if (!expression2_type.equals(int_type)) {
         reportError(n.e2.lineNumber, n.e2.columnNumber, "Plus expression expects type int, found " + expression2_type);
      }
      return int_type;
   }

   // Expression e1,e2;
   public String visit (final Minus n) {
      String expression1_type = n.e1.accept(this);
      String expression2_type = n.e2.accept(this);
      if (!expression1_type.equals(int_type)) {
         reportError(n.e1.lineNumber, n.e1.columnNumber, "Minus expression expects type int, found " + expression1_type);
      }
      if (!expression2_type.equals(int_type)) {
         reportError(n.e2.lineNumber, n.e2.columnNumber, "Minus expression expects type int, found " + expression2_type);
      }
      return int_type;
   }

   // Expression e1,e2;
   public String visit (final Times n) {
      String expression1_type = n.e1.accept(this);
      String expression2_type = n.e2.accept(this);
      if (!expression1_type.equals(int_type)) {
         reportError(n.e1.lineNumber, n.e1.columnNumber, "Times expression expects type int, found " + expression1_type);
      }
      if (!expression2_type.equals(int_type)) {
         reportError(n.e2.lineNumber, n.e2.columnNumber, "Times expression expects type int, found " + expression2_type);
      }
      return int_type;
   }

   // Expression expressionForArray, indexInArray;
   public String visit (final ArrayLookup n) {
      String expression_type = n.expressionForArray.accept(this);
      if (!expression_type.equals(int_array_type)) {
         reportError(n.expressionForArray.lineNumber, n.expressionForArray.columnNumber, "Expected type int[], found " + expression_type);
      }
      expression_type = n.indexInArray.accept(this);
      if (!expression_type.equals(int_type)) {
         reportError(n.expressionForArray.lineNumber, n.expressionForArray.columnNumber, "Expected type int, found " + expression_type);
      }
      return int_type;
   }

   // Expression expressionForArray;
   public String visit (final ArrayLength n) {
      String expression_type = n.expressionForArray.accept(this);
      if (!expression_type.equals(int_array_type)) {
         reportError(n.expressionForArray.lineNumber, n.expressionForArray.columnNumber, "Expected type int[], found " + expression_type);
      }
      return int_type;
   }


   // Subcomponents of Call:  Expression e (Class identifier); Identifier i (method id); ExpressionList el;
   public String visit (Call n) {
      String class_id = n.e.accept(this);

      if (!symbolTable.containsKey(class_id)) {
         reportError(n.e.lineNumber, n.e.columnNumber, "Object type " + class_id + " has no methods");
         return void_type;
      }
      
      MiniJavaClass call_class = symbolTable.get(class_id);
      n.setReceiverClassName(class_id);
      
      String method_id = n.i.s;
      MiniJavaMethod call_method = findMethod(call_class, method_id, class_id);
      if (call_method == null) {
         reportError(n.i.lineNumber, n.i.columnNumber, "Object of type " + class_id + " has no method " + method_id);
         return void_type;
      }
      
      Iterator<String> params = call_method.paramTable.keySet().iterator();
      int num_params = call_method.paramTable.keySet().size();
      String param;
      String param_type;
      String expression_type;

      for(Expression e: n.el) {
         expression_type = e.accept(this);
         if (params.hasNext()) {
            param = params.next();
            param_type = call_method.paramTable.get(param);
            if (!compareTypes(expression_type, param_type)) {
               reportError(e.lineNumber, e.columnNumber, "Method parameter expects type " + param_type + ", found " + expression_type);
            }
         }
         else {
            reportError(n.i.lineNumber, n.i.columnNumber, "Method " + method_id + " expects " + Integer.toString(num_params) + " parameters, found " + Integer.toString(n.el.size()));
            break;
         }

      }

      return call_method.type;
   }

   public String visit (True n) {
      return bool_type;
   }

   public String visit (False n) {
      return bool_type;
   }

   public String visit (IntegerLiteral n) {
      return int_type;
   }

   // Subcompoents of identifier statement: String:s
   public String visit (IdentifierExp n) {
      String id_string = n.s;
      String id_type;
      if (currentMethod.paramTable.containsKey(id_string)) {
         id_type = currentMethod.paramTable.get(id_string);
      }
      else if (currentMethod.localTable.containsKey(id_string)) {
         id_type = currentMethod.localTable.get(id_string);
      }
      else if (currentClass.fieldTable.containsKey(id_string)) {
         id_type = currentClass.fieldTable.get(id_string);
      }
      else {
         id_type = findFieldType(currentClass, id_string, currentClass.id);
         if (id_type == null) {
            reportError(n.lineNumber, n.columnNumber, "Symbol " + id_string + " not found in this scope");
            return void_type;
         }
      }
      return id_type;
   }

   public String visit (This n) {
      if (currentClass == null) {
         return void_type;
      }
      return currentClass.id;
   }

   // Expression e;
   public String visit (NewArray n) {
      String expression_type = n.e.accept(this);
      if (!expression_type.equals(int_type)) {
         reportError(n.e.lineNumber, n.e.columnNumber, "Expected type int, found " + expression_type);
      }
      return int_array_type;
   }

   // Identifier i;
   public String visit (NewObject n) {
      String id_string = n.i.s;
      if (symbolTable.containsKey(id_string)) {
         return id_string;
      }
      else {
         reportError(n.i.lineNumber, n.i.columnNumber, "Unknown symbol " + id_string);
      }
      return void_type;
   }

   // Expression e;
   public String visit (Not n) {
      String expression_type = n.e.accept(this);
      if (!expression_type.equals(bool_type)) {
         reportError(n.e.lineNumber, n.e.columnNumber, "Expected type boolean, found " + expression_type);
      }
      return bool_type;
   }

   // String s;
   public String visit (Identifier n) {
      String id_string = n.s;
      String id_type;
      if (currentMethod.paramTable.containsKey(id_string)) {
         id_type = currentMethod.paramTable.get(id_string);
      }
      else if (currentMethod.localTable.containsKey(id_string)) {
         id_type = currentMethod.localTable.get(id_string);
      }
      else if (currentClass.fieldTable.containsKey(id_string)) {
         id_type = currentClass.fieldTable.get(id_string);
      }
      else {
         id_type = findFieldType(currentClass, id_string, currentClass.id);
         if (id_type == null) {
            reportError(n.lineNumber, n.columnNumber, "Symbol " + id_string + " not found in this scope");
            return void_type;
         }
      }
      return id_type;
   }
}
