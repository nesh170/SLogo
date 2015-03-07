package Statements.action_command;

import java.util.List;

import Model.*;
import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

public abstract class ActionCommand extends Command{
	
	//turtle manager object
	protected ITurtle myTurtles;
	
	public ActionCommand(List<Statement> statements, ViewAbstract view, ITurtle turtleManager) {
		super(statements, view);
		myTurtles = turtleManager;
	}

}
