package Statements.action_command;

import Constants.Constants;

import java.util.List;

import Model.SingleTurtle;
import Model.MultipleTurtles;
import Statements.Statement;
import view.ViewAbstract;

public class Forward extends Walking {

	public Forward(List<Statement> statements, ViewAbstract view,
			MultipleTurtles turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
		setMyAngle(Constants.FORWARD_ANGLE);
	}
}
