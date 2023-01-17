/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package tree;

import tree.Exp;
import tree.Stm;
import tree.ExpList;

import java.util.*;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.StringWriter;

public class TreePrint extends Printer {

   public static String toString (Exp e) {
      final StringWriter sw = new StringWriter ();
      new TreePrint (new PrintWriter (sw)). print (e);
      return sw.toString();
   }

   public static String toString (Stm s) {
      final StringWriter sw = new StringWriter ();
      new TreePrint (new PrintWriter (sw)). print (s);
      return sw.toString();
   }

   public static String toString (List<Stm> l) {
      final StringWriter sw = new StringWriter ();
      new TreePrint (new PrintWriter (sw)). print (l);
      return sw.toString();
   }

   public static void print (PrintWriter pw, List<Stm> l) {
      new TreePrint (pw) . print (l);
   }

   public static void print (PrintWriter pw, Stm s) {
      new TreePrint (pw) . print (s);
   }

   public void print (final Stm s) { prStm(s,0); say("\n"); flush ();}
   public void print (final Exp e) { prExp(e,0); say("\n"); flush ();}

   public void print (final List<Stm> l) {
      for (int i=0; i<l.size(); i++) {
         say (String.format ("%02d:", i+1));
         out (3);
         prStm (l.get(i), 7);
         say ("\n");
      }
      flush ();
   }


   /*
     Map every "temp" to its name for the purposes of printing instructions out.
   */
   private final static Map<NameOfTemp,String> DEFAULT_MAP = new HashMap<NameOfTemp,String> () {
      @java.lang.Override
      public String get(Object t) {
         if (containsKey(t)) return super.get(t); else return t.toString();
      }
   };


   final Map<NameOfTemp,String> tmap;

   public TreePrint (PrintStream ps)  {this (new PrintWriter (ps), DEFAULT_MAP); }
   public TreePrint (PrintWriter pw)  {this (pw, DEFAULT_MAP); }
   public TreePrint (PrintStream ps, Map<NameOfTemp,String> t) {this (new PrintWriter (ps)); }
   public TreePrint (PrintWriter pw, Map<NameOfTemp,String> t) {super (pw); tmap=t;}

   private void prStm (Stm s, int in) {
      if (s==null)  say (in, "null stm");
      else if (s instanceof SEQ)   prStm ((SEQ)s, in);
      else if (s instanceof LABEL) prStm ((LABEL)s, in);
      else if (s instanceof JUMP)  prStm ((JUMP)s, in);
      else if (s instanceof CJUMP) prStm ((CJUMP)s, in);
      else if (s instanceof MOVE)  prStm ((MOVE)s, in);
      else if (s instanceof EVAL)  prStm ((EVAL)s, in);
      else throw new RuntimeException (s.getClass().getName());
   }

   private void prStm (SEQ s, int in) {
      sayln (in, "SEQ(");
      prStm (s.left,in+2);  sayln (",");
      prStm (s.right,in+2); say (")");
   }

   private void prStm (LABEL s, int in) {
      say(in, "LABEL "); say(s.label.toString());
   }

   private void prStm (JUMP s, int in) {
      if (s.exp instanceof NAME) {
	 say (in, "JUMP(NAME "); say (((NAME)s.exp).label.toString()); say (")");
      } else {
	 sayln (in, "JUMP("); prExp (s.exp, in+2); say(")");
      }
   }

   private void prStm (CJUMP s, int in) {
      say(in, "CJUMP("); 
      switch(s.relop) {
      case CJUMP.EQ:  say("EQ"); break;
      case CJUMP.NE:  say("NE"); break;
      case CJUMP.LT:  say("LT"); break;
      case CJUMP.GT:  say("GT"); break;
      case CJUMP.LE:  say("LE"); break;
      case CJUMP.GE:  say("GE"); break;
      case CJUMP.ULT: say("ULT"); break;
      case CJUMP.ULE: say("ULE"); break;
      case CJUMP.UGT: say("UGT"); break;
      case CJUMP.UGE: say("UGE"); break;
      default:	 throw new RuntimeException ("unknown op="+s.relop);
      }
      sayln (","); prExp (s.left, in+2); sayln (",");
      prExp (s.right, in+2); sayln (",");
      indent (in+2); say(s.iftrue.toString()); say(","); 
      say (s.iffalse.toString()); say(")");
   }

