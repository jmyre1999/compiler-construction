/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  Hypothetical, experimental!

  Concrete subclasses:
     LocalDecl for local variable declarations
     FieldDecl for class field member declarations
     FormalDecl for subprocedure parameter declartions

  All have the same representation
*/

/*
  The location of an identifer declaration in the original source file can be
  taken to be that of the syntax.Identifier "i".  This subclass of AST has
  a linenumber and a column number.

  Why not make this a subclass of AST?  Too many line numbers?!
*/

public abstract class IdentifierDecl {
   public final Type t;
   public final Identifier i;
  
   public IdentifierDecl (Type at, Identifier ai) { t=at; i=ai;  }

   /*
     Not yet implmented!
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }
   */
}
