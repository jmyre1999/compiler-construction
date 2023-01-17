/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class ArrayLength extends Expression {

   public final Expression expressionForArray;  // the type of e is presumably an array
  
   public ArrayLength (final int l, final int c, final Expression e) {
      super (l,c);
      expressionForArray=e;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}
