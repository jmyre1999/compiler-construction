/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

import java.util.List;

public class Call extends Expression {
   public final Expression e;           // Instance receiving the method invocation
   public final Identifier i;           // Name of method invoked
   public final List <Expression> el;   // Actual arguments
  
   public Call (final int l, final int c, final Expression ae, final Identifier ai, final List <Expression> ael) {
      super(l,c); e=ae; i=ai; el=ael;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   /* It is very convenient during semantic analysis to determine and remember the
      class of the receiver which may be where the method is defined.

      In the absence of dynamic dispatch that is clear enough.  However, if dynamic
      dispatch is implmented then this refers to the class based on the statically
      known type of the instance. The actual method called might be an overriding
      method in a subclass.
 */

   private String receiverClassName = null;
   public void setReceiverClassName (String r) { receiverClassName = r; }
   public String getReceiverClassName () { return receiverClassName; }
}
