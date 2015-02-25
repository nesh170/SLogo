package Statements;

import java.util.List;

import view.ViewAbstract;

public class Tan extends Command {

	public Tan(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = Math.tan(Math.toRadians(myStatements.get(0).execute()));
		myView.printMessage("" + result);
		return result;
	}

}
