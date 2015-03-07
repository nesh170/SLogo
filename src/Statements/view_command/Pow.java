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
		double result = Math.pow(getMyStatements().get(0).execute(), getMyStatements().get(1).execute());
		getMyView().printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.POW_PARAMS;
//	}

}
