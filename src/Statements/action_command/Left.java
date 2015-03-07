package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**
 * @author Yancheng, Sierra
 */
public class Left extends Turning{

	/**
	 * Constructor for Left.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 */
	public Left(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
		setMyTurningDirection(Constants.LEFT_DIRECTION);
	}
	
}
