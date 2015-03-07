package Statements.query;

import Model.MultipleTurtles;
import Statements.Statement;
import view.ViewAbstract;

public abstract class Query extends Statement{
	private MultipleTurtles myTurtleManager;
	private ViewAbstract myView;
	

	public Query(ViewAbstract view, MultipleTurtles manager){
		myView = view;
		myTurtleManager = manager;
	}
	
	public ViewAbstract getMyView() {
		return myView;
	}
	
	public MultipleTurtles getMyTurtleManager() {
		return myTurtleManager;
	}

}
