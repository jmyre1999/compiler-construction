/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

/*
   This class consolidates into a single static function "transform" the
   three steps in canonicalizing a tree.Stm into a flat list of tree.Stm.
*/

/*
  The "canon" package depends on the given package "tree" for all the intermediate
  representation code.

  The "canon" package no longer depends on the package "temp" (which is no longer
  needed) for the classes and constructors Label(String) and Temp(String).  The
  classes Label and Temp are now in the package "tree".
*/

package canon;

import tree.Stm;
import tree.TreePrint;

import java.util.List;

public class Main {

   /*
     In general the input 'Stm' should begin with a label and end with a
     jump, as in the following template:
         LABEL endOfPrelude
         CODE
         JUMP epilog

     Because the basic blocks may be rearranged, one cannot count on the
     "falling through" to the original first statement, instead one must
     jump to the "endOfPrelude" label.  Likewise the jump to "epilog" ensures
     that the (rearranged) code will still jump to "epilog" and so the epilog
     will need the label "epilog" to be added later.

     However, the algorithm does seem to guarantee that the first block (and
     hence the first statement) of the input will be the first block of the
     trace.

     The transform does add a beginning label and an ending jump if necessary,
     but one has no control on the choice of labels, so it is better do add
     them first.
   */

   public static List<Stm> transform (Stm s) {
      final List<Stm> stms = Canon.linearize (s);
      final List<List<Stm>> bb = BasicBlocks.makeBlocks (stms);
      final List<Stm> traces = TraceSchedule.trace(bb);
      return traces;
    }
}