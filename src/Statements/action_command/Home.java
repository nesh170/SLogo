package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**
 * @author Yancheng, Sierra
 */
public class Home extends SetPosition {

	/**
	 * Constructor for Home.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 * @param colors List<String>
	 */
	public Home(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	public double execute() {
		myExecuteResultX = Constants.STARTING_XCOR;
		myExecuteResultY = Constants.STARTING_YCOR;
		return super.execute();
	}
}
