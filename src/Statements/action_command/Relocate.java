package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.ITurtle;
import Model.SingleTurtle;
import Statements.Statement;

/**This class acts as the template for commands that move the turtle to a certain
 * position specified by the user.
 * @author Yancheng, Sierra
 */
public abstract class Relocate extends ActionCommand {
	private List<String> myColors;
	
	/**
	 * Constructor for Relocate.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 * @param colors List<String>
	 */
	public Relocate(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager);
		myColors = colors;
	}

	/**
	 * Method updateView.
	 * @param t SingleTurtle
	 */
	public void updateView(SingleTurtle t){
		if(!t.isDrawing()){
			getMyView().moveShape(t.getX(), t.getY(), t.getID());
		} else {
			getMyView().drawShape(t.getX(), t.getY(),
					t.getID(), myColors.get(t.getPen().getColorIndex()),
					t.getPen().getThickness(), t.getPen().getPenStroke());
		}
	}
	
}
