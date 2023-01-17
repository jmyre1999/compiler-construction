/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  The void type may not be really useful in MiniJava, but it is the
  return type of the special 'main' method as in real Java.
*/

public final class VoidType extends Type {

   protected VoidType () { super(); }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      //      return v.visit(this);
      throw new UnsupportedOperationException ("VoidType.accept");
   }

   @Override
   public String toString() { return "void"; }

   @Override
   public String fieldDescriptor () { return "V"; }
}
