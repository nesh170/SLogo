package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import Constants.Constants;
import view.ViewAbstract;
import Constants.*;

public class Minus extends Command{

	public Minus(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = Constants.REVERSE_SIGN * getMyStatements().get(0).execute();
		getMyView().printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.MINUS_PARAMS;
//	}

}
