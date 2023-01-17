/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package syntax;

public class ArrayAssign extends Statement {

   public final Identifier nameOfArray;
   public final Expression indexInArray, e;

   public ArrayAssign (int l, int c, Identifier ai, Expression ae1, Expression ae2) {
      super(l,c); nameOfArray=ai; indexInArray=ae1; e=ae2;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

}

