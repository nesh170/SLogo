package Statements.operator;

import java.util.List;

import Statements.Statement;

public abstract class Operator extends Statement{
	protected List<Statement> myStatements;
	
	public Operator(List<Statement> statements){
		myStatements = statements;
	}
}
