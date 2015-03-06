package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.TurtleManager;
import Statements.Statement;

public class Right extends Turning{

	public Right(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
		myTurningDirection = Constants.RIGHT_DIRECRION;
	}

}
