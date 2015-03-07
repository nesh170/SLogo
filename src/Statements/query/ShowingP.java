package Statements.query;

import view.ViewAbstract;
import Constants.Constants;
import Model.MultipleTurtles;

public class ShowingP extends Query {

	public ShowingP(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		if (getMyTurtleManager()
				.getTurtle(getMyTurtleManager().getActiveTurtleIDs().get(0))
				.isHiding()) {
			getMyView().printMessage(Constants.HIDING_VALUE + "");
			return Constants.HIDING_VALUE;
		}
		getMyView().printMessage(Constants.SHOWING_VALUE + "");
		return Constants.SHOWING_VALUE;
	}

}
