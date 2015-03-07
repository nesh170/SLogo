package Statements.command;

import java.util.List;

import Statements.Statement;
import Statements.operator.Operator;
import view.ViewAbstract;

public abstract class Command extends Operator{
	private ViewAbstract myView;
	

	public Command(List<Statement> statements, ViewAbstract view) {
		super(statements);
		myView = view;
	}
	public ViewAbstract getMyView() {
		return myView;
	}
	
}
