/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

/*
 * The entire "assem" package is provided to the student to be used as
 * is in the completion of the "Program: Instruction Selection", Chapter 9,
 * page 216.
 */

package assem;

import tree.NameOfTemp;
import tree.NameOfLabel;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class OperationInstruction extends Instruction {

   private final List<NameOfTemp> dst, src; // List of tree.NameOfTemp
   private final List<NameOfLabel> jump;    // List of tree.NameOfLabel;

   public OperationInstruction (String a, List<NameOfTemp> d, List<NameOfTemp> s, List<NameOfLabel> j) {
      super (a); dst=d; src=s; jump=j;
   }

   public OperationInstruction (String a, String comment, List<NameOfTemp> d, List<NameOfTemp> s, List<NameOfLabel> j) {
      super (a, comment); dst=d; src=s; jump=j;
   }

   public OperationInstruction (String a, String comment, List<NameOfTemp> d, List<NameOfTemp> s) {
      this (a, comment, d, s, null);
   }

   public OperationInstruction (String a, List<NameOfTemp> d, List<NameOfTemp> s) {
      this (a, d, s, null);
   }

   public OperationInstruction (String a, NameOfTemp d, NameOfTemp s1, NameOfTemp s2) {
      this (a, Collections.singletonList (d), Arrays.asList (s1, s2));
   }

   public OperationInstruction (String a, String comment, NameOfTemp d, NameOfTemp s1, NameOfTemp s2) {
      //      this (a, comment, Collections.singletonList (d), new NameOfTemp [] {s1, s2});
      this (a, comment, Collections.singletonList (d), Arrays.asList (s1, s2));
   }

   public OperationInstruction (String a, NameOfTemp d, NameOfTemp s) {
      this (a, Collections.singletonList (d), Collections.singletonList (s), null);
   }

   public OperationInstruction (String a, String comment, NameOfTemp d, NameOfTemp s) {
      this (a, comment, Collections.singletonList (d), Collections.singletonList (s), null);
   }

   public OperationInstruction (final String a, final NameOfTemp d) {
      this (a, Collections.singletonList (d), Collections.<NameOfTemp>emptyList(), null);
   }

   public OperationInstruction (final String a, final String comment, final NameOfTemp d) {
      this (a, comment, Collections.singletonList (d), Collections.<NameOfTemp>emptyList(), null);
   }

   public OperationInstruction (final String a) {
      this (a, Collections.<NameOfTemp>emptyList(), Collections.<NameOfTemp>emptyList(), null);
   }

   public OperationInstruction (final String a, final String comment) {
      this (a, comment, Collections.<NameOfTemp>emptyList(), Collections.<NameOfTemp>emptyList(), null);
   }

   public List<NameOfTemp>  use()   {return src==null?null:Collections.unmodifiableList(src);}
   public List<NameOfTemp>  def()   {return dst==null?null:Collections.unmodifiableList(dst);}
   public List<NameOfLabel> jumps() {return jump==null?null:Collections.unmodifiableList(jump);}
}
