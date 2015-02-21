package Statements;

import Model.TurtleManager;
import view.ViewAbstract;

public abstract class Query extends Statement{
	protected TurtleManager myTurtleManager;
	protected ViewAbstract myView;
	
	public Query(ViewAbstract view, TurtleManager manager){
		myView = view;
		myTurtleManager = manager;
	}
}
