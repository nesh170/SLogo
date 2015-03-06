package Statements;

import java.util.List;
import Model.*;
import view.*;
import exceptions.*;

public class SetPenColor extends ActionCommand{
	private List<String> myColors;

	public SetPenColor(List<Statement> statements, ViewAbstract view, TurtleManager turtles, List<String> colors) {
		super(statements, view, turtles);
		myColors = colors;
	}

	@Override
	public double execute() {
		int index = (int) myStatements.get(0).execute();
		try{
			if(index >= myColors.size()){
				throw new ExecutionException("Invalid index for set pen color.");
			}else{
				myTurtleManager.doToActiveTurtles(e -> e.setPenColor(index));
			}
		}catch(ExecutionException e){
			myView.printError(e.toString());
		}
		return (double) index;
	}

}
