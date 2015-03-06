package Statements.query;

import view.ViewAbstract;
import Constants.Constants;
import Model.TurtleManager;

public class ShowingP extends Query {

	public ShowingP(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		if (myTurtleManager
				.getTurtle(myTurtleManager.getActiveTurtleIDs().get(0))
				.isHiding()) {
			myView.printMessage(Constants.HIDING_VALUE + "");
			return Constants.HIDING_VALUE;
		}
		myView.printMessage(Constants.SHOWING_VALUE + "");
		return Constants.SHOWING_VALUE;
	}

}
