package Statements.action_command;

import java.util.List;

import exceptions.ExecutionException;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;

public class SetShape extends ActionCommand {
	private List<String> myShapes;
	
	public SetShape(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> shapes) {
		super(statements, view, turtleManager);
		myShapes = shapes;
	}

	@Override
	public double execute() {
		int index = (int) myStatements.get(0).execute();
		try{
			if(index >= myShapes.size() || index < 0){
				throw new ExecutionException("Invalid index for set turtle shape.");
			}else{
				myTurtles.setShapeIndex(index);
				myTurtles.doToActiveTurtles(e -> myView.changeShape(myShapes.get(index), e.getID()));
			}
		}catch(ExecutionException e){
			myView.printError(e.toString());
		}
		return (double) index;
	}

}
