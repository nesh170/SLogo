package Statements.query;

import view.ViewAbstract;
import Constants.Constants;
import Model.TurtleManager;
import Constants.*;

public class PenDownP extends Query {

	public PenDownP(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		if (myTurtleManager
				.getTurtle(myTurtleManager.getActiveTurtleIDs().get(0)).isDrawing()) {
			myView.printMessage(Constants.PEN_UP_VALUE + "");
			return Constants.PEN_UP_VALUE;
		}
		myView.printMessage(Constants.PEN_DOWN_VALUE + "");
		return Constants.PEN_DOWN_VALUE;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.PENDOWNP_PARAMS;
//	}

}
