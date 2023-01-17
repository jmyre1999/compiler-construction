/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package tree;

import tree.Stm;
import tree.ExpList;

import java.util.List;
import java.util.Collections;

/*
   EVAL(e)  --  Evaluate e and discard the result.

   Used to be named EXP; renamed to reduce confusion.
*/
public class EVAL extends Stm {
   public final Exp exp; 
   public EVAL (Exp e) {exp=e;}
   public ExpList kids() {return new ExpList(exp,null);}
   public Stm build (ExpList kids) {
      return new EVAL(kids.head);
   }
   public Stm build (List<Exp> kids) {
      return new EVAL(kids.get(0));
   }

   @Override
   public List<Exp> subcomponents () {
      return Collections.singletonList (exp);
   }
   @Override
   public Stm create(final List<Exp> kids) {
      return new EVAL (kids.get(0));
   }

}

