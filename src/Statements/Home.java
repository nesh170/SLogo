package Statements;

import java.util.List;
import Constants.Constants;
import view.ViewAbstract;
import Model.TurtleManager;

public class Home extends SetPosition {

	public Home(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	public double execute() {
		myExecuteResultX = Constants.STARTING_XCOR;
		myExecuteResultY = Constants.STARTING_YCOR;
		return super.execute();
	}

}
