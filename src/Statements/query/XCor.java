package Statements.query;

import view.ViewAbstract;
import Model.MultipleTurtles;

public class XCor extends Query{

	public XCor(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double result = (myTurtleManager.getTurtle(myTurtleManager.getActiveTurtleIDs().get(0))).getX();
		myView.printMessage(result + "");
		return result;
	}

}
