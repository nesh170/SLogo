package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

public class Sin extends Command{

	public Sin(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = Math.sin(Math.toRadians(myStatements.get(0).execute()));
		myView.printMessage("" + result);
		return result;
	}

}
