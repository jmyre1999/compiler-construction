package graph;

import java.util.List;
import java.util.ArrayList;

public class Node {

   final Graph mygraph;
   private final int mykey;

   public Node (Graph g) {
      mygraph = g; 
      mykey = g.nodecount++;
      g.mynodes.add (this);
   }

   final List<Node> succs = new ArrayList<Node>();
   final List<Node> preds = new ArrayList<Node>();
   public List<Node> succ() {return succs;}
   public List<Node> pred() {return preds;}

   private List<Node> cat (List<Node> a, List<Node> b) {
      if (a==null) return b;
      final List<Node> list = new ArrayList<Node>(a);
      list.addAll (b);
      return list;
   }

   public List<Node> adj() {return cat(succ(), pred());}

   public int inDegree()  { return pred().size(); }
   public int outDegree() { return succ().size(); }
   public int degree()    { return inDegree()+outDegree(); } 

   public boolean goesTo (final Node n) {
      return succs.contains(n);
   }

   public boolean comesFrom (final Node n) {
      return preds.contains(n);
   }

   public boolean adj (final Node other) {
      return this.goesTo(other) || this.comesFrom(other);
   }

   public String toString() {return String.valueOf(mykey);}

}
