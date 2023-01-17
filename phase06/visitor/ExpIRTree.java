package visitor;

import tree.*;

class ExpIRTree extends LazyIRTree {
	private final tree.Exp exp;
	ExpIRTree (Exp e) { exp = e; }
	Exp asExp() { return exp; }
	Stm asStm() { return new EVAL(exp); }
	// asCond not implemented
}