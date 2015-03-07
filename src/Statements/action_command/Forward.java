package Statements.action_command;

import Constants.Constants;

import java.util.List;

import Model.MultipleTurtles;
import Statements.Statement;
import view.ViewAbstract;

/**
 * @author Yancheng, Sierra
 */
public class Forward extends Walking {

	/**
	 * Constructor for Forward.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager MultipleTurtles
	 * @param colors List<String>
	 */
	public Forward(List<Statement> statements, ViewAbstract view,
			MultipleTurtles turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
		setMyAngle(Constants.FORWARD_ANGLE);
	}
}
