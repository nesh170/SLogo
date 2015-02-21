package Statements;

import view.ViewAbstract;
import Model.TurtleManager;

public class XCor extends Query{

	public XCor(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double result = (myTurtleManager.getTurtle(myTurtleManager.getActiveTurtles().get(0))).getX();
		myView.printMessage(result + "");
		return result;
	}

}
