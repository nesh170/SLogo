package Statements;

import java.util.List;
import Constants.*;

import view.ViewAbstract;

public class Remainder extends Command{

	public Remainder(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	/**
	 * Returns the remainder of dividing the first expression by the second expression
	 */
	@Override
	public double execute() {
		double result = myStatements.get(0).execute() % myStatements.get(1).execute();
		myView.printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.REMAINDER_PARAMS;
//	}

}
