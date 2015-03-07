package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**
 * @author Yancheng, Sierra
 */
public class Right extends Turning{

	/**
	 * Constructor for Right.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 */
	public Right(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
		setMyTurningDirection(Constants.RIGHT_DIRECRION);
	}

}
