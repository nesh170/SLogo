package Statements;

import java.util.List;
import Constants.Constants;
import view.ViewAbstract;
import Model.TurtleManager;

public class Backward extends Walking{

	public Backward(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
		myAngle = Constants.BACKWARD_ANGLE;
	}
}
