package Statements;

import view.ViewAbstract;
import Model.TurtleManager;

public class HideTurtle extends Query {

	public HideTurtle(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		System.out.println("Hide Turtle");
		return 0;
	}

}
