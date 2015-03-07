package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
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
		double result = getMyStatements().get(0).execute() % getMyStatements().get(1).execute();
		getMyView().printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.REMAINDER_PARAMS;
//	}

}
