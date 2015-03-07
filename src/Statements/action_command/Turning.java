package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

public class Turning extends ActionCommand {
	private double myTurningDirection;
	

	public Turning(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		double executeResult = getMyStatements().get(0).execute();
		double value = executeResult;
		executeResult = Constants.FULL_CIRCLE + (executeResult * myTurningDirection) % Constants.FULL_CIRCLE;
		getMyTurtles().rotate(value);
		getMyTurtles().doToActiveTurtles(t -> getMyView().rotateShape(t.getAngle(), t.getID()));
		return value;
	}
	
	public double getMyTurningDirection() {
		return myTurningDirection;
	}
	
	public void setMyTurningDirection(double myTurningDirection) {
		this.myTurningDirection = myTurningDirection;
	}
}
