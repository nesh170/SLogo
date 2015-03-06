package Statements.action_command;

import java.util.List;

import Constants.Constants;
import view.ViewAbstract;
import Model.ModelTurtle;
import Model.TurtleManager;
import Statements.Statement;

public class SetTowards extends SetAngle{

	public SetTowards(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		System.out.println("turns around");
		myExecuteResult = Math.toDegrees(Math.atan(myStatements.get(0).execute()/myStatements.get(1).execute()));
		return super.execute();
	}

}
