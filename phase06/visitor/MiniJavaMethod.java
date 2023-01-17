package visitor;

import java.util.HashMap;

public final class MiniJavaMethod {
  	public String id;
  	public String type;
  	public HashMap<String, String> paramTable = new HashMap<String, String>();
  	public HashMap<String, String> localTable = new HashMap<String, String>();

  	public MiniJavaMethod(String method_id, String method_type) {
   		id = method_id;
   		type = method_type;
   	}
}