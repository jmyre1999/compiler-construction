package visitor;

import tree.*;

class BooleanIRTree extends ExpIRTree {
	BooleanIRTree(Exp e) { super(e); }

	Stm asCond(NameOfLabel t, NameOfLabel f) {
		return (new CJUMP(CJUMP.EQ, super.asExp(), CONST.TRUE, t, f));
	}
}