package Statements;

import view.ViewAbstract;
import Constants.*;
import Model.TurtleManager;

public class Heading extends Query{

	public Heading(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double result = (myTurtleManager.getTurtle(myTurtleManager.getActiveTurtles().get(0))).getAngle();
		myView.printMessage(result + "");
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.HEADING_PARAMS;
//	}

}
