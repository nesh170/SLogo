package Statements.query;

import view.ViewAbstract;
import Constants.Constants;
import Model.MultipleTurtles;


/**
 * @author Sierra, Yancheng
 */
public class PenDownP extends Query {

    public PenDownP (ViewAbstract view, MultipleTurtles manager) {
        super(view, manager);
    }

    @Override
    public double execute () {
        if (getMyTurtleManager()
                .getTurtle(getMyTurtleManager().getActiveTurtleIDs().get(0)).isDrawing()) {
            getMyView().printMessage(Constants.PEN_UP_VALUE + "");
            return Constants.PEN_UP_VALUE;
        }
        getMyView().printMessage(Constants.PEN_DOWN_VALUE + "");
        return Constants.PEN_DOWN_VALUE;
    }

    // @Override
    // public int getNumParams() {
    // return Constants.PENDOWNP_PARAMS;
    // }

}
