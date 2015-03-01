package Statements;

import view.ViewAbstract;
import Model.TurtleManager;

public class ShowTurtle extends Query {

	public ShowTurtle(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		System.out.println("Show Turtle");
		return 1;
	}

}
