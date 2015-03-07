package Statements.loop;

import java.util.List;

import Statements.Statement;

public class IfElse extends Statement {
	private List<List<Statement>> myStatementLists;
	
	public IfElse(List<List<Statement>> statementLists){
		myStatementLists = statementLists;
	}
	
	@Override
	public double execute() {
		int listNum;
		if(myStatementLists.get(0).get(0).execute() == 0){
			listNum = 2;
		}
		else{
			listNum = 1;
		}
		return executeStatements(myStatementLists.get(listNum));
	}
	
	public double executeStatements(List<Statement> toExecute){
		double result = 0;
		for(Statement state: toExecute){
			result = state.execute();
		}
		return result;
	}

}
