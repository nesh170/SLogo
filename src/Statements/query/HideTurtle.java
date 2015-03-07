package Statements.query;

import view.ViewAbstract;
import Model.*;


/**
 * @author Sierra, Yancheng
 */
public class HideTurtle extends Query {

    public HideTurtle (ViewAbstract view, MultipleTurtles manager) {
        super(view, manager);
    }

    @Override
    public double execute () {
        System.out.println("Hide Turtle");
        for (SingleTurtle t : getMyTurtleManager().getActiveTurtles()) {
            t.setHiding(true);
            getMyView().visibleShape(!t.isHiding(), t.getID());
        }
        return 0;
    }

}
