package Statements;

import view.ViewAbstract;
import Model.TurtleManager;
import Constants.*;

public class ClearScreen extends Query {

	public ClearScreen(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double distance = myTurtleManager.getTurtle(Constants.FIRST_TURTLE_ID)
				.getTotalDistance();
		myTurtleManager.clearTurtles();
		myTurtleManager.addTurtle(Constants.FIRST_TURTLE_ID);
		System.out.println("clear screen");
		myView.clearScreen();
		myView.addShape(Constants.DEFAULT_SHAPE, Constants.FIRST_TURTLE_XCOR,
				Constants.FIRST_TURTLE_YCOR, Constants.FIRST_TURTLE_ID);
		return distance;
	}

}
