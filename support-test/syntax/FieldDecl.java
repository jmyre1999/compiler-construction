/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  VarDecl no longer is used for the declarations of fields in a class, and local
  variable declaration.  It would be very nice to know which!  It is not
  used in formal declarations; these can be found in Formal.

  Should FormalDecl, LocalDecl, FieldDecl are subclasses of abstract IdentifierDecl.
*/

/*
  The location of a variable declaration in the original source file can be
  taken to be that of the syntax.Identifier "i".  This subclass of AST has
  a linenumber and a column number.

  Why not make this a subclass of AST?  Too many line numbers?!
*/

public class FieldDecl extends IdentifierDecl {
   public FieldDecl (Type at, Identifier ai) { super (at, ai); }

   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

}
