package visitor;

import java.util.HashMap;

public final class MiniJavaClass {
	public String id;
	public String extend_id = null;
   	public HashMap<String, String> fieldTable = new HashMap<String, String>();
   	public HashMap<String, MiniJavaMethod> methodTable = new HashMap<String, MiniJavaMethod>();

   	public MiniJavaClass(String class_id) {
   		this.id = class_id;
   	}
}
