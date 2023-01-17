/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public class BooleanType extends Type {

   protected BooleanType () { super(); }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   @Override
   public String toString() { return "boolean"; }

   @Override
   public String fieldDescriptor () { return "B"; }
}
