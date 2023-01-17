/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class Times extends Expression {
   public final Expression e1,e2;

   public Times (Expression ae1, Expression ae2) { this(0,0,ae1,ae2); }
   public Times (int l, int c, Expression ae1, Expression ae2) { super (l,c); e1=ae1; e2=ae2; }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   @Override
   public String toString () {
      return String.format ("(%s*%s)", e1, e2);
   }
}
