package Statements;

import java.util.List;
import Model.*;
import view.*;
import exceptions.*;

public class SetPenColor extends Operator{
	private TurtleManager myTurtles;
	private List<String> myColors;
	private ViewAbstract myView;

	public SetPenColor(List<Statement> statements, TurtleManager turtles, List<String> colors, ViewAbstract view) {
		super(statements);
		myTurtles = turtles;
		myColors = colors;
		myView = view;
	}

	@Override
	public double execute() {
		int index = (int) myStatements.get(0).execute();
		try{
			if(index >= myColors.size()){
				throw new ExecutionException("Invalid index for set pen color.");
			}else{
				myTurtles.doToActiveTurtles(e -> e.setPenColor(index));
			}
		}catch(ExecutionException e){
			myView.printError(e.toString());
		}
		return (double) index;
	}

}
