/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package syntax;

import java.util.List;

/*
 * "SimpleClassDecl" and "ExtendingClassDecl" are the two concrete subclasses
 * of this class, "ClassDecl".
 *
 * No need to extend AST as the class name, "Identifier", carries the position
 * (line number and column number) which is adequate for reporting errors
 * concerning classes in general.
 */

public abstract class ClassDecl {
   public final Identifier i;       // The name of the class.  It carries position info: line number, column number
   public final List<FieldDecl> fields;  
   public final List<MethodDecl> methods;

   public ClassDecl (final Identifier ai, final List <FieldDecl> afl, final List <MethodDecl> aml) {
      i=ai; fields=afl; methods=aml;
   }

   public abstract <T> T accept (final SyntaxTreeVisitor <T> v);
}
