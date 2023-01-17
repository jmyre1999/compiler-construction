/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2st edition, by Andrew W. Appel.
 */

/*
   Based on:
   https://www.cs.princeton.edu/~appel/modern/java/chap10/Graph/Graph.java
*/

package graph;

import java.util.List;
import java.util.ArrayList;

public class Graph {

   int nodecount=0;
   final List<Node> mynodes=new ArrayList<Node>();

   public List<Node> nodes() { return mynodes;} 

   public Node newNode() {
      return new Node(this);
   }

   private void check (final Node n) {
      if (n.mygraph != this)
         throw new RuntimeException("Graph.addEdge using nodes from the wrong graph");
   }

   public void addEdge (final Node from, final Node to) {
      check(from); check(to);
      if (from.goesTo(to)) return;
      // Undirected graph
      to.preds.add (0, from);
      from.succs.add (0, to);
   }


   public void removeEdge (final Node from, final Node to) {
      to.preds.remove (from);
      from.succs.remove (to);
   }

   /**
    * Print a human-readable dump for debugging.
    */
   public void show (java.io.PrintWriter out) {
      assert out!=null;
      for (final Node n: nodes()) {
         assert n!=null;
         out.format ("%s:  ", n);
         for (Node x: n.succ()) {
            out.format ("%s ", x);
         }
         out.println();
      }
   }

   public String toString () {
      final StringBuffer sb = new StringBuffer();
      for (final Node n: nodes()) {
         sb.append (n);
         sb.append (":  ");
         for (Node x: n.succ()) {
            sb.append (x);
            sb.append (" ");
         }
         sb.append (System.lineSeparator());  // line terminator!
      }
      return sb.toString();
   }
}
