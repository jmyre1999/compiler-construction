/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

/*
 * The entire "assem" package is provided to the student to be used as
 * is in the completion of the "Program: Instruction Selection", Chapter 9,
 * page 216.
 */

/*
  "A MOVE is like an OPER, but must perform only data transfer.  Then if
   the dst and src temporaries are assigned to the same register, the MOVE
   can later be deleted.  Appel, Chapter 9, page 210.
*/

/*
  The MoveInstruction is reserved for moving Temps to Temps.  It is not used
  for loads and stores (OperationInstruction is used instead).
*/

package assem;

import tree.NameOfTemp;

import java.util.List;
import java.util.Collections;

public class MoveInstruction extends Instruction {
   private final NameOfTemp dst, src;

   public MoveInstruction (final String a, final NameOfTemp d, final NameOfTemp s) {
      super(a); dst=d; src=s;
   }

   public MoveInstruction (String a, String comment, NameOfTemp d, NameOfTemp s) {
      super(a, comment); dst=d; src=s;
   }

   public List<NameOfTemp>  use()    {return Collections.singletonList (src); }
   public List<NameOfTemp>  def()    {return Collections.singletonList (dst); }

   @Override
   public boolean isMove() { return true; }

}
