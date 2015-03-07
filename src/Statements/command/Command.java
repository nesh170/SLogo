package Statements.command;

import java.util.List;
import Statements.Statement;
import Statements.operator.Operator;
import view.ViewAbstract;

/**
 * Acts as a parent class to all commands that has parameter and results in some visual change
 * @author Sierra, Yancheng
 *
 */
public abstract class Command extends Operator {
    private ViewAbstract myView;

    public Command (List<Statement> statements, ViewAbstract view) {
        super(statements);
        myView = view;
    }

    public ViewAbstract getMyView () {
        return myView;
    }

}
