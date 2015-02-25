package Model;

import java.util.ArrayList;
import java.util.List;

import Statements.Statement;

public class Program {
	private List<Statement> myStatements;

	// execute all of the statement objects
	public Program() {
		myStatements = new ArrayList<>();
	}

	public void addStatment(Statement toAdd){
		myStatements.add(toAdd);
	}
	
	public void execute() {
		System.out.println("Size of the statement: " + myStatements.size());
		System.out.println(myStatements.get(0).getClass().toString());
		for (Statement statement : myStatements) {
			statement.execute();
		}
	}

}
