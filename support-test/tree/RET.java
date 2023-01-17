/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import tree.Stm;
import tree.Exp;

/*
   RET(s,e) -- Execute statement s and return value of e.

   ESEQ renamed

   Watch for all the uses of ESEQ in package canon, so this
   class is not yet deprecated.
*/

public class RET extends Exp {
   public final Stm stm;
   public final Exp exp;
   public RET (Stm s, Exp e) {
      assert (e!=null);
      stm=s; exp=e;
   }
}
