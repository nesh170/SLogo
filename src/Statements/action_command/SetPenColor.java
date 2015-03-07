package Statements.action_command;

import java.util.List;

import Model.*;
import Statements.Statement;
import slogoEnums.ErrorMessage;
import view.*;

/**
 * @author Yancheng, Sierra
 */
public class SetPenColor extends SetVisual{

	/**
	 * Constructor for SetPenColor.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtles ITurtle
	 * @param colors List<String>
	 */
	public SetPenColor(List<Statement> statements, ViewAbstract view, ITurtle turtles, List<String> colors) {
		super(statements, view, turtles, colors);
		setErrorMessage(ErrorMessage.INVALID_COLOR_INDEX.getVal());
	}

	/**
	 * Method completeOperation.
	 * @param index int
	 */
	@Override
	public void completeOperation(int index) {
		getMyTurtles().setPenColor(index);
	}

	
	
}
