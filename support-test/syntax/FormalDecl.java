/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  The type 't' does not have a location in the original source file because
  the primitive types and int[] type are all shared.

  The location of a formal declaration in the original source file could be
  taken to be that of the syntax.Identifier "i".  This class is a subclass
  of AST and has a linenumber and column number.

  syntax.Type is a subclass of AST and so contains a linenumber and a
  column number.
*/

public class FormalDecl {
   public final Type t;
   public final Identifier i;  // Has line number?  Yes
 
   public FormalDecl (final Type at, final Identifier ai) { t=at; i=ai;  }

   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}
