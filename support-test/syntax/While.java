/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public final class While extends Statement {
   public final Expression e;
   public final Statement s;

   public While (final Expression ae, final Statement as) { this (0,0,ae,as); }
   public While (final int l, final int c, final Expression ae, final Statement as) {
      super (l,c); e=ae; s=as;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}