   private void prStm(MOVE s, int in) {
      final boolean b = isShort (s.dst) && isShort (s.src);
      if (b) {
         say  (in, "MOVE("); prExp(s.dst,0); say(", ");  prExp(s.src,0);
      } else {
         sayln(in, "MOVE("); prExp(s.dst,in+2); sayln(",");  prExp(s.src,in+2);
      }
      say(")");
   }

   private void prStm (EVAL s, int in) {
      sayln (in, "EVAL("); prExp (s.exp, in+2); say (")"); 
   }

   private void prExp (BINOP e, int in) {
      say (in, "BINOP("); 
      switch(e.binop) {
      case BINOP.PLUS:    say("PLUS"); break;
      case BINOP.MINUS:   say("MINUS"); break;
      case BINOP.MUL:     say("MUL"); break;
      case BINOP.DIV:     say("DIV"); break;
      case BINOP.AND:     say("AND"); break;
      case BINOP.OR:      say("OR"); break;
      case BINOP.LSHIFT:  say("LSHIFT"); break;
      case BINOP.RSHIFT:  say("RSHIFT"); break;
      case BINOP.ARSHIFT: say("ARSHIFT"); break;
      case BINOP.XOR:     say("XOR"); break;
      default:
         throw new Error("TreePrint.prExp.BINOP");
      }
      final boolean s = isShort (e.left) && isShort (e.right);
      if (s) {
         say (", ");
         prExp(e.left,0);
         say (", ");
         prExp(e.right,0);
         say(")");
      }  else {
         sayln(",");
         prExp(e.left,in+2);
         sayln(",");
         prExp(e.right,in+2);
         say(")");
      }
   }

   private boolean isShort (final Exp e) {
      if (e instanceof TEMP)  return true;
      else if (e instanceof CONST) return true;
      else return false;

   }

   private void prExp (MEM e, int in) {
      final boolean s = isShort (e.exp);
      if (s) {
         say(in, "MEM("); prExp(e.exp,0); say(")");
      } else {
         sayln(in, "MEM("); prExp(e.exp,in+2); say(")");
      }
   }

   private void prExp (TEMP e, int in) {
      say(in, "TEMP "); say (tmap.get(e.temp));
   }

   private void prExp (ESEQ e, int in) {
      sayln (in, "ESEQ(");
      prStm (e.stm, in+2); sayln (",");
      prExp (e.exp, in+2); say (")");
   }

   private void prExp (RET e, int in) {
      sayln (in, "RET(");
      prStm (e.stm, in+2); sayln (",");
      prExp (e.exp, in+2); say (")");
   }

   private void prExp (NAME e, int in) {
      say (in, "NAME "); say(e.label.toString());
   }

   private void prExp (CONST e, int in) {
      say (in, "CONST "); say(String.valueOf(e.value));
   }

   private void prExp (CALL e, int in) {
      sayln (in, "CALL(");
      prExp (e.func, in+2);
      for (ExpList a = e.args; a!=null; a=a.tail) {
	 sayln (",");
	 prExp (a.head, in+2); 
      }
      say(")");
   }

   private void prExp (Exp e, int in) {
      if (e==null)  say (in, "null exp");
      else if (e instanceof BINOP) prExp ((BINOP)e, in);
      else if (e instanceof MEM)   prExp ((MEM)e, in);
      else if (e instanceof TEMP)  prExp ((TEMP)e, in);
      else if (e instanceof ESEQ)  prExp ((ESEQ)e, in);
      else if (e instanceof RET)   prExp ((RET)e, in);
      else if (e instanceof NAME)  prExp ((NAME)e, in);
      else if (e instanceof CONST) prExp ((CONST)e, in);
      else if (e instanceof CALL)  prExp ((CALL)e, in);
      else throw new RuntimeException (e.getClass().getName());
   }
}
