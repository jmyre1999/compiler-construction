package graph;

import graph.Node;
import graph.Graph;
import java.util.*;

abstract public class AbstractInterferenceGraph <T> extends Graph {

   Map<T, Node> nodes = new HashMap<>();
   Map<Node, T> temps = new HashMap<>();

   public boolean hasNode (T temp) { return nodes.containsKey(temp); }
   public Node ensureNode (T temp) {
      // This temp is in the graph already, return its corresponding graph 'Node'.
      if (nodes.containsKey(temp)) {
         return nodes.get(temp);
      } else {
         // Put this temp into the graph, assign it a node, (insert it into
         // the graph), and return the newly created node.
         final Node n = this.newNode();
         nodes.put (temp, n);
         temps.put (n, temp);
         return n;
      }
   }
   public Node getNode (T temp)    { return nodes.get(temp); }
   public T    getTemp (Node node) { return temps.get(node); }

   /* "Categorize each node as either move-related or non-move-related.
       A move-related node is one that is either the source or destination
       of a move instruction."  Appel, 2nd, page 224. */
   public boolean isMove (final Node node) {
      return false;
   }

   public int spillCost (Node node) {return 1;}
}
