package Statements.query;

import view.ViewAbstract;
import Model.MultipleTurtles;

public class YCor extends Query{

	public YCor(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double result = (getMyTurtleManager().getTurtle(getMyTurtleManager().getActiveTurtleIDs().get(0))).getY();
		getMyView().printMessage(result + "");
		return result;
	}
}
