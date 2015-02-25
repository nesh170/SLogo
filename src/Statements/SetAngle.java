package Statements;

import java.util.List;
import view.ViewAbstract;
import Constants.Constants;
import Model.ModelTurtle;
import Model.TurtleManager;

public abstract class SetAngle extends ActionCommand {
	protected double myExecuteResult;
	
	public SetAngle(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
		
	}

	@Override
	public double execute() {
		for (Integer ID : myTurtleManager.getActiveTurtles()) {
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			myExecuteResult = Constants.FULL_CIRCLE + myExecuteResult % Constants.FULL_CIRCLE;
			currentTurtle.setAngle(myExecuteResult);
			myView.rotateTurtle(currentTurtle.getAngle(), currentTurtle.getID());
			System.out.println("The set angle is " + currentTurtle.getAngle() + " the result is "+myExecuteResult);
		}
		return myExecuteResult % Constants.FULL_CIRCLE;
	}

}
