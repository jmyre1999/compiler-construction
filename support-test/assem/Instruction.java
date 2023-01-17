/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

/*
 * The entire "assem" package is provided to the student to be used as
 * is in the completion of the "Program: Instruction Selection", Chapter 9
 * page 216.
 */

/*
 * The "assem" package provides abstract assembly language instructions;
 * these instructions are independent of any map of registers to temps.
 */

package assem;

import tree.NameOfTemp;
import tree.NameOfLabel;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/*
  The concrete subclasses are now: Comment, LabelInstruction, MoveInstruction, OperationInstruction
*/

public abstract class Instruction {

   // A concrete subclass should override these three methods
   public List<NameOfTemp>  use()   { return null;}
   public List<NameOfTemp>  def()   { return null;}
   public List<NameOfLabel> jumps() { return null;}

   public boolean uses   (final NameOfTemp t)   { return this.use()!=null && this.use().contains(t); }
   public boolean defines(final NameOfTemp t)   { return this.def()!=null && this.def().contains(t); }

   public Set<NameOfTemp>   temps() {
      final Set<NameOfTemp> set = new HashSet<NameOfTemp>();
      if (use()!=null) set.addAll (use());
      if (def()!=null) set.addAll (def());
      return set;
   }

   public static Set<NameOfTemp> allTemps (final List<Instruction> list) {
      final Set<NameOfTemp> set = new HashSet<NameOfTemp>();
      for (Instruction i: list) {
         if (i.use()!=null) set.addAll (i.use());
         if (i.def()!=null) set.addAll (i.def());
      }
      return set;
   }

   public final String assem;
   public final String comment;

   protected Instruction (final String a) { this (a, null); }

   protected Instruction (final String a, final String comment) {
      assem=a; this.comment = comment;
   }

   // Default implementations
   public boolean isMove()  { return false; }
   public boolean isLabel() { return false; }
   public boolean isGOTO()  { return false; }

   public String toString ()  {
      if (comment==null) return assem;
      else return String.format ("%s\t#%s", assem, comment);
   }

   /*
     Map every "temp" to its name for the purposes of printing instructions out.
   */
   public final static Map<NameOfTemp,String> DEFAULT_MAP = new HashMap<NameOfTemp,String> () {
      @java.lang.Override
      public String get(Object t) {
         if (containsKey(t)) return super.get(t); else return t.toString();
      }
   };

   public String format () {
      return format (DEFAULT_MAP);
   }

   // Format this instruction given a mapping of the temps
   public <T> String format (final Map<NameOfTemp,T> map) {
      if (map==null) return this.toString();
      final List<NameOfTemp>  src = use();
      final List<NameOfTemp>  dst = def();
      final List<NameOfLabel> jump = jumps();
      final StringBuilder s = new StringBuilder();
      final int len = assem.length();
      for (int i=0; i<len; i++)
	 if (assem.charAt(i)=='`') {
	    int n;
	    switch(assem.charAt(++i)) {
	    case 's':
	       n = Character.digit(assem.charAt(++i),10);
               if (n>=src.size()) {
                  final String message = String.format ("The src list (len=%d) for the instr '%s' is too short.", src.size(), assem);
                  throw new RuntimeException (message);
               }
	       s.append (map.get (src.get(n)));  // calls toString()
	       break;

	    case 'd':
	       n = Character.digit(assem.charAt(++i),10);
               if (n>=dst.size()) {
                  final String message = String.format ("The dst list (len=%d) for the instr '%s' is too short.", dst.size(), assem);
                  throw new RuntimeException (message);
               }
	       s.append (map.get (dst.get(n)));   // calls toString()
	       break;

	    case 'j':
	       n = Character.digit(assem.charAt(++i),10);
               if (n>=jump.size()) {
                  final String message = String.format ("The jump list (len=%d) for the instr '%s' is too short.", jump.size(), assem);
                  throw new RuntimeException (message);
               }
	       s.append (jump.get(n)).toString();  // calls toString()
	       break;

	    case '`':
	       s.append('`'); 
	       break;

	    default:
	       throw new RuntimeException ("bad instruction format:" + assem);
	    }
	 } else if (assem.charAt(i)=='\t') {
            // This does guarantee a space
            do {
               s.append(' ');
            } while (s.length()%8!=0);
	 } else {
	    s.append(assem.charAt(i));
	 }

      // append comment
      if (comment!=null) {
         do {
            s.append(' ');
         } while (s.length()<32);

         s.append ("! ");  // assembler dependent
         s.append (comment);
      }
      return s.toString();
   }

}
