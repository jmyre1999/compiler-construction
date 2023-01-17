package sparc;

import tree.*;
import assem.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class CodeGen {
	Frame frame;
	public HashMap<Integer, String> binopInstructions = new HashMap<Integer, String>();
	public HashMap<Integer, String> binopOperators = new HashMap<Integer, String>();
	public HashMap<Integer, String> conditionalBranches = new HashMap<Integer, String>();
	public HashMap<NameOfTemp, String> tempRegisters = new HashMap<NameOfTemp, String>();
	private int locals_registered = 0;
	private int inputs_registered = 0;
	private int globals_registered = 1; // %g0,%g6,%g7 reserved 
	private int total_temps = 0;

	public CodeGen(Frame f) {
		frame = f;
		// Add binop instructions
		binopInstructions.put(BINOP.PLUS, "ADD");
		binopInstructions.put(BINOP.MINUS, "SUB");
		binopInstructions.put(BINOP.AND, "AND");
		binopInstructions.put(BINOP.OR, "OR");
		binopInstructions.put(BINOP.MUL, "SMUL");
		// Add binop operators
		binopOperators.put(BINOP.PLUS, "+");
		binopOperators.put(BINOP.MINUS, "-");
		// Add conditional branches
		conditionalBranches.put(CJUMP.EQ, "BE");
		conditionalBranches.put(CJUMP.LT, "BL");
	}

	private List<Instruction> inst_list;

	public void emit(Instruction inst) {
		inst_list.add(inst);
	}

	public void premit(Instruction inst) {
		inst_list.add(0, inst);
	}


	public List<Instruction> generate(List<Stm> traces) {
		inst_list = new ArrayList<Instruction>();
		for(Stm trace : traces) {
			munchStm(trace);
		}
		mapTemps();
		// Prologue (prepend in reverse order)
		if(frame.method_id != "main") {
			premit(new OperationInstruction("\tsave %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp"));
			premit(new OperationInstruction("\t.set ARGSB,0"));
			premit(new OperationInstruction("\t.set TEMPS," + Integer.toString(total_temps)));
			premit(new OperationInstruction("\t.set LOCLS," + Integer.toString(frame.localMems.size())));
		}
		premit(new LabelInstruction(new NameOfLabel(frame.class_id + "$" + frame.method_id)));
		if(frame.method_id == "main") {
			premit(new LabelInstruction(new NameOfLabel("start")));
			premit(new OperationInstruction("\t.global start"));
		}
		// Epilouge
		emit(new LabelInstruction(new NameOfLabel(frame.class_id + "$" + frame.method_id + "$epilogBegin")));
		if(frame.method_id == "main") {
			emit(new OperationInstruction("\tclr %o0"));
			emit(new OperationInstruction("\tcall exit"));
			emit(new OperationInstruction("\tnop"));
		}
		else {
			emit(new OperationInstruction("\tret"));
			emit(new OperationInstruction("\trestore"));
		}

		return inst_list;
	}

	public void mapTemps() {
		// Check number of parameters in method frame, +1 for this pointer in %i0
		inputs_registered = frame.paramTemps.size() + 1;
		// Map temps
		OperationInstruction op_inst;
		MoveInstruction mov_inst;
		List<NameOfTemp> temps;
		for(Instruction inst : inst_list) {
			if(inst instanceof OperationInstruction) {
				op_inst = (OperationInstruction)inst;
				temps = op_inst.use();
				if (temps != null) {
					for (NameOfTemp temp : temps) {
						if (temp != null) {
							mapToRegister(temp);
						}
					}
				}
				temps = op_inst.def();
				if (temps != null) {
					for (NameOfTemp temp : temps) {
						if (temp != null) {
							mapToRegister(temp);
						}
					}
				}
			}
			else if (inst instanceof MoveInstruction) {
				//System.out.println("MoveInstruction");
				mov_inst = (MoveInstruction)inst;
				//System.out.println("Call use");
				temps = mov_inst.use();
				//System.out.println("Check if null");
				if (temps != null) {
					//System.out.println("Loop sources");
					for (NameOfTemp temp : temps) {
						//System.out.println("Get first char");
						if (temp != null) {
							mapToRegister(temp);
						}
					}
				}
				//System.out.println("Call def");
				temps = mov_inst.def();
				//System.out.println("Check if null");
				if (temps != null) {
					//System.out.println("Loop destinations");
					for (NameOfTemp temp : temps) {
						//System.out.println("Get first char");
						if (temp != null) {
							mapToRegister(temp);
						}
					}
				}
			}
		}
		
	}

	private void mapToRegister(NameOfTemp temp) {
		//System.out.println("Mapping '" + temp.toString() + "'");
		if(!tempRegisters.containsKey(temp)) {
			if (!temp.toString().contains("%")) {			
				if (locals_registered <= 7) {
					//System.out.println("Mapping temp " + temp.toString() + " to %l" + Integer.toString(locals_registered));
					tempRegisters.put(temp, "%l" + Integer.toString(locals_registered));
					locals_registered++;
					total_temps++;
				}
				else if (inputs_registered <= 5) {
					//System.out.println("Mapping temp " + temp.toString() + "to %i" + Integer.toString(inputs_registered));
					tempRegisters.put(temp, "%i" + Integer.toString(inputs_registered));
					inputs_registered++;
					total_temps++;
				}
				else if (globals_registered <= 5) {
					//System.out.println("Mapping temp " + temp.toString() + "to %g" + Integer.toString(globals_registered));
					tempRegisters.put(temp, "%g" + Integer.toString(globals_registered));
					globals_registered++;
					total_temps++;
				}
			}
			else {
				tempRegisters.put(temp, temp.toString());
			}
		}
		return;
	}

	void munchStm(Stm s) {
		List<NameOfTemp> left_temp_list = new ArrayList<NameOfTemp>();
		List<NameOfTemp> right_temp_list = new ArrayList<NameOfTemp>();
		if (s instanceof SEQ) {
			SEQ seq = (SEQ)s;
			munchStm(seq.left);
			munchStm(seq.right);
		}
		else if (s instanceof MOVE) {
			MOVE m = (MOVE)s;
			/*
				munchStm(MOVE(MEM(BINOP(PLUS,e1,CONST(i))),e2)) 
				munchStm(MOVE(MEM(BINOP(PLUS,CONST(i),e1)),e2)) 
				munchStm(MOVE(MEM(e1),MEM(e2))) 
				munchStm(MOVE(MEM(CONST(i)),e2))
				munchStm(MOVE(MEM(e1),e2)) 
			*/
			if (m.dst instanceof MEM) {
				MEM dst_mem = (MEM)m.dst;
				/*
					munchStm(MOVE(MEM(BINOP(PLUS,e1,CONST(i))),e2)) 
					munchStm(MOVE(MEM(BINOP(PLUS,CONST(i),e1)),e2)) 
				*/
				if (dst_mem.exp instanceof BINOP) {
					BINOP dst_binop = (BINOP)dst_mem.exp;
					if (binopOperators.containsKey(dst_binop.binop)) {
						String binop_op = binopOperators.get(dst_binop.binop);
						Exp src_exp = m.src;
						/*
							munchStm(MOVE(MEM(BINOP(PLUS,e1,CONST(i))),e2)) 
						*/
						if(dst_binop.right instanceof CONST) {
							CONST right_const = (CONST)dst_binop.right;
							Exp left_exp = dst_binop.left;
							emit(new MoveInstruction("\tST `s0, [`d0" + binop_op + Integer.toString(right_const.value) + "]",munchExp(left_exp), munchExp(src_exp)));
						}
						/*
							munchStm(MOVE(MEM(BINOP(PLUS,CONST(i),e1)),e2)) 
						*/
						else if(dst_binop.left instanceof CONST) {
							CONST left_const = (CONST)dst_binop.left;
							Exp right_exp = dst_binop.right;
							emit(new MoveInstruction("\tST `s0, [`d0" + binop_op + Integer.toString(left_const.value) + "]",munchExp(right_exp), munchExp(src_exp)));
						}
						else {
							Exp dst_mem_exp = dst_mem.exp;
							emit(new MoveInstruction("\tST `s0, [`d0]", munchExp(dst_mem_exp), munchExp(src_exp))); 
						}
					}
				}
				/*
					munchStm(MOVE(MEM(e1),MEM(e2))) 
				*/
				else if (m.src instanceof MEM) {
					MEM src_mem = (MEM)m.src;
					Exp dst_mem_exp = dst_mem.exp;
					Exp src_mem_exp = src_mem.exp;
					emit(new MoveInstruction("\tMOV [`s0], [`d0]", munchExp(dst_mem_exp), munchExp(src_mem_exp)));
				}
				/*
					munchStm(MOVE(MEM(CONST(i)),e2))
				*/
				/*else if (dst_mem.exp instanceof CONST) {
					CONST dst_mem_const = (CONST)dst_mem.exp;
					Exp src_exp = m.src;
					emit(new MoveInstruction("\tST `s0, [r0+" + Integer.toString(dst_mem_const.value) + "]", null, munchExp(src_exp))); 
				}*/
				/*
					munchStm(MOVE(MEM(e1),e2))
				*/
				else {
					Exp dst_mem_exp = dst_mem.exp;
					Exp src_exp = m.src;
					emit(new MoveInstruction("\tST `s0, [`d0]", munchExp(dst_mem_exp), munchExp(src_exp))); 
				}
			}
			/*
				munchStm(MOVE(TEMP(i), e2))
			*/
			else if (m.dst instanceof TEMP) {
				TEMP dst_temp = (TEMP)m.dst;
				Exp src_exp = m.src;
				emit(new MoveInstruction("\tMOV `s0,`d0", dst_temp.temp, munchExp(src_exp)));
			}
		}
		else if (s instanceof LABEL) {
			LABEL l = (LABEL)s;
			emit(new LabelInstruction(l.label.toString() + ":", l.label));
		}
		else if (s instanceof EVAL) {
			EVAL eval = (EVAL)s;
			munchExp(eval.exp);
		}
		else if (s instanceof CJUMP) {
			CJUMP cjump = (CJUMP)s;
			right_temp_list.add(munchExp(cjump.left));
			right_temp_list.add(munchExp(cjump.right));
			emit(new OperationInstruction("\tCMP `s0,`s1", left_temp_list, right_temp_list));
			if (conditionalBranches.containsKey(cjump.relop)) {
				String branch = conditionalBranches.get(cjump.relop);
				emit(new OperationInstruction("\t" + branch + " " + cjump.iftrue));
				emit(new OperationInstruction("\tnop"));
			}
			emit(new OperationInstruction("\tBA " + cjump.iffalse));
			emit(new OperationInstruction("\tnop"));
		}
		else if (s instanceof JUMP) {
			JUMP jump = (JUMP)s;
			emit(new OperationInstruction("\tBA `j0", left_temp_list, right_temp_list, jump.targets));
			emit(new OperationInstruction("\tnop"));
		}
		return;
	}

	NameOfTemp munchExp(Exp e) {
		/*
			munchExp(TEMP(t))
		*/
		if (e instanceof TEMP) {
			TEMP e_temp = (TEMP)e;
			return e_temp.temp;
		}
		NameOfTemp r = NameOfTemp.generateTemp();
		List<NameOfTemp> left_temp_list = new ArrayList<NameOfTemp>();
		List<NameOfTemp> right_temp_list = new ArrayList<NameOfTemp>();
		/*
			munchExp(MEM(BINOP(PLUS,e1,CONST(i))))
			munchExp(MEM(BINOP(PLUS,CONST(i),e1)))
			munchExp(MEM(CONST(i)))
			munchExp(MEM(e1))
		*/
		if (e instanceof MEM) {
			MEM e_mem = (MEM)e;
			/*
				munchExp(MEM(BINOP(PLUS,e1,CONST(i))))
				munchExp(MEM(BINOP(PLUS,CONST(i),e1)))
			*/
			if (e_mem.exp instanceof BINOP) {
				BINOP e_mem_binop = (BINOP)e_mem.exp;
				if (binopOperators.containsKey(e_mem_binop.binop)) {
					String binop_op = binopOperators.get(e_mem_binop.binop);
					/*
						munchExp(MEM(BINOP(PLUS,e1,CONST(i))))
					*/
					if (e_mem_binop.right instanceof CONST) {
						CONST right_const = (CONST)e_mem_binop.right;
						Exp left_exp = e_mem_binop.left;
						left_temp_list.add(r);
						right_temp_list.add(munchExp(left_exp));
						emit(new OperationInstruction("\tLD [`s0" + binop_op + Integer.toString(right_const.value) + "],`d0", left_temp_list, right_temp_list)); 
					}
					/*
						munchExp(MEM(BINOP(PLUS,CONST(i),e1)))
					*/
					else if (e_mem_binop.left instanceof CONST) {
						CONST left_const = (CONST)e_mem_binop.left;
						Exp right_exp = e_mem_binop.right;
						left_temp_list.add(r);
						right_temp_list.add(munchExp(right_exp));
						emit(new OperationInstruction("\tLD [`s0" + binop_op + Integer.toString(left_const.value) + "],`d0", left_temp_list, right_temp_list));
					}
					/*
						munchExp(MEM(BINOP(PLUS,e1,e2)))
					*/
					else {
						Exp e_mem_exp = e_mem.exp;
						left_temp_list.add(r);
						right_temp_list.add(munchExp(e_mem_exp));
						emit(new OperationInstruction("\tLD [`s0],`d0", left_temp_list, right_temp_list));
					}
				}
			}
			/*
				munchExp(MEM(CONST(i)))
			*/
			/*else if (e_mem.exp instanceof CONST) {
				CONST mem_const = (CONST)e_mem.exp;
				left_temp_list.add(r);
				emit(new OperationInstruction("\tLOAD `d0 <- M[r0+" + Integer.toString(mem_const.value) + "]", left_temp_list, null));
			}*/
			/*
				munchExp(MEM(e1))
			*/
			else {
				Exp e_mem_exp = e_mem.exp;
				left_temp_list.add(r);
				right_temp_list.add(munchExp(e_mem_exp));
				emit(new OperationInstruction("\tLD [`s0],`d0", left_temp_list, right_temp_list));
			}
		}
		/*
			munchExp(BINOP(PLUS,e1,CONST(i)))
			munchExp(BINOP(PLUS,CONST(i),e1))
			munchExp(BINOP(PLUS,e1,e2)) 
		*/
		else if (e instanceof BINOP) {
			BINOP e_binop = (BINOP)e;
			if (binopInstructions.containsKey(e_binop.binop)) {
				String binop_instruction = binopInstructions.get(e_binop.binop);
				/*
					munchExp(BINOP(PLUS,e1,CONST(i)))
				*/
				if (e_binop.right instanceof CONST) {
					CONST right_const = (CONST)e_binop.right;
					Exp left_exp = e_binop.left;
					left_temp_list.add(r);
					right_temp_list.add(munchExp(left_exp));
					emit(new OperationInstruction("\t" + binop_instruction + " `s0," + Integer.toString(right_const.value) + ",`d0", left_temp_list, right_temp_list)); 
				}
				/*
					munchExp(BINOP(PLUS,CONST(i),e1))
				*/
				else if (e_binop.left instanceof CONST) {
					CONST left_const = (CONST)e_binop.left;
					Exp right_exp = e_binop.right;
					left_temp_list.add(r);
					right_temp_list.add(munchExp(right_exp));
					emit(new OperationInstruction("\t" + binop_instruction + " `s0," + Integer.toString(left_const.value) + ",`d0", left_temp_list, right_temp_list));
				}
				/*
					munchExp(BINOP(PLUS,e1,e2)) 
				*/
				else {
					Exp left_exp = e_binop.left;
					Exp right_exp = e_binop.right;
					left_temp_list.add(r);
					right_temp_list.add(munchExp(left_exp));
					right_temp_list.add(munchExp(right_exp));
					emit(new OperationInstruction("\t" + binop_instruction + " `s0,`s1,`d0", left_temp_list, right_temp_list));  
				}
			}
		}
		/*
			munchExp(CONST(i))
		*/
		else if (e instanceof CONST) {
			CONST e_const = (CONST)e;
			emit(new MoveInstruction("\tSET " + Integer.toString(e_const.value) + ",`d0", r, null));
		}
		else if (e instanceof CALL) {
			CALL call = (CALL)e;
			int counter = 0;
			for (Exp arg : call.args.toList()) {
				emit(new MoveInstruction("\tMOV `s0,%o" + Integer.toString(counter), null, munchExp(arg)));
				counter++;
			}
			NAME func_name = (NAME)call.func;
			emit(new OperationInstruction("\tcall " + func_name.label.toString()));
			emit(new OperationInstruction("\tnop"));
			emit(new MoveInstruction("\tMOV %o0, `d0", r, null));
		}
		return r;
	}
}