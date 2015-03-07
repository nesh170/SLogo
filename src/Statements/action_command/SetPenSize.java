package Statements.action_command;

import java.util.List;

import view.ViewAbstract;
import Model.*;
import Statements.Statement;
import exceptions.*;

public class SetPenSize extends ActionCommand {

	public SetPenSize(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		double newSize = myStatements.get(0).execute();
		try{
			if(newSize < 0){
				throw new ExecutionException("Pen width can't be negative");
			}else{
				myTurtles.setPenSize(newSize);
			}
		}catch (ExecutionException e){
			myView.printError(e.toString());
		}
		return newSize;
	}

}
