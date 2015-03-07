package Statements;

import Model.VariableManager;

/**
 * @author Yancheng, Sierra
 */
public class Variable extends Statement{
	private String myName;
	private VariableManager myManager;
	
	
	/**
	 * Constructor for Variable.
	 * @param name String
	 * @param manager VariableManager
	 */
	public Variable(String name, VariableManager manager){
		myName = name;
		myManager = manager;
	}
	
	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		return myManager.getVarValue(myName);
	}
	
	/**
	 * Method getName.
	 * @return String
	 */
	public String getName(){
		return myName;
	}
}
