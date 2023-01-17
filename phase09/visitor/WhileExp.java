package visitor;

import tree.*;

class WhileExp extends LazyIRTree {

	private final LazyIRTree cond, e;
	final NameOfLabel t = NameOfLabel.generateLabel("while","do");
	final NameOfLabel f = NameOfLabel.generateLabel("while","end");

	WhileExp (LazyIRTree c, LazyIRTree doClause) {
		assert c!=null; 
		assert doClause!=null;
		cond = c; 
		e = doClause; 
	}
	
	public Exp asExp() {
		throw new UnsupportedOperationException();
	}

	public Stm asStm() {
		final Stm seq;
		seq = SEQ.fromList(
			cond.asCond(t, f),
			new LABEL(t),
			e.asStm(),
			cond.asCond(t, f),
			new LABEL(f)
		);
		return seq;
	}
}