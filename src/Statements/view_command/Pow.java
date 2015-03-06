package Statements.view_command;

import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;
import Constants.*;

public class Pow extends Command{

	public Pow(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = Math.pow(myStatements.get(0).execute(), myStatements.get(1).execute());
		myView.printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.POW_PARAMS;
//	}

}
