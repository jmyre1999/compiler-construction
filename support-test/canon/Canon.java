/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2st edition, by Andrew W. Appel.
 */

package canon;

import tree.*;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public final class Canon {

   public static List<Stm> linearize (final Stm s) {
      assert (s!=null);
      return linear (do_stm(s));
   }

   // Remove all SEQ; flatten them to a list
   private static List<Stm> linear (final Stm s) {
      return linear (s, new LinkedList<Stm>());
   }

   private static List<Stm> linear (final Stm s, final List<Stm> l) {
      if (s instanceof SEQ) return linear((SEQ)s, l);
      else return cons(s,l);
   }

   private static List<Stm> linear (final SEQ s, final List<Stm> l) {
      return linear (s.left, linear(s.right, l));
   }

   private static List<Stm> cons (final Stm s, final List<Stm> l) {
      assert ! (s instanceof SEQ);
      l.add (0, s);
      return l;
   }


   @Deprecated
   private static class MoveCallX extends Stm {
      final TEMP dst;
      final CALL src;
      MoveCallX (TEMP d, CALL s) {dst=d; src=s;}
      public ExpList kids() {return src.kids();}
      public Stm build (ExpList kids) {
         return new tree.MOVE (dst, src.build(kids));
      }
   }   

   private static class MoveCall extends Stm {
      final TEMP dst;
      final CALL src;
      MoveCall (TEMP d, CALL s) {dst=d; src=s;}
      @Override
      public ExpList kids() {return src.kids();}
      @Override
      public Stm build (ExpList kids) {
         return new tree.MOVE (dst, src.build(kids));
      }
      @Override
      public List<Exp> subcomponents() {return src.subcomponents();}
      @Override
      public Stm create (List<Exp> kids) {
         return new tree.MOVE (dst, src.create(kids));
      }
   }   
  
   private static class ExpCall extends Stm {
      final CALL call;
      ExpCall (CALL c) {call=c;}
      @Override
      public ExpList kids() {return call.kids();}
      @Override
      public Stm build(ExpList kids) {
         return new tree.EVAL (call.build(kids));
      }

      @Override
      public List<Exp> subcomponents() {return call.subcomponents();}
      @Override
      public Stm create (List<Exp> kids) {
         return new tree.EVAL (call.create(kids));
      }

   }   

   // A pair (Stm,ExpList)
   private static final class StmPair<T> {
      final Stm stm;
      final T exps;
      StmPair (Stm s, T e) {stm=s; exps=e;}
   }

   private static boolean isNop (Stm a) {
      return a instanceof EVAL && ((EVAL)a).exp instanceof CONST;
   }

   private static boolean commute (final Stm a, final Exp b) {
      return isNop(a) || b instanceof NAME || b instanceof CONST;
   }

   private static Stm seq (final Stm a, final Stm b) {
      if (isNop(a)) return b;
      else if (isNop(b)) return a;
      else return new SEQ(a,b);
   }

   private static Stm seq (final Stm a, final Stm b, final Stm c) {
      return (seq (a, seq (b, c)));
   }

   /*
     Remove ESEQ (=RET) and move CALLs to top level

     tree.Stm do_stm (tree.Stm)
     tree.RET do_exp (tree.Exp)
    */


   private static Stm do_stm (final SEQ s) { 
      return seq (do_stm(s.left), do_stm(s.right));
   }


   private static Stm do_stm (final MOVE s) { 
      assert (s!=null);
      if (s.dst instanceof TEMP && s.src instanceof CALL) 
         return reorder_stm (new MoveCall((TEMP)s.dst, (CALL)s.src));
      else if (s.dst instanceof tree.ESEQ) throw new RuntimeException ("Do not use ESEQ");
      else if (s.dst instanceof RET)
	 return do_stm (new SEQ(((RET)s.dst).stm, new MOVE(((RET)s.dst).exp, s.src)));
      else return reorder_stm (s);
   }

   private static Stm do_stm (final EVAL s) {
      assert (s!=null);
      if (s.exp instanceof CALL)
	 return reorder_stm (new ExpCall((tree.CALL)s.exp));
      else return reorder_stm(s);
   }

   private static Stm do_stm (final Stm s) {
      assert s!=null;
      if (s instanceof SEQ) return do_stm ((SEQ)s);
      else if (s instanceof MOVE) return do_stm ((MOVE)s);
      else if (s instanceof EVAL) return do_stm ((EVAL)s);
      else return reorder_stm(s);
   }

   private static Stm reorder_stm (final Stm s) {
      assert (s!=null);
      final StmPair<ExpList> x = reorderExpList (s.kids());
      assert (x!=null);
      //final StmPair<ExpList> y = reorderExpList (ExpList.fromList (s.subcomponents()));
      //assert (y!=null);
      return seq (x.stm, s.build (x.exps));
   }

   private static RET do_exp (final RET e) {
      final Stm stms = do_stm (e.stm);
      final RET b = do_exp (e.exp);
      return new RET (seq(stms,b.stm), b.exp);
   }

   private static RET do_exp (final Exp e) {
      assert e!=null;
      assert (! (e instanceof tree.ESEQ) );
      if (e instanceof tree.RET) return do_exp((tree.RET)e);
      else return reorder_exp(e);
   }

   private static RET reorder_exp (final Exp e) {
      assert e!=null;
      assert (! (e instanceof tree.ESEQ) );
      assert (! (e instanceof tree.RET) );
      final StmPair<ExpList> x = reorderExpList(e.kids());
      //final StmPair<ExpList> y = reorderExpList(ExpList.fromList (e.subcomponents()));
      return new RET (x.stm, e.build(x.exps));
   }

   private static final StmPair<ExpList> nopNull =
      new StmPair<>(new EVAL(new tree.CONST(0)),null);


   private static final Stm nop = new EVAL(new tree.CONST(0));

   static StmPair<List<Exp>> reorder (final List<Exp> exps) {
      if (exps==null) return new StmPair<> (nop, null);
      // FIX ME!
      return null;
   }

   static StmPair<ExpList> reorderExpList (final ExpList exps) {
      if (exps==null) {
	 return nopNull;
      } else {
	 final Exp a = exps.head;
         assert a!=null;
	 if (a instanceof tree.CALL) {
            final TEMP t = TEMP.generateTEMP("call");            // Generate a new TEMP
	    final Exp e = new tree.RET(new tree.MOVE(t, a), t);
	    return reorderExpList (new ExpList(e, exps.tail));
	 } else {
	    tree.RET aa = do_exp(a);
	    final StmPair<ExpList> bb = reorderExpList(exps.tail);
	    if (commute(bb.stm, aa.exp)) {
               final Stm st = seq(aa.stm,bb.stm); 
	       return new StmPair<ExpList>(st, new ExpList(aa.exp,bb.exps));
	    } else {
               final TEMP t = TEMP.generateTEMP("hoist");
               final Stm st = seq(aa.stm, new tree.MOVE(t,aa.exp), bb.stm);
	       return new StmPair<ExpList>(st, new ExpList(t, bb.exps));
	    }
	 }
      }
   }
}
