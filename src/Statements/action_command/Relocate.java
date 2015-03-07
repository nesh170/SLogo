package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.ITurtle;
import Model.SingleTurtle;
import Statements.Statement;

public abstract class Relocate extends ActionCommand {
	private List<String> myColors;
	
	public Relocate(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager);
		myColors = colors;
	}

	public void updateView(SingleTurtle t){
		if(!t.isDrawing()){
			myView.moveShape(t.getX(), t.getY(), t.getID());
		} else {
			myView.drawShape(t.getX(), t.getY(),
					t.getID(), myColors.get(t.getPen().getColorIndex()),
					t.getPen().getThickness(), t.getPen().getPenStroke());
		}
	}
	
}
