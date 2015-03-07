package Statements.view_command;

import java.util.List;
import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

/**
 * @author Sierra, Yancheng
 */
public class Pi extends Command {

    public Pi (List<Statement> statements, ViewAbstract view) {
        super(statements, view);
    }

    @Override
    public double execute () {
        double pi = Math.PI;
        getMyView().printMessage("" + pi);
        return pi;
    }
}
