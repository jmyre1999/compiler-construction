/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  Get an element of an array at a particular index.

  Why is 'expressionForArray' not simply an identifer?  An array in
  MiniJava can only be denoted by an identifier, but in Java the
  expression 'twoDimArrayName[3]' might also denote an array.
*/

public class ArrayLookup extends Expression {
   public final Expression expressionForArray, indexInArray;
   public ArrayLookup (final int l, final int c, final Expression e1, final Expression e2) {
      super (l,c);
      expressionForArray=e1; indexInArray=e2;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}
