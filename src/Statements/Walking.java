package Statements;

import java.util.List;

import view.ViewAbstract;
import Model.ModelTurtle;
import Model.Pen;
import Model.TurtleManager;

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
		System.out.println("size of active turtles: " + myTurtleManager.getActiveTurtleIDs().size());
		for(Integer ID: myTurtleManager.getActiveTurtleIDs()){
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			currentTurtle.moveTurtle(executeResult, myAngle);
			System.out.println("X is "+currentTurtle.getX()+" Y is "+currentTurtle.getY());
		}
		myTurtleManager.doToActiveTurtles(e -> updateView(e));
		return executeResult;
	}
	
	public void updateView(ModelTurtle t){
		if(!t.isDrawing()){
			myView.moveShape(t.getX(), t.getY(), t.getID());
		} else {
			myView.drawShape(t.getX(), t.getY(),
					t.getID(), myColors.get(t.getPen().getColorIndex()),
					t.getPen().getThickness(), t.getPen().getPenStroke());
		}
	}
	
}
