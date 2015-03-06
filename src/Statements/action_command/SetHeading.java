package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.TurtleManager;
import Statements.Statement;
public class SetHeading extends SetAngle {

	public SetHeading(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		System.out.println("turns around");
		myExecuteResult = myStatements.get(0).execute();
		return super.execute();
	}

//	@Override
//	public int getNumParams() {
//		return Constants.SET_HEADING_PARAMS;
//	}

}
