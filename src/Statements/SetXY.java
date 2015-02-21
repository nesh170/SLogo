package Statements;

import java.util.List;

import view.ViewAbstract;
import Model.TurtleManager;

public class SetXY extends SetPosition{

	public SetXY(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}
	
	public double execute(){
		myExecuteResultX = myStatements.get(0).execute();
		myExecuteResultY = myStatements.get(1).execute();
		return super.execute();
	}

}
