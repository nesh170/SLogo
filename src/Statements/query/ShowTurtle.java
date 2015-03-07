package Statements.query;

import view.ViewAbstract;
import Model.SingleTurtle;
import Model.MultipleTurtles;

public class ShowTurtle extends Query {

	public ShowTurtle(ViewAbstract view, MultipleTurtles manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		System.out.println("Show Turtle");
		for(SingleTurtle t: getMyTurtleManager().getActiveTurtles()){
			t.setHiding(false);
			getMyView().visibleShape(!t.isHiding(), t.getID());
		}
		return 1;
	}

}
