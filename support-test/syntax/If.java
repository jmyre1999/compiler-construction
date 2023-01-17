/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

// The "else" part is not optional in the MiniJava "if" statement

public class If extends Statement {
   public final Expression e;
   public final Statement s1,s2;

   public If (Expression ae, Statement as1, Statement as2) { this (0,0,ae,as1,as2); }
   public If (int l, int c, Expression ae, Statement as1, Statement as2) {
      super (l,c); e=ae; s1=as1; s2=as2;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}
