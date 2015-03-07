package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**This class handles commands that move the turtle in a straight line.
 * @author Yancheng, Sierra
 */
public abstract class Walking extends Relocate{
	private double myAngle;
	

	/**
	 * Constructor for Walking.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 * @param colors List<String>
	 */
	public Walking(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		double executeResult = getMyStatements().get(0).execute();
		getMyTurtles().moveTurtle(executeResult, myAngle);
		getMyTurtles().doToActiveTurtles(e -> updateView(e));
		return executeResult;
	}
	
	/**
	 * Method getMyAngle.
	 * @return double
	 */
	public double getMyAngle() {
		return myAngle;
	}
	
	/**
	 * Method setMyAngle.
	 * @param myAngle double
	 */
	public void setMyAngle(double myAngle) {
		this.myAngle = myAngle;
	}
}
