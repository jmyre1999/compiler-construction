package sparc;

import visitor.*;
import tree.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public final class Frame {
	/* Parameter name -> TEMP */
	public HashMap<String, TEMP> paramTemps = new HashMap<String, TEMP>();
	/* Local variable name -> MEM */
	public HashMap<String, MEM> localMems = new HashMap<String, MEM>();
	/* Class field name -> MEM */
	public HashMap<String, MEM> fieldMems = new HashMap<String, MEM>();

	public static TEMP thisPointer = new TEMP("%i0");

	public static TEMP framePointer = new TEMP("%fp");

	public Frame(MiniJavaMethod m, MiniJavaClass c, HashMap<String, MiniJavaClass> symbolTable) {
	    /* Temp %i1, %i2, %i3, ... = method params */
	    Iterator<String> params = m.paramTable.keySet().iterator();
	    String param;
	    int counter = 1;
	    while(params.hasNext()) {
	    	param = params.next();
	    	paramTemps.put(param, new TEMP("%i" + Integer.toString(counter)));
	    	counter += 1;
	    }
	    /* Mem %fp - 4j for j=1;j<num_vars */
		Iterator<String> locals = m.localTable.keySet().iterator();
	    String local;
	    counter = 1;
	    while(locals.hasNext()) {
	    	local = locals.next();
	    	localMems.put(local, new MEM(new BINOP(BINOP.MINUS, framePointer, new CONST(4*counter))));
	    	counter += 1;
	    }
	    /* Mem %i0 + 4j  for j=0;j<num_fields */
	    MiniJavaClass current_class = c;
	    Iterator<String> fields;
	    String field;
	    counter = 0;
	    while(current_class != null) {
	    	fields = current_class.fieldTable.keySet().iterator();
	    	while(fields.hasNext()) {
		    	field = fields.next();
		    	if(!fieldMems.containsKey(field)) {
		    		fieldMems.put(field, new MEM(new BINOP(BINOP.PLUS, thisPointer, new CONST(4*counter))));
		    		counter += 1;
		    	}
		    }
		    if(current_class.extend_id != null) {
		    	current_class = symbolTable.get(current_class.extend_id);
		    } 
		    else {
		    	current_class = null;
		    }
	    } 
	}

	public Exp findID(String id) {
		if(paramTemps.containsKey(id)) {
			return paramTemps.get(id);
		}
		else if(localMems.containsKey(id)) {
			return localMems.get(id);
		}
		else if(fieldMems.containsKey(id)){
			return fieldMems.get(id);
		}
		else {
			return (new CONST(0));
		}
	}
}