package Statements;

import Constants.Constants;

import java.util.List;

import Model.ModelTurtle;
import Model.TurtleManager;
import view.ViewAbstract;

public class Forward extends ActionCommand {
	
	public Forward(List<Statement> statements, ViewAbstract view, TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		double executeResult = myStatements.get(0).execute();
		for(Integer ID: myTurtleManager.getActiveTurtles()){
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			currentTurtle.moveTurtle(executeResult, Constants.FORWARD_ANGLE);
			System.out.println("X is "+currentTurtle.getX()+" Y is "+currentTurtle.getY());
			if(currentTurtle.isPenUp()){
				myView.moveTurtle(currentTurtle.getX(), currentTurtle.getY(), currentTurtle.getID());
			} else {
				myView.drawTurtle(currentTurtle.getX(), currentTurtle.getY(), currentTurtle.getID());
			}
		}
		return executeResult;
	}

}
