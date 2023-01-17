/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package syntax;

/*
   This class may not need to extend AST as Identifier carries the position.  On the
   other hand we may wish to mark the position of general class errors by the position
   of the "class" keyword which will likely be close but not identical to the position
   of the class name.
*/

public class MainClass extends AST {
   public final Identifier i1,i2;  // name of the class, name of the argument to method "main"
   public final Statement s;

   @Deprecated
   public MainClass (Identifier ai1, Identifier ai2, Statement as) {
      this (0,0,ai1,ai2,as);
   }

   public MainClass (int l, int c, Identifier ai1, Identifier ai2, Statement as) {
      super (l,c); i1=ai1; i2=ai2; s=as;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}

