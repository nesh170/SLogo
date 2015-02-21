package Statements;

import Constants.Constants;

import java.util.List;

import Model.ModelTurtle;
import Model.TurtleManager;
import view.ViewAbstract;

public class Forward extends Walking {

	public Forward(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
		myAngle = Constants.FORWARD_ANGLE;
	}

}
