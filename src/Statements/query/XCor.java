package Statements.query;

import view.ViewAbstract;
import Model.MultipleTurtles;


/**
 * @author Sierra, Yancheng
 */

public class XCor extends Query {

    public XCor (ViewAbstract view, MultipleTurtles manager) {
        super(view, manager);
    }

    @Override
    public double execute () {
        double result =
                (getMyTurtleManager().getTurtle(getMyTurtleManager().getActiveTurtleIDs().get(0)))
                        .getX();
        getMyView().printMessage(result + "");
        return result;
    }

}
