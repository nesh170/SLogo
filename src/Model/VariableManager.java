package Model;

import java.util.HashMap;
import java.util.Map;

public class VariableManager {
	private Map<String, Double> myVariables;
	
	public VariableManager(){
		myVariables = new HashMap<>();
	}
	
	public void addVariable(String var, Double value){
		myVariables.put(var, value);
	}
	
	public void removeVariable(String var){
		myVariables.remove(var);
	}
	
	public void clearVariables(){
		myVariables.clear();
	}
	
	public Double getVarValue(String varName){
		return myVariables.get(varName);
	}
	
}
