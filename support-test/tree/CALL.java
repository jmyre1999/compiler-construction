/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import java.util.List;  // eventually for List<Exp>
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;  // eventually for List<Exp>

/*
  A CALL is an expression; its value is the value returned by the function call.
*/

public class CALL extends Exp {
   public final Exp     func;
   public final ExpList args;         // FIXME
   public final List<Exp> UNUSEDargs;

   private CALL (Exp f, ExpList a, List<Exp> x) {
      func=f; args=a; UNUSEDargs = x;
   }

   public CALL (Exp f, Exp a) {
      this (f, new ExpList (a,null), Collections.singletonList (a));
   }

   public CALL (NameOfLabel f, Exp a) {
      this (new NAME(f), a);
   }

   public CALL (Exp f) {
      this (f, (ExpList) null, Collections.emptyList());
   }


   public CALL (Exp f, Exp...x) {
      this (f, Arrays.asList (x));
   }


   public CALL (Exp f, List<Exp> l) {
      this (f, ExpList.fromList (l), l);
   }

   // We want to switch from 'kids' to 'subcomponent'
   public ExpList kids() {return new ExpList(func,args);}

   // We want to switch from 'build' to 'create'
   public Exp build (final ExpList kids) {
      return new CALL (kids.head,kids.tail.toList());
   }

   @Override
   public List<Exp> subcomponents () {
      final int n = UNUSEDargs.size() + 1;
      final List<Exp> sub = new ArrayList<> (n);
      sub.add (func);
      for (int i=1; i<n; i++) sub.add (UNUSEDargs.get(i-1));
      return sub;
   }

   public Exp create (final Exp... kids) {
      return new CALL (kids[0], Arrays.asList (Arrays.copyOfRange (kids,1,kids.length)));
   }


}

