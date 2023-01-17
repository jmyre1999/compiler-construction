/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

/*
 * A Label represents an label in assembly language:  a  machine-language 
 * location whose exact address is yet to be determined.
 *
 * The class allows the creation of unique labels 'X$Y$Z123' and specific
 * labels for calls to library functions, for example.
 *
 * See sketch of this class in Appel, 2nd, page 131.
 */

// Is NameAsLabel a better name?
public final class NameOfLabel  {

   private final String name;
   private static final String FMT = "%s%03d";

   private NameOfLabel (final String n)        { name = n; }
   public  NameOfLabel (final String ... a)    { this (NameOfLabel.concat (a)); }
   private NameOfLabel (final String n, int c) { this (String.format (FMT, n, c)); }

   // Machine architectural decisions ought not to be in the 'tree' package
   private static final char sep = '$';  // Needs to be legal in assembly

   // Generate unique, new labels using 'args' as a debugging hint.
   // Static, factory methods
   private static int count = 0;
   public static NameOfLabel generateLabel (String... args) {
      if (args.length<1) return new NameOfLabel("L", ++count);
      return new NameOfLabel (concat (args), ++count);
   }

   public static String concat (String ... args) {
      assert args.length>0;
      final StringBuilder sb = new StringBuilder(args[0]);
      for (int i=1; i<args.length; i++) {
         sb.append (sep);
         sb.append (args[i]);
      }
      return sb.toString();
   }


   @java.lang.Override
   public String toString() { return name; }
   @java.lang.Override
   public int hashCode()    { return name.hashCode(); }
   @java.lang.Override
   public boolean equals (Object other) {
      if (this == other) return true;
      if (other == null) return false;
      if (this.getClass() != other.getClass()) return false;
      final NameOfLabel o = (NameOfLabel) other;
      return name.equals (o.name);
   }

   public static void main (String... args) {
      System.out.println (generateLabel ());
      System.out.println (generateLabel ("member"));
      System.out.println (generateLabel ("class", "member"));
      System.out.println (generateLabel ("package", "class", "member", "local"));
   }
}
