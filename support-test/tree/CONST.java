/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import tree.Stm;
import tree.ExpList;

import java.util.List;

public class CONST extends Exp {
   public final static Exp ZERO= new CONST(0);
   public final static Exp ONE = new CONST(1);
   public final static Exp TRUE = ONE;
   public final static Exp FALSE= ZERO;
   public final int value;
   public CONST(int v) {value=v;}
   public ExpList kids() {return null;}
   public Exp build(ExpList kids) {return this;}
   @java.lang.Override
   public Exp   create (final List<Exp> kids) {return this;}
   @java.lang.Override
   public List<Exp> subcomponents () {return null;}
}

