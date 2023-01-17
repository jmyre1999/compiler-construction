/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;


/*
  A speculative class consolidating all the primitive types.
*/

public final class PrimitiveType extends Type {

   /*
     B 	byte 	signed byte
     C 	char 	Unicode character code point in the Basic Multilingual Plane, encoded with UTF-16
     D 	double 	double-precision floating-point value
     F 	float 	single-precision floating-point value
     I 	int 	integer
     J 	long 	long integer
     S 	short 	signed short
     Z 	boolean true or false

     V  void    
     L ClassName ; 	reference 	an instance of class ClassName
     [ reference 	one array dimension

    */

   private final String name;
   public PrimitiveType (final String theName) {
      super ();
      this.name = theName;
   }
   public PrimitiveType (final int l, final int c, final String theName) {
      super (l,c);
      this.name = theName;
   }
      
   @Override
   public <T> T accept (final SyntaxTreeVisitor <T> v) {
      // return v.visit(this); // Not in visitor interface (yet)
      throw new UnsupportedOperationException ("PrimitiveType.accept");

   }

   @Override
   public String toString() { return name; }

   @Override
   public String fieldDescriptor () { return "?"; }
}
