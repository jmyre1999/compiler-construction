/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

/*
  These identifiers are the names of MiniJava classes (not int,
  boolean, or int[]) used in just in the AST class "Type" found in
  fields declarations, formal declarations, local declarations, or
  method return types.
 */
  
public class IdentifierType extends Type {
   public final String s;

   public IdentifierType (int l, int c, String as) { super(l,c); s=as;  }

   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      return v.visit(this);
   }

   @Override
   public String toString () { return s; }

   @Override
   public String fieldDescriptor () { return String.format ("L%s;", s); }

}
