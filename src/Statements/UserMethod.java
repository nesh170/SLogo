package Statements;

import java.util.List;
import Model.*;

public class UserMethod extends Statement{
	private VariableManager myVarManager;
	private List<Statement> myValues;
	private List<Statement> myVariables;
	private List<Statement> myCommands;
	
	public UserMethod(List<Statement> values, List <Statement> variables, List<Statement> commands, VariableManager varMan){
		myVarManager = varMan;
		myValues = values;
		myVariables = variables;
		myCommands = commands;
	}
	
	@Override
	public double execute() {
		addVarsToTable();
		double result = 0;
		System.out.println("About to execute user-defined method");
		for(Statement curCommand: myCommands){
			result = curCommand.execute();
		}
		return result;
	}
	
	private void addVarsToTable(){
		for(int i = 0; i < myVariables.size(); i++){
			myVarManager.addVariable(((Variable)myVariables.get(i)).getName(), myValues.get(i).execute());
		}
	}
}
