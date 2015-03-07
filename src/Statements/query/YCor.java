package Statements.query;

import view.ViewAbstract;
import Model.MultipleTurtles;

public class YCor extends Query{

	public YCor(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double result = (myTurtleManager.getTurtle(myTurtleManager.getActiveTurtleIDs().get(0))).getY();
		myView.printMessage(result + "");
		return result;
	}
}
