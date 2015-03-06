package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.TurtleManager;
import Statements.Statement;

public class Home extends SetPosition {

	public Home(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}

	public double execute() {
		myExecuteResultX = Constants.STARTING_XCOR;
		myExecuteResultY = Constants.STARTING_YCOR;
		return super.execute();
	}

}
