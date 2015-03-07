package Statements.action_command;

import java.util.List;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**
 * @author Yancheng, Sierra
 */
public class SetTowards extends SetAngle{

	/**
	 * Constructor for SetTowards.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 */
	public SetTowards(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		setMyExecuteResult(Math.toDegrees(Math.atan(getMyStatements().get(0).execute()/getMyStatements().get(1).execute())));
		return super.execute();
	}

}
