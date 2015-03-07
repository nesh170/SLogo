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
		setMyExecuteResult(Math.toDegrees(Math.atan(getMyStatements().get(0).execute()/getMyStatements().get(1).execute())));
		return super.execute();
	}

}
