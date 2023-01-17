/*
 *  Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import tree.Stm;
import tree.ExpList;

public class SEQ extends Stm {

   public final Stm left, right;

   public SEQ (final Stm l, final Stm r) {
      if (l==null) throw new RuntimeException ("first arg in tree.SEQ must not be null");
      if (r==null) throw new RuntimeException ("second arg in tree.SEQ must not be null");
      left=l; right=r;
   }

   public static SEQ fromList (final Stm... args) {
      final int l = args.length;
      assert l>1;
      SEQ s = new SEQ (args[l-2], args[l-1]);
      for (int i=l-3; i>=0; i--) {
         s = new SEQ (args[i], s);
      }
      return s;
   }
}

