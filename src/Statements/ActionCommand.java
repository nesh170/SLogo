package Statements;

import java.util.List;

import Model.TurtleManager;
import view.ViewAbstract;

public abstract class ActionCommand extends Command{
	
	//turtle manager object
	protected TurtleManager myTurtleManager;
	
	public ActionCommand(List<Statement> statements, ViewAbstract view, TurtleManager turtleManager) {
		super(statements, view);
		myTurtleManager = turtleManager;
	}

}
