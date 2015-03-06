package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.TurtleManager;
import Statements.Statement;
import Constants.*;

public class SetXY extends SetPosition{

	public SetXY(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}
	
	public double execute(){
		myExecuteResultX = myStatements.get(0).execute();
		myExecuteResultY = myStatements.get(1).execute();
		return super.execute();
	}

}
