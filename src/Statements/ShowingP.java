package Statements;

import view.ViewAbstract;
import Constants.Constants;
import Model.TurtleManager;

public class ShowingP extends Query{

	public ShowingP(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		if (myTurtleManager.getTurtle(myTurtleManager.getActiveTurtles().get(0)).isHiding()){
			myView.printMessage(Constants.HIDING_VALUE + "");
			return 0;
		}
		myView.printMessage(Constants.SHOWING_VALUE + "");
		return 1;
	}

}
