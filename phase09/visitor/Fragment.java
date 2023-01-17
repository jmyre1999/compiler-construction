package visitor;

import tree.*;
import sparc.Frame;

public class Fragment {
	public Stm method_body = null;
	public Frame method_frame = null;

	public Fragment(Stm b, Frame f) {
		method_body = b;
		method_frame = f;
	}
}