package Statements.query;

import view.ViewAbstract;
import Model.TurtleManager;

public class Stamp extends Query {

	public Stamp(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		myView.stamp(0);
		return 0;
	}

}
