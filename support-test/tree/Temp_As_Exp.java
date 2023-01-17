/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import tree.Exp;
import tree.ExpList;
import java.util.List;

/*
  A renaming of the class TEMP to conform with Java class naming standards
  and to permit compilation on Windows which does not like files which differ
  only by capitalization.

  I would rather introduce the class NameOfTemp and use uppercase any way
  consistently as the name of "instructions".
 */

/*
  Immutable, can be shared with impunity
 */

@Deprecated
public class Temp_As_Exp extends Exp {
   public final NameOfTemp temp;
   public Temp_As_Exp (NameOfTemp t) {temp=t;}
   private Temp_As_Exp (String s) {this (new NameOfTemp(s)); }
   public List<Exp> subexpressions() {return null;}
   public ExpList kids() {return null;}
   public Exp build (ExpList kids) {return this;}
   public Exp build (List<Exp> kids) {return this;}

   // Generate unique, new Temp_As_Exp
   public static Temp_As_Exp generateTEMP () {
      return new Temp_As_Exp (NameOfTemp.generateTemp ());
   }
   public static Temp_As_Exp generateTEMP (String s) {
      return new Temp_As_Exp (NameOfTemp.generateTemp (s));
   }
}

