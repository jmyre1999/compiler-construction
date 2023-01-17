/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  This class in completely pointless and could be replaced by the simple
  use of the Java java.lang.String class.
*/

/*
  An Identifier is part of particular, syntactic structure:
    class IDENTIFIER ...        // a class declaration
    new IDENTIFIER ()           // class instantiation
    ... . IDENTIFIER ( ... )    // a call
    IDENTIFIER [...] := ...     // array assignment
    type IDENTIFIER, ....       // formal argument
    ... IDENTIFiER ( ... )      // method declaration
    IDENTIFIER := ...           // assignment

    It is not to be confused with the syntax.IdentifierExp class which represents
    an identifier/string used as an expression.  Similiarly, do not confuse this class
    with the syntax.IdentifierType class which represents and identifer/string used as
    a type.
*/

public class Identifier extends AST {
   public final String s;

   public Identifier (final int l, final int c, final String as) { super (l,c); s=as; }

   @java.lang.Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   @java.lang.Override
   public String toString () { return s; }

}
