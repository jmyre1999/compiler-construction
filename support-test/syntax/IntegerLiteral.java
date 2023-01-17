/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

// There is no good way to pass a too-large literal from the syntax phase of the compiler
// to the semantic checking phase where the error checking might well be better.
// Why not a string?  Because we want IntegerLiteral to be immutable??

public class IntegerLiteral extends Expression {
   public final int i;

   @Deprecated
   public IntegerLiteral (final int l, final int c, final String ai) {
      this (l, c, 0);
   }
   
   public IntegerLiteral (final int l, final int c, int ai) {
      super (l,c); i=ai;
      assert 0<=i;  // No negative literals in MiniJava
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   @Override
   public String toString () {
      return String.format ("%d", i);
   }

}
