package Statements;

import view.ViewAbstract;
import Model.*;

public class HideTurtle extends Query {

	public HideTurtle(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		System.out.println("Hide Turtle");
		for(ModelTurtle t: myTurtleManager.getActiveTurtles()){
			t.setHiding(true);
			myView.visibleShape(!t.isHiding(), t.getID());
		}
		return 0;
	}

}
