package Statements.query;

import Model.TurtleManager;
import Statements.Statement;
import view.ViewAbstract;

public abstract class Query extends Statement{
	protected TurtleManager myTurtleManager;
	protected ViewAbstract myView;
	
	public Query(ViewAbstract view, TurtleManager manager){
		myView = view;
		myTurtleManager = manager;
	}
}
