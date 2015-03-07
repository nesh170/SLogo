package Statements.query;

import Model.MultipleTurtles;
import Statements.Statement;
import view.ViewAbstract;


/**
 * This is a query superclass. It acts as a template for all turtle query commands. It takes in the
 * view and MultipleTurtles so it can report and inquire about turtles.
 * 
 * @author Sierra, Yancheng
 */

public abstract class Query extends Statement {
    private MultipleTurtles myTurtleManager;
    private ViewAbstract myView;

    public Query (ViewAbstract view, MultipleTurtles manager) {
        myView = view;
        myTurtleManager = manager;
    }

    public ViewAbstract getMyView () {
        return myView;
    }

    public MultipleTurtles getMyTurtleManager () {
        return myTurtleManager;
    }

}
