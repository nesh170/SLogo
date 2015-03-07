package Statements.query;

import view.ViewAbstract;
import Model.MultipleTurtles;

public class ClearStamps extends Query {

	public ClearStamps(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		return getMyView().clearStamps()?1:0;
	}

}
