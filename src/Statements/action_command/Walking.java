package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;

public abstract class Walking extends Relocate{
	protected double myAngle;
	
	public Walking(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}

	@Override
	public double execute() {
		double executeResult = myStatements.get(0).execute();
		myTurtles.moveTurtle(executeResult, myAngle);
		myTurtles.doToActiveTurtles(e -> updateView(e));
		return executeResult;
	}
}
