package Statements;

import java.util.List;

import Model.VariableManager;
import view.ViewAbstract;

public class Make extends Command{
	private VariableManager myManager;
	
	public Make(List<Statement> statements, ViewAbstract view, VariableManager manager) {
		super(statements, view);
		myManager = manager;
	}

	@Override
	public double execute() {
		double value = myStatements.get(1).execute();
		myManager.addVariable(((Variable)myStatements.get(0)).getName(), value);
		myView.addVariable(((Variable)myStatements.get(0)).getName(), value);
		return value;
	}

}
