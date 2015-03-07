package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**This class handles commands that move the turtle based on
 * distance or location specifications.
 * @author Yancheng, Sierra
 */
public abstract class SetPosition extends Relocate {
	protected double myExecuteResultX;
	protected double myExecuteResultY;

	/**
	 * Constructor for SetPosition.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 * @param colors List<String>
	 */
	public SetPosition(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		double result = getMyTurtles().relocateTurtle(myExecuteResultX, myExecuteResultY);
		getMyTurtles().doToActiveTurtles(e -> updateView(e));
		return result;
	}

}
