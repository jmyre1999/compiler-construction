/*
 * CSE3001.  Compiler project based on "Modern Compiler Implementation
 * in Java" by Andrew W. Appel.
 *
 */
package canon;

import tree.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TraceSchedule {

   public static List<tree.Stm> trace (final List<List<tree.Stm>> bb) {
      final TraceSchedule ts = new TraceSchedule(bb);
      //      ts.getNext();
      ts.getNext2();
      return ts.getSchedule();
   }

   /* deprecated
   private final List<List<Stm>> theBB = null;
   */


   /*
     A linked hash map has the advantage of make the first basic block
     first.  Thus avoid a jump at the end of the prelude.
     
     This is no guarantee that the last blcok will be scheduled last,
     so the epilog will require a label.
   */
   private final Map<NameOfLabel,List<Stm>> table = new LinkedHashMap<NameOfLabel,List<Stm>>();

   private final List<List<Stm>> theTrace = new ArrayList<List<Stm>>();

   private TraceSchedule (final List<List<Stm>> bb) {
      //theBB=bb;
      /*
        Associate each block (Statement List) with the label that heads it.
      */
      for (List<Stm> stml: bb) {
         table.put (TraceSchedule.head(stml).label, stml);
      }
   }



   // Get last *two* statements!
   /*
   private static List<Stm> getLast (final List<Stm> block) {
      final int n = block.size();
      return block.subList (n-2,n);
   }
   private void scheduleNext (List<Stm> bb) {
      theTrace.add (bb);
   }
   */

   private void tracex (List<Stm> stmList) {

      for(;;) {
	 final tree.LABEL lab = TraceSchedule.head (stmList);
         assert table.containsKey(lab.label);
         
         theTrace.add (table.get(lab.label));  // schedule the next block
         table.remove(lab.label);

         final Stm last_stm = stmList.get (stmList.size()-1);
         if (last_stm instanceof tree.JUMP) {
            // Unconditional jump
            final tree.JUMP j = (tree.JUMP)last_stm;
            final List<Stm> target = table.get(j.targets.get(0));
            if (j.targets.size()==1 && target!=null) {
               stmList=target;
            } else {
               //this . getNext();
               this . getNext2();
               return;
            }
         } else if (last_stm instanceof tree.CJUMP) {
            // As with real machines' instructions, schedule a false trace immediately
            // after condition jump.
            final tree.CJUMP j = (tree.CJUMP)last_stm;
            assert j.iftrue!=null;
            final List<Stm> t = table.get(j.iftrue);
            assert j.iffalse!=null;
            final List<Stm> f = table.get(j.iffalse);
            if (f!=null) {
               // If there is a trace for the false branch, schedule it next
               stmList=f;
            } else if (t!=null) {
               // If there is no trace for the false branch, negate the condition
               final tree.CJUMP cj =
                  new tree.CJUMP (tree.CJUMP.notRel(j.relop), j.left, j.right, j.iffalse, j.iftrue);
               // Update/modify last of list
               stmList.set (stmList.size()-1, cj);
               stmList=t;
            } else {
               // Both already scheduled!

               // Invent a new false label to fall through to and goto the original false  label
               final tree.NameOfLabel ff = tree.NameOfLabel.generateLabel("trace", "false");
               final tree.CJUMP cj = new tree.CJUMP(j.relop, j.left, j.right, j.iftrue, ff);
               stmList.set (stmList.size()-1, cj);
               stmList.add (new LABEL (ff));            // append
               stmList.add (new tree.JUMP (j.iffalse)); // append

               //getNext();   // sort of recurse
               getNext2();   // sort of recurse
               return;

            }
         } else {
            throw new RuntimeException("Bad basic block in TraceSchedule");
         }
      }
   }


   private static tree.LABEL head (List<Stm> l) {
      assert l.get(0) instanceof tree.LABEL;
      return (tree.LABEL) (l.get(0));
   }

   // Flatten the list of lists
   private List<Stm> getSchedule() {
      final List<Stm> list = new ArrayList<Stm> ();
      for (List<Stm> l: theTrace) {
         for (Stm s: l) list.add (s);
      }
      return list;
   }

   private List<Stm> getNext2() {
      for (Map.Entry<NameOfLabel, List<Stm>> entry : table.entrySet()) {
         final List <Stm> stmList = entry.getValue();
         tracex(stmList);
         // We leave losing the iterator and avoiding concurrent modification
         // from later removes.
         return stmList;
      }
      return null;
   }

   /*
   private List<Stm> getNext() {
      if (theBB.size()==0) {
         return null;
      } else {
         final List<Stm> s = theBB.get(0);
         final tree.LABEL lab = TraceSchedule.head (s);
         if (table.containsKey(lab.label)) {
            // schedule the traces that HAVE ALREADY BEEN SCHEDULED???!!
            tracex(s);
            return s;
         } else {
            // already added lab to the trace (theTrace)
            theBB.remove(0);
            return getNext();
         }
      }
   }
   */
}

