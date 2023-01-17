/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class Plus extends Expression {
   public final Expression e1, e2;
   public Plus (final int l, final int c, final Expression ae1, final Expression ae2) {
      super (l,c); e1=ae1; e2=ae2;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

}
