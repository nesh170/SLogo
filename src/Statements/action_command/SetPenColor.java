package Statements.action_command;

import java.util.List;

import Model.*;
import Statements.Statement;
import view.*;
import exceptions.*;
import Constants.*;
import exceptions.*;

public class SetPenColor extends SetVisual{

	public SetPenColor(List<Statement> statements, ViewAbstract view, ITurtle turtles, List<String> colors) {
		super(statements, view, turtles, colors);
		setErrorMessage(ErrorMessage.INVALID_COLOR_INDEX.getVal());
	}

	@Override
	public void completeOperation(int index) {
		getMyTurtles().setPenColor(index);
	}

	
	
}
