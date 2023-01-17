package visitor;

import tree.*;

class CondIRTree extends LazyIRTree {
	int op;
	Exp left,right;

	CondIRTree(Exp l, Exp r, int o) { 
		op = o; 
		left = l;
		right = r;
	}

	Exp asExp() {
		TEMP temp = TEMP.generateTEMP();
		NameOfLabel t = NameOfLabel.generateLabel("cond","true");
		NameOfLabel f = NameOfLabel.generateLabel("cond","false");
		NameOfLabel end = NameOfLabel.generateLabel("cond","end");
		RET ret = new RET(
			SEQ.fromList(
				asCond(t, f),
				new LABEL(t),
				new MOVE(temp, CONST.TRUE),
				new JUMP(end),
				new LABEL(f),
				new MOVE(temp, CONST.FALSE),
				new LABEL(end)
			),
			temp
		);
		return ret;
	}

	Stm asStm() {
		return (new SEQ(new EVAL(left), new EVAL(right)));
	}

	Stm asCond(NameOfLabel t, NameOfLabel f) {
		return (new CJUMP(op, left, right, t, f));
	}
}