package Statements;

import java.util.List;

public abstract class Operator extends Statement{
	private List<Statement> myStatements;
	
	public Operator(List<Statement> statements){
		myStatements = statements;
	}
}
