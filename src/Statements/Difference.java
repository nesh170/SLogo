package Statements;

import java.util.List;

import view.ViewAbstract;

public class Difference extends Command{

	public Difference(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	/**
	 * Returns the first expression minus the second expression
	 */
	@Override
	public double execute() {
		double result = myStatements.get(0).execute() - myStatements.get(1).execute();
		myView.printMessage("" +result);
		return result;
	}

}
