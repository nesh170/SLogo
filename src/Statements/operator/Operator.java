package Statements.operator;

import java.util.List;

import Statements.Statement;

public abstract class Operator extends Statement{
	private List<Statement> myStatements;
	
	public Operator(List<Statement> statements){
		myStatements = statements;
	}
	
	public List<Statement> getMyStatements() {
		return myStatements;
	}
	
	public void setMyStatements(List<Statement> myStatements) {
		this.myStatements = myStatements;
	}
	
}
