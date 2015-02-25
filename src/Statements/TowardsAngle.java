package Statements;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.ModelTurtle;
import Model.TurtleManager;

public class TowardsAngle extends SetAngle{

	public TowardsAngle(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		myExecuteResult = Math.toDegrees(Math.atan(myStatements.get(0).execute()/myStatements.get(1).execute()));
		return super.execute();
	}

}
