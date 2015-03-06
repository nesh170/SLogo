package Statements.action_command;

import java.util.List;

import Model.TurtleManager;
import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

public abstract class ActionCommand extends Command{
	
	//turtle manager object
	protected TurtleManager myTurtleManager;
	
	public ActionCommand(List<Statement> statements, ViewAbstract view, TurtleManager turtleManager) {
		super(statements, view);
		myTurtleManager = turtleManager;
	}

}
