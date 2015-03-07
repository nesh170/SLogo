package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**This class serves as the super class for the turning movements of the turtle.
 * @author Yancheng, Sierra
 */
public class Turning extends ActionCommand {
	private double myTurningDirection;
	

	/**
	 * Constructor for Turning.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 */
	public Turning(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		double executeResult = getMyStatements().get(0).execute();
		double value = executeResult;
		executeResult = Constants.FULL_CIRCLE + (executeResult * myTurningDirection) % Constants.FULL_CIRCLE;
		getMyTurtles().rotate(executeResult);
		getMyTurtles().doToActiveTurtles(t -> getMyView().rotateShape(t.getAngle(), t.getID()));
		return value;
	}
	
	/**
	 * Method getMyTurningDirection.
	 * @return double
	 */
	public double getMyTurningDirection() {
		return myTurningDirection;
	}
	
	/**
	 * Method setMyTurningDirection.
	 * @param myTurningDirection double
	 */
	public void setMyTurningDirection(double myTurningDirection) {
		this.myTurningDirection = myTurningDirection;
	}
}
