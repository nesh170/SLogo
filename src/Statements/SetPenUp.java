package Statements;

import java.util.List;
import Constants.Constants;
import view.ViewAbstract;
import Model.TurtleManager;

public class SetPenUp extends Query{

	public SetPenUp(ViewAbstract view, TurtleManager manager) {
		super(view, manager);
	}

	@Override
	public double execute() {
		myTurtleManager.getTurtle(myTurtleManager.getActiveTurtles().get(0)).setPen(Constants.SET_PEN_UP);
		System.out.println("Set Turtle Pen Up");
		return 1;
	}

}
