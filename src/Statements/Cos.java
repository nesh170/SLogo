package Statements;

import java.util.List;

import view.ViewAbstract;

public class Cos extends Command{

	public Cos(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		//cos(90) is not equal to 1 when testing, but 6.123e-17???
		double result = Math.cos(Math.toRadians(myStatements.get(0).execute()));
		myView.printMessage("" + result);
		return result;
	}

}
