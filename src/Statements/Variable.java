package Statements;

import Model.VariableManager;

public class Variable extends Statement{
	private String myName;
	private VariableManager myManager;
	
	
	public Variable(String name, VariableManager manager){
		myName = name;
		myManager = manager;
	}
	
	@Override
	public double execute() {
		return myManager.getVarValue(myName);
	}
	
	public String getName(){
		return myName;
	}
}
