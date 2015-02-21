package Statements;

import view.ViewAbstract;
import Model.TurtleManager;

public class PenDownP extends Query{

	public PenDownP(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		if (myTurtleManager.getTurtle(myTurtleManager.getActiveTurtles().get(0)).isPenUp()){
			myView.printMessage("0");
			return 0;
		}
		myView.printMessage("1");
		return 1;
	}

}
