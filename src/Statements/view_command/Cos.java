package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import Constants.*;
import view.ViewAbstract;

public class Cos extends Command{

	public Cos(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		//cos(90) is not equal to 1 when testing, but 6.123e-17???
		double result = Math.cos(Math.toRadians(getMyStatements().get(0).execute()));
		getMyView().printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.COS_PARAMS;
//	}

}
