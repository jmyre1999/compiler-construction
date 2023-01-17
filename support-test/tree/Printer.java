/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

/*
  This non-public class supports the public class TreePrint.
*/

package tree;

import java.io.PrintWriter;

class Printer {

   final PrintWriter out;

   Printer (PrintWriter o) { out=o; }

   private int o=0;
   void out (final int o) {
      this.o = o;
   }

   void indent (int d) {
      for (int i=this.o; i<d; i++) {
	 out.print (' ');
      }
      this.o=0;
   }

   void say (String s) { out.print(s); }

   void sayln (String s) { say(s); say("\n"); }

   void say (int d, String s) { indent (d); say (s); }

   void sayln (int d, String s) { indent (d); sayln (s); }

   void flush () { out.flush(); }
}

