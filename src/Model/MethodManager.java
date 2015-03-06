package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Statements.*;

public class MethodManager {
	private Map<String, MethodContainer> myMethods;
	
	public MethodManager(){
		myMethods = new HashMap<>();
	}
	
	public void addMethod(String name, List<Statement> vars, List<Statement> commands){
		myMethods.put(name, new MethodContainer(vars, commands));
	}
	
	public MethodContainer getMethodFromName(String name){
		return myMethods.get(name);
	}
}
