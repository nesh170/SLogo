package Statements.query;

import view.ViewAbstract;
import Model.MultipleTurtles;
import Constants.*;

public class ClearScreen extends Query {

	public ClearScreen(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		double distance = getMyTurtleManager().getTurtle(Constants.FIRST_TURTLE_ID)
				.getTotalDistance();
		getMyTurtleManager().clearTurtles();
		getMyTurtleManager().addTurtle(Constants.FIRST_TURTLE_ID);
		System.out.println("clear screen");
		getMyView().clearScreen();
		getMyView().addShape(Constants.DEFAULT_SHAPE, Constants.FIRST_TURTLE_XCOR,
				Constants.FIRST_TURTLE_YCOR, Constants.FIRST_TURTLE_ID);
		return distance;
	}

}
