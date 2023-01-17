import java.io.IOException;
import java.util.HashMap;

class Interp {

   static HashMap<String, Integer> map = new HashMap<String, Integer>();

   static int max = 0;

   /* Interpreter */

   public static void interp(Stm s) {
      if(s instanceof CompoundStm) {
         compoundStmHandler((CompoundStm)s);
      }
      else if(s instanceof AssignStm) {
         assignmentStmHandler((AssignStm)s);
      }
      else if(s instanceof PrintStm) {
         printStmHandler((PrintStm)s);
      }
   }

   public static void compoundStmHandler(CompoundStm s) {
      interp(s.stm1);
      interp(s.stm2);
   }

   public static void assignmentStmHandler(AssignStm s) {
      map.put(s.id, interpExp(s.exp));
   }

   public static void printStmHandler(PrintStm s) {
      printExpListHandler("", s.exps);
   }

   public static void printExpListHandler(String line, ExpList exps) {
      if(exps instanceof PairExpList) {
         pairExpListPrinter(line, (PairExpList)exps);
      }
      else if(exps instanceof LastExpList) {
         lastExpListPrinter(line, (LastExpList)exps);
      }
   }

   public static void pairExpListPrinter(String line, PairExpList exps) {
      line = line + Integer.toString(interpExp(exps.head)) + " ";
      printExpListHandler(line, exps.tail);
   }

   public static void lastExpListPrinter(String line, LastExpList exps) {
      line = line + Integer.toString(interpExp(exps.head)) + " ";
      System.out.println(line);
   }

   public static int interpExp(Exp e) {
      if(e instanceof IdExp) {
         return idExpHandler((IdExp)e);
      }
      else if(e instanceof NumExp) {
         return numExpHandler((NumExp)e);
      }
      else if(e instanceof OpExp) {
         return opExpHandler((OpExp)e);
      }
      else if(e instanceof EseqExp) {
         return eseqExpHandler((EseqExp)e);
      }
      return -1;
   }

   public static int idExpHandler(IdExp e) {
      return map.get(e.id);
   }

   public static int numExpHandler(NumExp e) {
      return e.num;
   }

   public static int opExpHandler(OpExp e) {
      if(e.oper == OpExp.Plus) {
         return (interpExp(e.left) + interpExp(e.right));
      }
      else if(e.oper == OpExp.Minus) {
         return (interpExp(e.left) - interpExp(e.right));
      }
      else if(e.oper == OpExp.Times) {
         return (interpExp(e.left) * interpExp(e.right));
      }
      else if(e.oper == OpExp.Div) {
         return (interpExp(e.left) / interpExp(e.right));
      }
      return -1;
   }

   public static int eseqExpHandler(EseqExp e) {
      interp(e.stm);
      return interpExp(e.exp);
   }

   /* Max Args */

   public static int maxargs(Stm s) {
      if(s instanceof CompoundStm) {
         return compoundStmMaxHandler((CompoundStm)s);
      }
      else if(s instanceof AssignStm) {
         return assignmentStmMaxHandler((AssignStm)s);
      }
      else if(s instanceof PrintStm) {
         return printStmMaxHandler((PrintStm)s);
      }
      return 0;
   }

   public static int compoundStmMaxHandler(CompoundStm s) {
      int x = maxargs(s.stm1);
      int y = maxargs(s.stm2);
      if(x > y) {
         return x;
      }
      return y;
   }

   public static int assignmentStmMaxHandler(AssignStm s) {
      return maxargsExp(s.exp);
   }

   public static int printStmMaxHandler(PrintStm s) {
      return printExpListMaxHandler(s.exps);
   }

   public static int printExpListMaxHandler(ExpList exps) {
      if(exps instanceof LastExpList) {
         return 1;
      }
      PairExpList pair_exps = (PairExpList)exps;
      LastExpList last_exps;
      ExpList tail = pair_exps.tail;
      Exp head;
      int count = 2;
      int internal_max = 0;
      int temp;

      while(tail instanceof PairExpList) {
         pair_exps = (PairExpList)tail;
         count = count + 1;
         head = pair_exps.head;
         temp = maxargsExp(head);
         if(temp > internal_max){
            internal_max = temp;
         }
         tail = pair_exps.tail;
      }

      last_exps = (LastExpList)tail;
      temp = maxargsExp(last_exps.head);
      if(temp > internal_max){
         internal_max = temp;
      }

      if(internal_max > count) {
         return internal_max;
      }
      return count;
   }

   public static int maxargsExp(Exp e) {
      if(e instanceof OpExp) {
         return opExpMaxHandler((OpExp)e);
      }
      else if(e instanceof EseqExp) {
         return eseqExpMaxHandler((EseqExp)e);
      }
      return 0;
   }

   public static int opExpMaxHandler(OpExp e) {
      int x = maxargsExp(e.left);
      int y = maxargsExp(e.right);
      if(x > y) {
         return x;
      }
      return y;
   }

   public static int eseqExpMaxHandler(EseqExp e) {
      int x = maxargs(e.stm);
      int y = maxargsExp(e.exp);
      if(x > y) {
         return x;
      }
      return y;
   }

   /* Main */

   public static void main (Stm s) throws IOException {
      System.out.println (maxargs(s));
      interp(s);
   }

   public static void main(String args[]) throws IOException {
      main (Example.a_program);
      main (Example.another_program);
   }
}