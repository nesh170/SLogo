package Statements;

import java.util.List;

import view.ViewAbstract;

public abstract class Command extends Operator{
	private ViewAbstract myView;
	
	public Command(List<Statement> statements, ViewAbstract view) {
		super(statements);
		myView = view;
	}

}