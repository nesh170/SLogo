package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;
/**
 * @author Yancheng, Sierra
 */
public class SetHeading extends SetAngle {

	/**
	 * Constructor for SetHeading.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 */
	public SetHeading(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		setMyExecuteResult(getMyStatements().get(0).execute());
		return super.execute();
	}
}
