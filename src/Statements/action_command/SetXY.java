package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**
 * @author Yancheng, Sierra
 */
public class SetXY extends SetPosition{

	/**
	 * Constructor for SetXY.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 * @param colors List<String>
	 */
	public SetXY(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> colors) {
		super(statements, view, turtleManager, colors);
	}
	
	/**
	 * Method execute.
	 * @return double
	 */
	public double execute(){
		myExecuteResultX = getMyStatements().get(0).execute();
		myExecuteResultY = getMyStatements().get(1).execute();
		return super.execute();
	}

}
