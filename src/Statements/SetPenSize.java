package Statements;

import java.util.List;

import view.ViewAbstract;
import Model.TurtleManager;
import exceptions.*;

public class SetPenSize extends ActionCommand {

	public SetPenSize(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
	}

	@Override
	public double execute() {
		double newSize = myStatements.get(0).execute();
		try{
			if(newSize < 0){
				throw new ExecutionException("Pen width can't be negative");
			}else{
				myTurtleManager.doToActiveTurtles(e -> e.setPenSize(newSize));
			}
		}catch (ExecutionException e){
			myView.printError(e.toString());
		}
		return newSize;
	}

}
