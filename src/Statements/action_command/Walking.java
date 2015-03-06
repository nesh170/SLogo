package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Constants.Constants;
import Model.ModelTurtle;
import Model.Pen;
import Model.TurtleManager;
import Statements.Statement;

public abstract class Walking extends ActionCommand{
	protected double myAngle;
	private List<String> myColors;
	
	public Walking(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager, List<String> colors) {
		super(statements, view, turtleManager);
		myColors = colors;
	}

	@Override
	public double execute() {
		double executeResult = myStatements.get(0).execute();
		for(Integer ID: myTurtleManager.getActiveTurtleIDs()){
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			currentTurtle.moveTurtle(executeResult, myAngle);
			if(!currentTurtle.isDrawing()){
				myView.moveShape(currentTurtle.getX(), currentTurtle.getY(), currentTurtle.getID());
			} else {
				Pen curPen = currentTurtle.getPen();
				myView.drawShape(currentTurtle.getX(), currentTurtle.getY(),
						currentTurtle.getID(), myColors.get(curPen.getColorIndex()),
						curPen.getThickness(), curPen.getPenStroke());
			}
		}
		return executeResult;
	}
}
