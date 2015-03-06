package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

public class Sum extends Command{

	public Sum(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = myStatements.get(0).execute() + myStatements.get(1).execute();
		myView.printMessage("" + result);
		return result;
	}
}
