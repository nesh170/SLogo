package Statements;

import java.util.List;
import Constants.Constants;
import view.ViewAbstract;
import Model.TurtleManager;

public class Left extends Turning{

	public Left(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
		myTurningDirection = Constants.LEFT_DIRECTION;
	}
	
}
