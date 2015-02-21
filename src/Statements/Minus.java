package Statements;

import java.util.List;
import Constants.Constants;
import view.ViewAbstract;

public class Minus extends Command{

	public Minus(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = Constants.REVERSE_SIGN * myStatements.get(0).execute();
		myView.printMessage("" + result);
		return result;
	}

}
