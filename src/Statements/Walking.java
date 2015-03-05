package Statements;

import java.util.List;

import view.ViewAbstract;
import Constants.Constants;
import Model.ModelTurtle;
import Model.Pen;
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
		System.out.println("size of active turtles: " + myTurtleManager.getActiveTurtleIDs().size());
		for(Integer ID: myTurtleManager.getActiveTurtleIDs()){
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			currentTurtle.moveTurtle(executeResult, myAngle);
			System.out.println("X is "+currentTurtle.getX()+" Y is "+currentTurtle.getY());
			if(!currentTurtle.isDrawing()){
				myView.moveShape(currentTurtle.getX(), currentTurtle.getY(), currentTurtle.getID());
			} else {
				Pen curPen = currentTurtle.getPen();
				myView.drawShape(currentTurtle.getX(), currentTurtle.getY(),
						currentTurtle.getID(), curPen.getStringColor(),
						curPen.getThickness(), curPen.getPenStroke());
			}
		}
		return executeResult;
	}
}
