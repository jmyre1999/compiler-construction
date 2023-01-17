/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class Assign extends Statement {
   public final Identifier i;
   public final Expression e;

   public Assign (Identifier ai, Expression ae) { this(0,0,ai,ae); }
   public Assign (int l, int c, Identifier ai, Expression ae) { super(l,c); i=ai; e=ae; }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}

