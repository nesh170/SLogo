package Statements;

import java.util.List;

import view.ViewAbstract;

public class Quotient extends Command{

	public Quotient(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	/**
	 * Returns the first expression divided by the second expression
	 */
	@Override
	public double execute() {
		double result = myStatements.get(0).execute() / myStatements.get(1).execute();
		myView.printMessage("" + result);
		return result;
	}

}
