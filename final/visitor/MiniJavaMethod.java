package visitor;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public final class MiniJavaMethod {
  	public String id;
  	public String type;
  	public HashMap<String, String> paramTable = new HashMap<String, String>();
  	public HashMap<String, String> localTable = new HashMap<String, String>();
  	public List<String> paramList = new ArrayList<String>();
  	public MiniJavaClass declaring_class;

  	public MiniJavaMethod(String method_id, String method_type, MiniJavaClass dec_class) {
   		id = method_id;
   		type = method_type;
   		declaring_class = dec_class;
   	}
}