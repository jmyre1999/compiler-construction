/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package tree;

import tree.Exp;
import tree.ExpList;

import java.util.List;

public class BINOP extends Exp {
   public final int binop;
   public final Exp left, right;

   public final static int
      PLUS=0,
      MINUS=1,
      MUL=2,     /* Warning:  no multiplication operation in SPARC */
      DIV=3,     /* Warning:  no division operation in SPARC       */
      AND=4,
      OR=5,
      LSHIFT=6,
      RSHIFT=7,
      ARSHIFT=8,
      XOR=9
      ;

   public BINOP (int b, Exp l, Exp r) {
      binop=b; left=l; right=r; 
   }

   @Override
   public ExpList kids() {return new ExpList(left, new ExpList(right,null));}

   @Override
   public Exp build (ExpList kids) {
      return new BINOP(binop,kids.head,kids.tail.head);
   }

   @Override
   public List<Exp> subcomponents() {return List.of (left,right);}

   @Override
   public Exp create (final List<Exp> kids) {
      return new BINOP(binop,kids.get(0),kids.get(1));
   }
}

