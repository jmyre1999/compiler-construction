// Needs to be replace by List<Exp>

package tree;
import java.util.List;
import java.util.LinkedList;

public class ExpList {
   public final Exp head;
   public final ExpList tail;
   public ExpList(Exp h, ExpList t) {head=h; tail=t;}

   public static ExpList fromList (List <Exp> l) {
      ExpList tail = null;
      for (int i=l.size()-1; i>=0; i--) {
         tail = new ExpList (l.get(i), tail);
      }
      return tail;
   }

   public static ExpList toExpList (Exp... l) {
      if (l.length==0) {
         return null;
      } else if (l.length==1) {
         return new ExpList (l[0], null);
      } else if (l.length==2) {
         return new ExpList (l[0], new ExpList (l[1], null));
      } else if (l.length==3) {
         return new ExpList (l[0], new ExpList (l[1], new ExpList (l[2], null)));
      } else {
         throw new IllegalArgumentException (String.format ("len=%d", l.length));
      }
   }

   public LinkedList<Exp> toList() {
      final LinkedList<Exp> ll = tail==null ? new LinkedList<Exp> (): tail.toList();
      ll.addFirst(head);
      return ll;
   }

}
