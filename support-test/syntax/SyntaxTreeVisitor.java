/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public interface SyntaxTreeVisitor <T> {
   T visit (Program n);
   T visit (MainClass n);
   T visit (SimpleClassDecl n);
   T visit (ExtendingClassDecl n);
   T visit (MethodDecl n);
   T visit (LocalDecl n);
   T visit (FieldDecl n);
   T visit (FormalDecl n);
   T visit (IdentifierType n);
   // Visiting the 'int[]' type seems pointless, but for objects (IdentifierType)
   T visit (IntArrayType n);
   // Visiting the primitive types Bool, Int, Void seems pointless
   T visit (BooleanType n);
   T visit (IntegerType n);
   T visit (VoidType n);
   T visit (Block n);
   T visit (If n);
   T visit (While n);
   T visit (Print n);
   T visit (Assign n);
   T visit (ArrayAssign n);
   T visit (And n);
   T visit (LessThan n);
   T visit (Plus n);
   T visit (Minus n);
   T visit (Times n);
   T visit (ArrayLookup n);
   T visit (ArrayLength n);
   T visit (Call n);
   T visit (IntegerLiteral n);
   T visit (True n);
   T visit (False n);
   T visit (IdentifierExp n);  // Identifier as an expression
   T visit (This n);
   T visit (NewArray n);
   T visit (NewObject n);
   T visit (Not n);
   // An identifier as a local or formal is just a string
   T visit (Identifier n);
}
