package Statements.loop;

import java.util.List;

import Model.*;
import Statements.Statement;

public abstract class Loop extends Statement{
	protected List<List<Statement>> myParams;
	protected VariableManager myManager;
	
	public Loop(List<List<Statement>> params, VariableManager manager){
		myParams = params;
		myManager = manager;
	}
	
}
