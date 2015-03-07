package Statements.action_command;

import java.util.List;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

public class SetTowards extends SetAngle{

	public SetTowards(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		myExecuteResult = Math.toDegrees(Math.atan(myStatements.get(0).execute()/myStatements.get(1).execute()));
		return super.execute();
	}

}
