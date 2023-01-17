package visitor;

import tree.*;

abstract class LazyIRTree {

	abstract Exp asExp();
	abstract Stm asStm();

	Stm asCond(NameOfLabel t, NameOfLabel f) {
		throw new UnsupportedOperationException();
	}

	public String toString () {
		return String.format ("IR: %s", asStm().toString());
	}

}