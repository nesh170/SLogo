package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;

public class SetXY extends SetPosition{

	public SetXY(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}
	
	public double execute(){
		myExecuteResultX = myStatements.get(0).execute();
		myExecuteResultY = myStatements.get(1).execute();
		return super.execute();
	}

}
