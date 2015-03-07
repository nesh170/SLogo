package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;
import Constants.*;

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

//	public static int getNumParams() {
//		return Constants.ATAN_PARAMS;
//	}

}