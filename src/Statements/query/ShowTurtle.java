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
		for(SingleTurtle t: myTurtleManager.getActiveTurtles()){
			t.setHiding(false);
			myView.visibleShape(!t.isHiding(), t.getID());
		}
		return 1;
	}

}
