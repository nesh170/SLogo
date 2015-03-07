package Statements.query;

import view.ViewAbstract;
import Constants.Constants;
import Model.MultipleTurtles;

public class SetPenDown extends Query {

	public SetPenDown(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		myTurtleManager.getTurtle(myTurtleManager.getActiveTurtleIDs().get(0)).setDrawing(Constants.SET_PEN_DOWN);
		System.out.println("Set Turtle Pen Down");
		return 0;
	}

}
