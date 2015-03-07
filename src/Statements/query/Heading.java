package Statements.query;

import view.ViewAbstract;
import Constants.*;
import Model.MultipleTurtles;

public class Heading extends Query{

	public Heading(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double result = (getMyTurtleManager().getTurtle(getMyTurtleManager().getActiveTurtleIDs().get(0))).getAngle();
		getMyView().printMessage(result + "");
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.HEADING_PARAMS;
//	}

}
