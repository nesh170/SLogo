package Statements;

import java.util.List;

import view.ViewAbstract;

public class ATan extends Command{

	public ATan(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = Math.toDegrees(Math.atan(myStatements.get(0).execute()));
		myView.printMessage("" + result);
		return result;
	}

}
