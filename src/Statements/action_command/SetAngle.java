package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Constants.Constants;
import Model.*;
import Statements.Statement;

/** This class handles the commands that move the turtle based on angle 
 * parameters.
 * @author Yancheng, Sierra
 */
public abstract class SetAngle extends ActionCommand {
	private double myExecuteResult;
	

	/**
	 * Constructor for SetAngle.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 */
	public SetAngle(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		myExecuteResult = Constants.FULL_CIRCLE + myExecuteResult % Constants.FULL_CIRCLE;
		double toReturn = getMyTurtles().setAngle(myExecuteResult);
		getMyTurtles().doToActiveTurtles(t -> getMyView().rotateShape(t.getAngle(), t.getID()));
		return toReturn;
	}

	/**
	 * Method getMyExecuteResult.
	 * @return double
	 */
	public double getMyExecuteResult() {
		return myExecuteResult;
	}
	
	/**
	 * Method setMyExecuteResult.
	 * @param myExecuteResult double
	 */
	public void setMyExecuteResult(double myExecuteResult) {
		this.myExecuteResult = myExecuteResult;
	}
}
