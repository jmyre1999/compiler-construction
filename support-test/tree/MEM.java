/*
 * CSE3001.  Compiler project based on "Modern Compiler Implementation
 * in Java" by Andrew W. Appel.
 *
 */

package tree;

import tree.Exp;
import tree.ExpList;

public class MEM extends Exp {
   public final Exp exp;
   public MEM(Exp e) {exp=e;}
   public ExpList kids() {return new ExpList(exp,null);}
   public Exp build(ExpList kids) {return new MEM(kids.head);}
}

