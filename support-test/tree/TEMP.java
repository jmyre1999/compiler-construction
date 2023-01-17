/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import tree.Exp;
import tree.ExpList;
import java.util.List;
import java.util.Collections;

/*
  Consider renaming the class TEMP to conform with Java class naming standards
  and to permit compilation on Windows which does not like files which differ
  only by capitalization.

  I would rather introduce the class NameOfTemp and use uppercase
  consistently as the name of "instructions".
 */

/*
  This class is immutable and can be shared with impunity.
 */

public class TEMP extends Exp {
   public final NameOfTemp temp;
   public TEMP (final NameOfTemp t) {temp=t;}
   public TEMP (final String s) {this (new NameOfTemp(s)); }  // (possibly non-unique)

   @Override
   public Exp build (ExpList kids) {return this;}
   @Override
   public ExpList kids() {return null;}
   @Override
   public List<Exp>   subcomponents() { return Collections.emptyList();}
   @Override
   public Exp create (List<Exp> kids) {return this;}

   // Generate unique, new TEMP
   public static TEMP generateTEMP () {
      return new TEMP (NameOfTemp.generateTemp ());
   }
   public static TEMP generateTEMP (String s) {
      return new TEMP (NameOfTemp.generateTemp (s));
   }
}

