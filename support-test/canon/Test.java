/*
  The "canon" package depends on the given package "tree" for all the intermediate
  representation code.
*/

package canon;

import tree.Stm;
import tree.Exp;
import tree.TreePrint;

import java.util.List;

public class Test {

   public static tree.Stm begin = tree.LABEL.generateLABEL ("endOfPrelude");
   public static tree.Stm jump  = new tree.JUMP ("epilog");
   public static tree.ExpList a = new tree.ExpList (new tree.CONST (34), new tree.ExpList (new tree.CONST (81), null));

   public static tree.Stm assign (String x, int i) {
      return new tree.MOVE (new tree.MEM (new tree.NAME ("x")), new tree.CONST(i));
   }
   public static tree.Exp call (String x, tree.Exp... l) {
      return new tree.CALL (new tree.NAME (x), l);
   }
   public static tree.Exp add (tree.Exp l, int i) {
      return new tree.BINOP (tree.BINOP.PLUS, l, new tree.CONST(i));
   }
   
   public static tree.Stm s1 = assign ("z", 37);

   /* Appel, Exercise 8.1, page 193.  */

   public static tree.NameOfLabel ok = tree.NameOfLabel.generateLabel ("ok");
   public static tree.NameOfLabel out= tree.NameOfLabel.generateLabel ("out");
   public static tree.TEMP t = tree.TEMP.generateTEMP ();
   public static tree.Stm  s = new tree.EVAL (call ("fun", new tree.CONST(3), new tree.CONST(81)));

   /*
      x := 3   new tree.MOVE (new tree.MEM (new tree.NAME ("x")), new tree.CONST (3))
   */
   public static tree.Exp e = new tree.CONST (2718);
   public static tree.Exp e1= new tree.CONST (1828);
   public static tree.Exp e2= new tree.MEM (new tree.NAME ("s$c"));
   public static tree.Exp e3= new tree.CONST (553);
   public static tree.Exp e4= t;

   public static tree.ExpList args = new tree.ExpList (e1, new tree.ExpList (e2, null));

   public static Stm exercise_81a =
      new tree.MOVE (t, new tree.RET (s, e));

   public static Stm exercise_81az =
      new tree.SEQ (begin, new tree.SEQ (new tree.MOVE (t, new tree.RET (s, e)), jump));

   public static Stm exercise_81b =
      new tree.MOVE (new tree.MEM (new tree.RET (s, e1)),e);

   public static Stm exercise_81c =
      new tree.MOVE (new tree.MEM (e1), new tree.RET (s, e));

   public static Stm exercise_81d =
      new tree.EVAL (new tree.RET (s, e));

   public static Stm exercise_81e =
      new tree.EVAL (new tree.CALL (new tree.RET (s, e), e1, e2));

   public static Stm exercise_81f =
      new tree.MOVE (t, new tree.CALL (new tree.RET (s, e), e1, e2));

   public static Stm exercise_81g =
      new tree.EVAL (call ("f", e2, new tree.RET (assign ("z", 37), e3), e4));


   public static Stm exercise_82a =
      new tree.MOVE (new tree.MEM (new tree.RET (new tree.SEQ (
         new tree.CJUMP (tree.CJUMP.LT,t,new tree.CONST(0),out,ok),new tree.LABEL(ok)),t)),e);

   public static Stm exercise_82z =
      new tree.MOVE (new tree.MEM (new tree.RET (new tree.SEQ (
         new tree.CJUMP (tree.CJUMP.LT,t,new tree.CONST(0),ok,out),new tree.LABEL(ok)),t)),e);

   public static Stm call2 = new tree.EVAL (new tree.CALL (new tree.NAME ("proc")));
   public static Stm x = new tree.SEQ (call2, new tree.SEQ (s,s));
   public static Stm z = new tree.SEQ (begin, new tree.SEQ (x, new tree.JUMP("fin")));

   /*  En example related to dynamic dispatch */
   public static Exp to = new tree.TEMP ("to");
   public static Stm sz0 = 
   new tree.SEQ (
                 new tree.MOVE (to,call ("alloc", new tree.CONST(1))),
     new tree.SEQ (
       new tree.MOVE (new tree.TEMP ("%l0"),
          new tree.CALL (new tree.MEM(add (to, 2)), to)),
       new tree.MOVE (new tree.TEMP ("%g1"), new tree.CONST(3))));
   /*
SEQ(
  SEQ(
    MOVE(
      TEMP to,
      RET(
        MOVE(
          TEMP this,
          CALL( NAME alloc, CONST 1)),
        CALL(
          MEM(
            BINOP(PLUS, MEM( TEMP this), 0)),
          TEMP this))),
  MOVE( TEMP %g1, CONST 3))
    */
   private static Exp ths = new tree.TEMP("this");
   public static Stm sz1 = 
     new tree.SEQ (
       new tree.SEQ (
         new tree.MOVE (to,
           new tree.RET(
                         new tree.MOVE (ths,
                            call ("alloc", new tree.CONST(1))),
                         new tree.CALL (new tree.MEM(add (ths, 2)), ths))),
         new tree.MOVE (new tree.TEMP ("%g1"), new tree.CONST(3))),
       new tree.MOVE (new tree.TEMP ("%g1"), new tree.CONST(4)));


   public static void main (String [] args) {
      System.out.format ("%n%nStraight line code%n");
      main (x);

      System.out.format ("%n%nStraight line code%n");
      main (z);

      System.out.format ("%n%nExercise 81a%n");
      main (exercise_81a);
      System.out.format ("%n%nExercise 81a, but with an epilog label%n");
      main (exercise_81az);
      System.out.format ("%n%nExercise 81b%n");
      main (exercise_81b);
      System.out.format ("%n%nExercise 81c%n");
      main (exercise_81c);
      System.out.format ("%n%nExercise 81d%n");
      main (exercise_81d);
      System.out.format ("%n%nExercise 81e%n");
      main (exercise_81e);
      System.out.format ("%n%nExercise 81f%n");
      main (exercise_81f);
      System.out.format ("%n%nExercise 81g%n");
      main (exercise_81g);
      System.out.format ("%n%nExercise 82a%n");
      main (exercise_82a);
      System.out.format ("%n%nExercise 82z, reverse t/f%n");
      main (exercise_82z);

      System.out.format ("%n%nTest case Jacob%n");
      main (sz1);


   }

   public static void main (Stm s) {
      final TreePrint p = new TreePrint (System.out);
      System.out.println ("At the beginning");
      p.print (s);

      System.out.println ("\nAfter linearize");
      final List<Stm> stms = Canon.linearize (s);
      p.print (stms);

      final List<List<Stm>> bb = BasicBlocks.makeBlocks (stms);
      System.out.format ("%nThe number of basic blocks = %d%n", bb.size());
      BasicBlocks.print (bb);
      
      final List<Stm> traces = TraceSchedule.trace(bb);
      System.out.println ("\nAfter trace scheduling");
      p.print (traces);

      System.out.println ("\nEnd of test case.");
   }
}
