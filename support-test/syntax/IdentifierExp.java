/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  An IdentiferExp (which extends the abstract class Expresion) is the use
  of an identifer as an expression.

  An IdentiferType (which extends the abstract class Type) is the use of
  an identifer as a type.

  How does an IdentifierExp and IdentifierType differ from an Identifier?

  An Identifier is part of a particular, syntactic structure:
    class IDENTIFIER ...        // a class declaration
    new IDENTIFIER ()           // class instantiation
    ... . IDENTIFIER ( ... )    // a call
    IDENTIFIER [...] := ...     // array assignment
    type IDENTIFIER, ....       // formal argument
    ... IDENTIFIER ( ... )      // method declaration
    IDENTIFIER := ...           // assignment

*/

public class IdentifierExp extends Expression {
   public final String s;
   
   public IdentifierExp (int l, int c, String as) { super (l,c); s=as; }

   @java.lang.Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   @java.lang.Override
   public String toString () { return s; }

}
