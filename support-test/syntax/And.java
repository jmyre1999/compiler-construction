/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class And extends Expression {
   public final Expression e1, e2;
  
   public And (final Expression ae1, final Expression ae2) { this(0,0,ae1, ae2); }
   public And (final int l, final int c, final Expression ae1, final Expression ae2) { super (l,c); this.e1=ae1; this.e2=ae2;  }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   @Override
      public String toString () { return String.format ("(%s && %s)", e1, e2); }
}
