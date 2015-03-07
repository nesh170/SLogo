package Statements.loop;

import java.util.List;

import Model.*;
import Statements.Statement;

public abstract class Loop extends Statement{
	private List<List<Statement>> myParams;
	private VariableManager myManager;
	
	public Loop(List<List<Statement>> params, VariableManager manager){
		myParams = params;
		myManager = manager;
	}
	
	public VariableManager getMyManager() {
		return myManager;
	}

	
	public List<List<Statement>> getMyParams() {
		return myParams;
	}

}
