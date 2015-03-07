package Statements.command;

import java.util.List;
import Model.VariableManager;
import Statements.Statement;
import Statements.Variable;
import view.ViewAbstract;


/**
 * This method will add the variable into the variable table by taking in the variable manager.
 * 
 * @author Sierra, Yancheng
 *
 */
public class MakeVariable extends Command {
    private VariableManager myManager;

    public MakeVariable (List<Statement> statements, ViewAbstract view, VariableManager manager) {
        super(statements, view);
        myManager = manager;
    }

    @Override
    public double execute () {
        double value = getMyStatements().get(1).execute();
        myManager.addVariable(((Variable) getMyStatements().get(0)).getName(), value);
        return value;
    }

}
