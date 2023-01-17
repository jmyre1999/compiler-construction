/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

/*
  Temps are abstract names for values temporarily held in registers.

  See sketch of this class in Appel, 2nd, page 131.  He puts it in the package
  'temp'.  It does not really belong in package 'tree', but ...
*/

/*
  Need to implement equals correctly for HashMap!
*/

package tree;

// Is NameAsTemp a better name?
public final class NameOfTemp implements Comparable<NameOfTemp> {

   private final String name;
   private static final String FMT = "%s%03d";

   private NameOfTemp (String n)              { name = n; }
   public  NameOfTemp (final String ... a)    { this (NameOfLabel.concat (a)); }
   private NameOfTemp (final String n, int c) { this (String.format (FMT, n, c)); }

   //Static, factory methods.
   private static int count = 0;
   public static NameOfTemp generateTemp ()         { return generateTemp ("t"); }
   public static NameOfTemp generateTemp (String s) { return new NameOfTemp (s, ++count); }

   @java.lang.Override
   public String toString() { return name; }
   @java.lang.Override
   public int hashCode()    { return name.hashCode(); }
   @java.lang.Override
   public boolean equals (Object other) {
      if (this == other) return true;
      if (other == null) return false;
      if (this.getClass() != other.getClass()) return false;
      final NameOfTemp o = (NameOfTemp) other;
      return name.equals (o.name);
   }
   @java.lang.Override
   public int compareTo (final NameOfTemp other) {
      return this.name.compareTo (other.name);
   }
}
