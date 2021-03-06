package Statements.view_command;

import java.util.List;
import java.util.Random;
import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

/**
 * @author Sierra, Yancheng
 */

public class RandomGen extends Command {
    private Random myRandom;

    public RandomGen (List<Statement> statements, ViewAbstract view) {
        super(statements, view);
        myRandom = new Random();
    }

    @Override
    public double execute () {
        double result = myRandom.nextDouble() * getMyStatements().get(0).execute();
        getMyView().printMessage("" + result);
        return result;
    }

}
