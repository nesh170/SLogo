package Statements.action_command;

import java.util.List;

import Model.*;
import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

/**ActionCommand handles all the commands that make changes to the state of the turtle
 * and display the new states in view. 
 * 
 * @author Yancheng, Sierra
 */
public abstract class ActionCommand extends Command{
	
	//turtle manager object
	private ITurtle myTurtles;
	

	/**
	 * Constructor for ActionCommand.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 */
	public ActionCommand(List<Statement> statements, ViewAbstract view, ITurtle turtleManager) {
		super(statements, view);
		myTurtles = turtleManager;
	}

	/**
	 * Method getMyTurtles.
	 * @return ITurtle
	 */
	public ITurtle getMyTurtles() {
		return myTurtles;
	}
	
}
