/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 1st edition, by Andrew W. Appel.
 */

package canon;

import tree.*;
import java.util.List;
import java.util.ArrayList;

public class BasicBlocks {

   private static Stm jumpTo (Stm s) {
      return new JUMP (((LABEL) s).label);
   }

   // BB: first statement is LABEL, last statement is [C]JUMP
   public static List<List<Stm>> makeBlocks (final List<Stm> stml) {

      final List <List<Stm>> blocks = new ArrayList<List<Stm>>();
      List<Stm> currentBlock = null;
      for (Stm s: stml) {
         if (s instanceof LABEL) {
            // In building this block, have come to a label before
            // getting to a [C]Jump.
            if (currentBlock != null) {
               assert currentBlock.size()>0;
               // Need to finish previous block with unconditional jump to next label
               currentBlock.add (jumpTo (s));
               blocks.add (currentBlock);
            }

            // The label is the beginning of a new block.
            // (There is no guarantee that
            // the label is in fact used in the statement list.)
            currentBlock = new ArrayList<Stm>();
            currentBlock.add (s);

         } else if (s instanceof JUMP || s instanceof CJUMP) {
            if (currentBlock == null) {
               // Need to insert a new unique label first
               currentBlock = new ArrayList<Stm>();
               currentBlock.add (LABEL.generateLABEL("BB", "begin"));
            }
            currentBlock.add (s);
            blocks.add (currentBlock);
            currentBlock = null;
         } else {
            if (currentBlock == null) {
               // Need to insert a new unique label first
               currentBlock = new ArrayList<Stm>();
               currentBlock.add (LABEL.generateLABEL("BB", "begin"));
            }
            currentBlock.add (s);
         }
      }
      if (currentBlock != null) {
         // block does not end with a [C]JUMP
         currentBlock.add (new JUMP (NameOfLabel.generateLabel("BB", "nowhere")));
         blocks.add (currentBlock);
      }
   
      return blocks;
   }

   public static void print (List<List<Stm>> stms) {
      int i=1;
      for (List<Stm> sl: stms) {
         System.out.format ("--Block %d--%n", i);
         TreePrint.print (new java.io.PrintWriter (System.out), sl);
         i++;
      }
   }
}
