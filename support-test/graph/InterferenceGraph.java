package graph;

import graph.Node;
import graph.Graph;
import tree.NameOfTemp;
import java.util.List;

/*
  Undirected graph.
*/

public class InterferenceGraph extends AbstractInterferenceGraph<NameOfTemp> {

   public boolean interfersWith (final NameOfTemp t1, final NameOfTemp t2) {
      final boolean b = this.getNode (t1).adj ( this.getNode(t2));
      assert b == this.getNode (t2).adj ( this.getNode(t1));
      return b;
   }

   public int spillCost (Node node) {return 1;}
}
