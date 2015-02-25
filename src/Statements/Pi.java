package Statements;

import java.util.List;

import view.ViewAbstract;
import Constants.*;

public class Pi extends Command{

	public Pi(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double pi = Math.PI;
		myView.printMessage("" + pi);
		return pi;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.PI_PARAMS;
//	}

}
