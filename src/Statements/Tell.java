package Statements;

import java.util.ArrayList;
import java.util.List;

import Model.TurtleManager;

public class Tell extends Operator{

	private TurtleManager myTurtleManager;
	
	public Tell(List<Statement> statements, TurtleManager turtleManager){
		super(statements);
		myTurtleManager = turtleManager;
	}
	
	@Override
	public double execute() {
		List<Integer> turtleIDs = new ArrayList<>();
		for(Statement instruct : myStatements){
			turtleIDs.add((int)instruct.execute());
		}
		myTurtleManager.setActiveTurtles(turtleIDs);
		return turtleIDs.get(turtleIDs.size()-1);
	}

}
