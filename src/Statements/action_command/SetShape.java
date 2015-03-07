package Statements.action_command;

import java.util.List;

import slogoEnums.ErrorMessage;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

/**
 * @author Yancheng, Sierra
 */
public class SetShape extends SetVisual {
	
	/**
	 * Constructor for SetShape.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 * @param shapes List<String>
	 */
	public SetShape(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> shapes) {
		super(statements, view, turtleManager, shapes);
		setErrorMessage(ErrorMessage.INVALID_SHAPE_INDEX.getVal());
	}

	/**
	 * Method completeOperation.
	 * @param index int
	 */
	@Override
	public void completeOperation(int index) {
		getMyTurtles().setShapeIndex(index);
		getMyTurtles().doToActiveTurtles(e -> getMyView().changeShape(getVisualList().get(index), e.getID()));;
	}
}
