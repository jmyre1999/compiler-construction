/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package tree;

import tree.Stm;
import tree.ExpList;

import java.util.List;
import java.util.Collections;

public class LABEL extends Stm { 
   public final NameOfLabel label;
   public LABEL(NameOfLabel l) {label=l;}
   public LABEL(String s) {this (new NameOfLabel(s)); }
   public LABEL(String... s) {this (NameOfLabel.generateLabel(s)); }

   public static LABEL generateLABEL (String... s) {
      return new LABEL (NameOfLabel.generateLabel(s));
   }

   @Override
   public Stm build(ExpList kids) { return this; }
   @Override
   public ExpList kids() {return null;}
   @Override
   public List<Exp>   subcomponents() { return Collections.emptyList();}
   @Override
   public Stm create (List<Exp> kids) {return this;}


}

