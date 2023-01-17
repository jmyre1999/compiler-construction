/*
 * Florida Tech, CSE3001: Compiler Design.  Part of the compiler project
 * from "Modern Compiler Implementation in Java" by Andrew W. Appel.
 */

package tree;

import tree.Stm;
import tree.Exp;
import tree.ExpList;

public class CJUMP extends Stm {

   public final int relop;
   public final Exp left, right;
   public final NameOfLabel iftrue, iffalse;

   // Signed, and unsigned, integer comparison operations
   public final static int EQ=0, NE=1, LT=2, GT=3, LE=4, GE=5, ULT=6, ULE=7, UGT=8, UGE=9;

   public CJUMP (int rel, Exp l, Exp r, String t, String f) {
      this (rel, l, r, new NameOfLabel(t), new NameOfLabel(f));
   }
   public CJUMP (int rel, Exp l, Exp r, NameOfLabel t, NameOfLabel f) {
      relop=rel; left=l; right=r; iftrue=t; iffalse=f;
   }

   public ExpList kids() {return new ExpList(left, new ExpList(right,null));}

   public Stm build (ExpList kids) {
      return new CJUMP(relop,kids.head,kids.tail.head,iftrue,iffalse);
   }

   /* 
      Used by TraceSchedule in canonicalization to swap branches
   */
   public static int notRel (int relop) {
      switch (relop) {
      case EQ:  return NE;
      case NE:  return EQ;
      case LT:  return GE;
      case GE:  return LT;
      case GT:  return LE;
      case LE:  return GT;
      case ULT: return UGE;
      case UGE: return ULT;
      case UGT: return ULE;
      case ULE: return UGT;
      default: throw new IllegalArgumentException ("bad relop in CJUMP.notRel");
      }
   }
}

