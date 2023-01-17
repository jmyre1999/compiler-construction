/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class NewObject extends Expression {

   // Should this be an IdentifierType?  There is really in no advantage
   public final Identifier i;

   // Don't really need a position as long as the ident has one.
   public NewObject (Identifier ai) { this(0,0,ai);  }
   public NewObject (int l, int c, Identifier ai) { super(l,c); i=ai;   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

}
