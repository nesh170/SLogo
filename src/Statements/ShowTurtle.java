package Statements;

import view.ViewAbstract;
import Model.ModelTurtle;
import Model.TurtleManager;

public class ShowTurtle extends Query {

	public ShowTurtle(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		System.out.println("Show Turtle");
		for(ModelTurtle t: myTurtleManager.getActiveTurtles()){
			t.setHiding(false);
			myView.visibleShape(!t.isHiding(), t.getID());
		}
		return 1;
	}

}
