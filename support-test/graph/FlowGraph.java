/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */

package graph;

import graph.Graph;
import graph.Node;
import java.util.Set;
import java.util.List;

/**
 * A control flow graph is a directed graph in which each edge
 * indicates a possible flow of control.  Also, each node in
 * the graph defines a set of temporaries; each node uses a set of
 * temporaries; and each node is, or is not, a <strong>move</strong>
 * instruction.
 *
 * See Appel 2nd, Chapter 10, Section 2, page 215
 *
 * @see AssemFlowGraph
 */

public abstract class FlowGraph <T> extends Graph {
   /**
    * The set of temporaries defined by this instruction or block 
    */
   public abstract Set<T> def (final Node node);

   /**
    * The set of temporaries used by this instruction or block 
    */
   public abstract Set<T> use (final Node node);

   /**
    * True if this node represents a <strong>move</strong> instruction,
    * i.e. one that can be deleted if def=use. 
    */
   public abstract boolean isMove (Node node);

   /**
    * Print a human-readable dump for debugging.
    */
   public void show (java.io.PrintStream out) {
      for (Node n: mynodes) {
         out.format ("%s: ", n.toString());
         for (T t: def(n)) {
            out.format (" %s", t.toString());
         }
         out.print(isMove(n) ? "<= " : "<- ");
         for (T t: use(n)) {
            out.format (" %s", t.toString());
         }
         out.print("; goto ");
         for (Node x: n.succ()) {
            out.format (" %s", x.toString());
         }
         out.println();
      }
   }
}
    
    
