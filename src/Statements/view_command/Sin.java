package Statements.view_command;

import java.util.List;
import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

/**
 * @author Sierra, Yancheng
 */

public class Sin extends Command {

    public Sin (List<Statement> statements, ViewAbstract view) {
        super(statements, view);
    }

    @Override
    public double execute () {
        double result = Math.sin(Math.toRadians(getMyStatements().get(0).execute()));
        getMyView().printMessage("" + result);
        return result;
    }

}
