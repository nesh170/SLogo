package Statements.view_command;

import java.util.List;
import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

/**
 * @author Sierra, Yancheng
 */

public class Sum extends Command {

    public Sum (List<Statement> statements, ViewAbstract view) {
        super(statements, view);
    }

    @Override
    public double execute () {
        double result = getMyStatements().get(0).execute() + getMyStatements().get(1).execute();
        getMyView().printMessage("" + result);
        return result;
    }
}
