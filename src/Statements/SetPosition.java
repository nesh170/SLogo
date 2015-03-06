package Statements;

import java.util.List;

import view.ViewAbstract;
import Model.*;

public abstract class SetPosition extends ActionCommand {
	protected double myExecuteResultX;
	protected double myExecuteResultY;
	private List<String> myColors;

	public SetPosition(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager, List<String> colors) {
		super(statements, view, turtleManager);
		myColors = colors;
	}

	@Override
	public double execute() {
		double distanceTraveled = 0;
		for (Integer ID : myTurtleManager.getActiveTurtleIDs()) {
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			distanceTraveled = currentTurtle.relocateTurtle(myExecuteResultX, myExecuteResultY);
			if (!currentTurtle.isDrawing()) {
				myView.moveShape(currentTurtle.getX(), currentTurtle.getY(),
						currentTurtle.getID());
			} else {
				Pen curPen = currentTurtle.getPen();
				myView.drawShape(currentTurtle.getX(), currentTurtle.getY(),
						currentTurtle.getID(), myColors.get(curPen.getColorIndex()),
						curPen.getThickness(), curPen.getPenStroke());
			}
		}
		return distanceTraveled;
	}

}
