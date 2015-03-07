package Statements.query;

import Constants.Constants;
import view.ViewAbstract;
import Model.MultipleTurtles;


public class SetPenUp extends Query {

    public SetPenUp (ViewAbstract view, MultipleTurtles manager) {
        super(view, manager);
    }

    @Override
    public double execute () {
        getMyTurtleManager().getTurtle(getMyTurtleManager().getActiveTurtleIDs().get(0))
                .setDrawing(Constants.SET_PEN_UP);
        System.out.println("Set Turtle Pen Up");
        return 1;
    }

}
