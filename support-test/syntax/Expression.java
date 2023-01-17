/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  Many concrete subclasses: And, ArrayLength, ArrayLookup, Call,
  False, IdentifierExp, IntegerLiteral, LessThan, Minus, NewArray,
  NewObject, Not, Plus, This, Times, True.
*/

public abstract class Expression extends AST {
   @Deprecated
   Expression () { this(0,0); }

   public Expression (int l, int c) { super (l,c); }
}
