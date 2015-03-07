package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Constants.Constants;
import Model.*;
import Statements.Statement;

public abstract class SetAngle extends ActionCommand {
	private double myExecuteResult;
	

	public SetAngle(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		myExecuteResult = Constants.FULL_CIRCLE + myExecuteResult % Constants.FULL_CIRCLE;
		double toReturn = getMyTurtles().setAngle(myExecuteResult);
		getMyTurtles().doToActiveTurtles(t -> getMyView().rotateShape(t.getAngle(), t.getID()));
		return toReturn;
	}

	public double getMyExecuteResult() {
		return myExecuteResult;
	}
	
	public void setMyExecuteResult(double myExecuteResult) {
		this.myExecuteResult = myExecuteResult;
	}
}
