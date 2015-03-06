package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.ModelTurtle;
import Model.TurtleManager;
import Statements.Statement;

public class Turning extends ActionCommand {
	protected double myTurningDirection;
	
	public Turning(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		double executeResult = myStatements.get(0).execute();
		double value = executeResult;
		for (Integer ID : myTurtleManager.getActiveTurtleIDs()) {
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			executeResult = Constants.FULL_CIRCLE + (executeResult * myTurningDirection) % Constants.FULL_CIRCLE;
			currentTurtle.rotate(executeResult);
			myView.rotateShape(currentTurtle.getAngle(), currentTurtle.getID());
			System.out.println("Angle is " + currentTurtle.getAngle());
		}
		return value;
	}

}
