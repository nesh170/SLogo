package Statements;

import java.util.List;
import Constants.*;

import view.ViewAbstract;

public class Log extends Command{

	public Log(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = Math.log(myStatements.get(0).execute());
		myView.printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.LOG_PARAMS;
//	}

}
