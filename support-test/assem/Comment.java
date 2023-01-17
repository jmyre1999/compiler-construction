/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package assem;

/*
   Caution:  assembly dependent syntax is used here.
*/

public class Comment extends Instruction {
   public Comment ()         { super ("!"); }
   public Comment (String a) { super ("! " + a); }
}
