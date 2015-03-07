package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.MultipleTurtles;
import Statements.Statement;

/**
 * @author Yancheng, Sierra
 */
public class Backward extends Walking{

	/**
	 * Constructor for Backward.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager MultipleTurtles
	 * @param colors List<String>
	 */
	public Backward(List<Statement> statements, ViewAbstract view,
			MultipleTurtles turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
		setMyAngle(Constants.BACKWARD_ANGLE);
	}
}
