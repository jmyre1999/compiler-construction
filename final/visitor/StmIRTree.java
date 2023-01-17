package visitor;

import tree.*;

class StmIRTree extends LazyIRTree {
	private final Stm stm;
	StmIRTree (Stm s) { stm = s; }
	Stm asStm() { return stm; }
	Exp asExp() { return new CONST(0); }
}