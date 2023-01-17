/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class Print extends Statement {
   public final Expression e;

   public Print (Expression ae) { this(0,0,ae); }
   public Print (int l, int c, Expression ae) {
      super (l,c); e=ae;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}
