/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class Not extends Expression {

   public final Expression e;

   @Deprecated
   public Not (final Expression ae) { this (0,0,ae); }
  
   public Not (final int l, final int c, final Expression ae) { super(l,c); this.e=ae; }

   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}
