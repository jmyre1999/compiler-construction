/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package tree;

import tree.ExpList;  // We'd like to get rid of this; see canonicalization

import java.util.List;


/*
  Concrete subclasses:  MOVE, EVAL, JUMP, CJUMP, SEQ, LABEL

  Possible renaming:  MoveStm, EvalStm, JumpStm, ConditionalStm, SequenceStm, LabelStm
 */

public abstract class Stm {

   public Stm    build(ExpList kids) {
      throw new UnsupportedOperationException("Can't put together the Stm "+this.getClass().getName());
   }
   public ExpList kids() {
      throw new UnsupportedOperationException("Can't take apart the Stm "+this.getClass().getName());
   }

   public String  toString ()          {return TreePrint.toString (this); }

   public Stm       create (final List<Exp> kids)  {
      throw new UnsupportedOperationException("Can't put together the Stm "+this.getClass().getName());
   }
   public List<Exp> subcomponents () {
      throw new UnsupportedOperationException("Can't take apart the Stm "+this.getClass().getName());
   }

}





