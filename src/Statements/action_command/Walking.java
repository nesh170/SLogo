package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;

public abstract class Walking extends Relocate{
	private double myAngle;
	

	public Walking(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}

	@Override
	public double execute() {
		double executeResult = getMyStatements().get(0).execute();
		getMyTurtles().moveTurtle(executeResult, myAngle);
		getMyTurtles().doToActiveTurtles(e -> updateView(e));
		return executeResult;
	}
	
	public double getMyAngle() {
		return myAngle;
	}
	
	public void setMyAngle(double myAngle) {
		this.myAngle = myAngle;
	}
}
