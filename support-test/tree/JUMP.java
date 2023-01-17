/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import tree.Exp;
import tree.Stm;
import tree.ExpList;

import java.util.List;
import java.util.Collections;

/*
   JUMP (e, labels)  -- Transfer control to address e.  For
   the purposes of doing dataflow analysis all possible targets
   are given in the list of labels.
*/

public class JUMP extends Stm {
   public final Exp exp;
   public List<NameOfLabel> targets;
   public JUMP (Exp e, List<NameOfLabel> t) {exp=e; targets=t;}
   public JUMP (NameOfLabel target) {
      this(new NAME(target), Collections.singletonList (target));
   }
   public JUMP (String s) { this (new NameOfLabel (s)); }
      
   public ExpList kids() {return new ExpList(exp,null);}
   public Stm build (ExpList kids) { return new JUMP(kids.head,targets); }

   @Override
   public List<Exp> subcomponents() {return Collections.singletonList(exp); }
   public Stm   create (final List<Exp>kids) { return new JUMP (kids.get(0),targets); }
}

