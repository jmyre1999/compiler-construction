/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

import java.util.List;

public class ExtendingClassDecl extends ClassDecl {
   public final Identifier j; // name of super class

   public ExtendingClassDecl (
      Identifier ai, Identifier aj, List <FieldDecl> afl, List <MethodDecl> aml) {
      super (ai, afl, aml);
      j=aj;
   }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}
