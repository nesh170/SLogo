package Statements.view_command;

/**
 * @author Sierra, Yancheng
 */
import java.util.List;

import Statements.Statement;
import Statements.command.Command;
import view.ViewAbstract;

public class ATan extends Command{

	public ATan(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = Math.toDegrees(Math.atan(getMyStatements().get(0).execute()));
		getMyView().printMessage("" + result);
		return result;
	}

}
