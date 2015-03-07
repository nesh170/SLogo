package Statements.query;

import view.ViewAbstract;
import Model.MultipleTurtles;


/**
 * @author Sierra, Yancheng
 */

public class Stamp extends Query {

    public Stamp (ViewAbstract view, MultipleTurtles manager) {
        super(view, manager);
    }

    @Override
    public double execute () {
        getMyView().stamp(0);
        return 0;
    }

}
