/*
 * CSE3001.  Compiler project based on "Modern Compiler Implementation
 * in Java" by Andrew W. Appel.
 *
 */

package tree;

import tree.Stm;

@Deprecated
public class StmList {
   public final Stm head;
   public StmList tail;
   public StmList (Stm h, StmList t) {head=h; tail=t;}
}



