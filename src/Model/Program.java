package Model;

import java.util.List;

import Statements.Statement;

public class Program {
	private List<Statement> myStatements;

	// execute all of the statement objects
	public Program(List<Statement> statements) {
		myStatements = statements;
	}

	public void execute() {
		for (Statement statement : myStatements) {
			statement.execute();
		}
	}

}
