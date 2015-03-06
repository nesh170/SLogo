package Statements;

import Constants.Constants;

import java.util.List;

import Model.ModelTurtle;
import Model.TurtleManager;
import view.ViewAbstract;

public class Forward extends Walking {

	public Forward(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
		myAngle = Constants.FORWARD_ANGLE;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.FORWARD_PARAMS;
//	}

}
