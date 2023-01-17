/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package tree;

import tree.Stm;
import tree.Exp;

/*
   ESEQ(s,e) -- Execute statement s and return value of e.

   Consider renaming to RET
*/
 
public class ESEQ extends Exp {
   public final Stm stm;
   public final Exp exp;
   @Deprecated
   public ESEQ (final Stm s, final Exp e) {
      assert (s!=null);
      assert (e!=null);
      stm=s; exp=e;
   }
}

