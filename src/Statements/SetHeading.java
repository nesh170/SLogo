package Statements;

import java.util.List;

import view.ViewAbstract;
import Constants.Constants;
import Model.ModelTurtle;
import Model.TurtleManager;
import Constants.Constants;
public class SetHeading extends SetAngle {

	public SetHeading(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		myExecuteResult = myStatements.get(0).execute();
		return super.execute();
	}

//	@Override
//	public int getNumParams() {
//		return Constants.SET_HEADING_PARAMS;
//	}

}
