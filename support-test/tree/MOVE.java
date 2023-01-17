/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import tree.Stm;
import java.util.List;
import java.util.Collections;

public class MOVE extends Stm {
   public final Exp dst, src;
   public MOVE (final Exp d, final Exp s) {dst=d; src=s;}

   public ExpList kids() {
      if (dst instanceof MEM) {
	 return new ExpList(((MEM)dst).exp, new ExpList(src,null));
      } else {
	 return new ExpList(src,null);
      }
   }
   public Stm build(ExpList kids) {
      if (dst instanceof MEM) {
	 return new MOVE(new MEM(kids.head), kids.tail.head);
      } else {
	 return new MOVE(dst, kids.head);
      }
   }

   @Override
   public List<Exp> subcomponents () {
      if (dst instanceof MEM) {
         return List.of (((MEM)dst).exp, src);
      } else {
	 return Collections.singletonList (src);
      }
   }
   @Override
   public Stm create(final List<Exp> kids) {
      if (dst instanceof MEM) {
	 return new MOVE(new MEM(kids.get(0)), kids.get(1));
      } else {
	 return new MOVE(dst, kids.get(0));
      }
   }
}

