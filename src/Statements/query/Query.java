package Statements.query;

import Model.MultipleTurtles;
import Statements.Statement;
import view.ViewAbstract;

public abstract class Query extends Statement{
	protected MultipleTurtles myTurtleManager;
	protected ViewAbstract myView;
	
	public Query(ViewAbstract view, MultipleTurtles manager){
		myView = view;
		myTurtleManager = manager;
	}
}
