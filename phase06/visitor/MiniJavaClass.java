package visitor;

import java.util.HashMap;

public final class MiniJavaClass {
	public String id;
	public String extend_id = null;
   	public HashMap<String, String> fieldTable = new HashMap<String, String>();
   	public HashMap<String, MiniJavaMethod> methodTable = new HashMap<String, MiniJavaMethod>();
   	private int num_fields = -1;

   	public MiniJavaClass(String class_id) {
   		this.id = class_id;
   	}

   	public int getNumFields(HashMap<String, MiniJavaClass> symbolTable) {
   		if (num_fields == -1) {
   			MiniJavaClass current_class = this;
		    num_fields = 0;
		    while(current_class != null) {
		    	num_fields += current_class.fieldTable.size();
			    if(current_class.extend_id != null) {
			    	current_class = symbolTable.get(current_class.extend_id);
			    } 
			    else {
			    	current_class = null;
			    }
		    } 
   		}
   		return num_fields;
   	}
}
