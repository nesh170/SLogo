package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;

public abstract class SetPosition extends Relocate {
	protected double myExecuteResultX;
	protected double myExecuteResultY;

	public SetPosition(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}

	@Override
	public double execute() {
		double result = getMyTurtles().relocateTurtle(myExecuteResultX, myExecuteResultY);
		getMyTurtles().doToActiveTurtles(e -> updateView(e));
		return result;
	}

}
