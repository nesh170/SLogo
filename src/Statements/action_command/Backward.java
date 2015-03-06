package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.TurtleManager;
import Statements.Statement;

public class Backward extends Walking{

	public Backward(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
		myAngle = Constants.BACKWARD_ANGLE;
	}

//	public static int getNumParams() {
//		return Constants.BACK_PARAMS;
//	}
}
