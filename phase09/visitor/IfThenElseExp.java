package visitor;

import tree.*;

class IfThenElseExp extends LazyIRTree {

	private final LazyIRTree cond, e2, e3;
	final NameOfLabel t    = NameOfLabel.generateLabel("if","then");
	final NameOfLabel f    = NameOfLabel.generateLabel("if","else");
	final NameOfLabel join = NameOfLabel.generateLabel("if","end");

	IfThenElseExp (LazyIRTree c, LazyIRTree thenClause, LazyIRTree elseClause) {
		assert c!=null; 
		assert thenClause!=null;
		cond = c; 
		e2 = thenClause; 
		e3 = elseClause;
	}
	
	public Exp asExp() {
		throw new UnsupportedOperationException();
	}

	public Stm asStm() {
		final Stm seq;
		if (e3 == null) {
			seq = SEQ.fromList(
				cond.asCond(t, f),
				new LABEL(t),
				e2.asStm(),
				new LABEL(f)
			);
		} else {
			seq = SEQ.fromList(
				cond.asCond(t, f),
				new LABEL(t),
				e2.asStm(),
				new JUMP(join),
				new LABEL(f),
				e3.asStm(),
				new LABEL(join)
			);
		}
		return seq;
	}
}