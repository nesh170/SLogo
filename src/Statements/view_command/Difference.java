package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;
import Constants.Constants;

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

//	@Override
//	public int getNumParams() {
//		return Constants.DIFFERENCE_PARAMS;
//	}

}
