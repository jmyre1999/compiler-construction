/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package tree;

import tree.ExpList;  // We'd like to get rid of this; see canonicalization

import java.util.List;

/*
  Concrete subclass: BINOP, CALL, CONST, ESEQ, MEM, NAME, TEMP
  Concrete subclass: BINOP, CALL, CONST, RET,  MEM, NAME, TEMP

  BINOP : Exp x Exp
  CALL  : Exp x [Exp]

  Possible renaming: BinaryExp, CallExp, ConstExp, ReturnExp, MemoryExp, NameExp, TemporaryExp
*/
public abstract class Exp {
   public int     need=0;   // Registers exp needs; used in Sethi-Ullman algo

   public Exp    build(ExpList kids) {
      throw new UnsupportedOperationException("Can't put together the Exp "+this.getClass().getName());
   }
   public ExpList kids() {
      throw new UnsupportedOperationException("Can't take apart the Exp "+this.getClass().getName());
   }

   // Replacements for build() & kids()
   public Exp     create (Exp...kids) {throw new UnsupportedOperationException();}

   // Replacements for build() & kids()
   public Exp         create (List<Exp> kids) {
      throw new UnsupportedOperationException("Can't put together the Exp "+this.getClass().getName());
   }

   public List<Exp>   subcomponents () {
      throw new UnsupportedOperationException("Can't take apart the Exp "+this.getClass().getName());
   }

   public String  toString ()         {return TreePrint.toString (this); }
}

