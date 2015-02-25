package Statements;

import java.util.List;

import view.ViewAbstract;
import Constants.Constants;
import Model.ModelTurtle;
import Model.TurtleManager;

public abstract class Walking extends ActionCommand{
	protected double myAngle;
	
	public Walking(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		double executeResult = myStatements.get(0).execute();
		System.out.println("size of active turtles: " + myTurtleManager.getActiveTurtles().size());
		for(Integer ID: myTurtleManager.getActiveTurtles()){
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			currentTurtle.moveTurtle(executeResult, myAngle);
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
