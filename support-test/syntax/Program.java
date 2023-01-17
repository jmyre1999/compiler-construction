/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

import java.util.List;

/*
 * No point in making this class extend AST as we don't really have a position
 * (line number, column number) for the whole program.  The only purpose of the
 * abstract class AST is to share the position declaration with all subclasses.
 */

public final class Program {

   public final MainClass m;
   public final List <ClassDecl> cl;

   public Program (final MainClass am, final List <ClassDecl> acl) {
      m=am; cl=acl; 
   }

   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
}
