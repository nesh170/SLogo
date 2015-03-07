package Statements.action_command;

import java.util.List;

import exceptions.ExecutionException;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

public class SetShape extends SetVisual {
	
	public SetShape(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> shapes) {
		super(statements, view, turtleManager, shapes);
		setErrorMessage("Invalid index for turtle shape.");
	}

	@Override
	public void completeOperation(int index) {
		getMyTurtles().setShapeIndex(index);
		getMyTurtles().doToActiveTurtles(e -> getMyView().changeShape(getVisualList().get(index), e.getID()));;
	}
}
