package graph;

import tree.NameOfTemp;
import tree.NameOfLabel;
import assem.Instruction;

import java.util.*;

public abstract class AbstractAssemFlowGraph extends FlowGraph <NameOfTemp> {

   public Set<NameOfTemp> def (final Node node) {
      if (instruction(node).def()==null) {
         // "null" means none
         return Collections.emptySet(); // Immutable
      } else {
         return Collections.unmodifiableSet (new HashSet<> (instruction (node) . def ()));
      }
   }
   public Set<NameOfTemp> use (final Node node) {
      if (instruction(node).use()==null) {
         // "null" means none
         return Collections.emptySet(); // Immutable
      } else {
         return Collections.unmodifiableSet (new HashSet<> (instruction (node) . use ()));
      }
   }
   public boolean isMove (final Node node) {
      return instruction (node). isMove ();
   }
   public abstract Instruction instruction (Node n);
}