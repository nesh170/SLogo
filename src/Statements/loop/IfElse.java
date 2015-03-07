package Statements.loop;

import java.util.List;

import Statements.Statement;

/**
 */
public class IfElse extends Statement {
	private List<List<Statement>> myStatementLists;
	
	/**
	 * Constructor for IfElse.
	 * @param statementLists List<List<Statement>>
	 */
	public IfElse(List<List<Statement>> statementLists){
		myStatementLists = statementLists;
	}
	
	/**
	 * Method execute.
	 * @return double
	 */
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
	
	/**
	 * Method executeStatements.
	 * @param toExecute List<Statement>
	 * @return double
	 */
	public double executeStatements(List<Statement> toExecute){
		double result = 0;
		for(Statement state: toExecute){
			result = state.execute();
		}
		return result;
	}

}
