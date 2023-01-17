/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  Do not instantiate this node but once.
*/
public class IntArrayType extends Type {

   IntArrayType () {};  // The constructor is restricted to this package.

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   @Override
   public String toString() { return "int[]"; }

   @Override
   public String fieldDescriptor () { return "[I"; }
}
