package Model;

import java.util.HashMap;
import view.*;
import java.util.Map;

/**
 * This class keeps track of all of the variables currently available in whatever class is tracking
 * this manager.  Allows functionality to add a variable with a corresponding value and get the 
 * value given a variable name.  
 * 
 * @author Sierra, Yancheng
 */
public class VariableManager {
	private Map<String, Double> myVariables;
	private ViewAbstract myView;
	
	/**
	 * Constructor for VariableManager.
	 * @param view ViewAbstract
	 */
	public VariableManager(ViewAbstract view){
		myVariables = new HashMap<>();
		myView = view;
	}
	
	/**
	 * Adds a variable with the given name and value
	 * @param var String
	 * @param value Double
	 */
	public void addVariable(String var, Double value){
		myVariables.put(var, value);
		myView.addVariable(var, value);
	}
	
	/**
	 *Removes the variable with the given name if it exists
	 * @param var String
	 */
	public void removeVariable(String var){
		myVariables.remove(var);
	}
	
	public void clearVariables(){
		myVariables.clear();
	}
	
	/**
	 * Returns the value of the specified variable, assumes the variable exists
	 * @param varName String
	 * @return Double
	 */
	public Double getVarValue(String varName){
		return myVariables.get(varName);
	}
	
	/**
	 * Returns true if a variable with the given name exists, false otherwise.
	 * @param s String
	 * @return boolean
	 */
	public boolean containsVariable(String s){
		return myVariables.containsKey(s);
	}
	
}
