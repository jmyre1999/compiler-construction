/*
 * Florida Tech, CSE3001.  Compiler project based on "Modern Compiler
 * Implementation in Java" by Andrew W. Appel.
 */

/*
 * The entire "assem" package is provided to the student to be used as
 * is in the completion of the "Program: Instruction Selection", Chapter 9,
 * page 216.
 */

package assem;

import tree.NameOfLabel;

public class LabelInstruction extends Instruction {
   public final NameOfLabel label;

   public LabelInstruction (String a, NameOfLabel l) {
      super (a);  label=l;
   }

   public LabelInstruction (NameOfLabel l) {
      super (l.toString()+":");  label=l;
   }

   @Override
   public boolean isLabel() { return true; }

}
