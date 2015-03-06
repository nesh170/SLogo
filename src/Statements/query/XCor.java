package Statements.query;

import view.ViewAbstract;
import Model.TurtleManager;

public class XCor extends Query{

	public XCor(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double result = (myTurtleManager.getTurtle(myTurtleManager.getActiveTurtleIDs().get(0))).getX();
		myView.printMessage(result + "");
		return result;
	}

}
