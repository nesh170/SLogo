package Statements.query;

import view.ViewAbstract;
import Model.TurtleManager;

public class ClearStamps extends Query {

	public ClearStamps(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		return myView.clearStamps()?1:0;
	}

}
