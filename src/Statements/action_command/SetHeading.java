package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;
public class SetHeading extends SetAngle {

	public SetHeading(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		setMyExecuteResult(getMyStatements().get(0).execute());
		return super.execute();
	}
}
