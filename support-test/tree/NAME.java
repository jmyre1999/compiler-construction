/*
 * CSE3001.  Compiler project based on "Modern Compiler Implementation
 * in Java" by Andrew W. Appel.
 *
 */

package tree;

import tree.Exp;
import tree.ExpList;

import java.util.List;

/*
  NAME (n) -- Intermediate representation tree corresponding to
  an assembly language label.

  Better names?  LabelAsExp
*/

public class NAME extends Exp {
   public final NameOfLabel label;
   public NAME (NameOfLabel l) {label=l;}
   public NAME (String s) {this (new NameOfLabel (s)); }
   public ExpList kids() {return null;}
   public Exp build(ExpList kids) {return this;}

   @Override
   public List<Exp> subcomponents () {
      return null;
   }
   @Override
   public Exp create(final List<Exp> kids) {
      return this;
   }

}

