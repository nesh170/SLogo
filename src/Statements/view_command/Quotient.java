package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import Constants.*;
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
		double result = getMyStatements().get(0).execute() / getMyStatements().get(1).execute();
		getMyView().printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.QUOTIENT_PARAMS;
//	}

}
