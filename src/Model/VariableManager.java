package Model;

import java.util.HashMap;
import view.*;
import java.util.Map;

public class VariableManager {
	private Map<String, Double> myVariables;
	private ViewAbstract myView;
	
	public VariableManager(ViewAbstract view){
		myVariables = new HashMap<>();
		myView = view;
	}
	
	public void addVariable(String var, Double value){
		myVariables.put(var, value);
		myView.addVariable(var, value);
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
	
	public boolean containsVariable(String s){
		return myVariables.containsKey(s);
	}
	
}
