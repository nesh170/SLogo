package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.MultipleTurtles;
import Statements.Statement;

public class Backward extends Walking{

	public Backward(List<Statement> statements, ViewAbstract view,
			MultipleTurtles turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
		setMyAngle(Constants.BACKWARD_ANGLE);
	}
}
