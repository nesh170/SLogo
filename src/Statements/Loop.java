package Statements;

import java.util.List;
import Model.*;

public abstract class Loop extends Statement{
	protected List<Statement> myFirstList;
	protected List<Statement> mySecondList;
	protected VariableManager myManager;
	
	public Loop(List<Statement> list1, List<Statement> list2, VariableManager manager){
		myFirstList = list1;
		mySecondList = list2;
		myManager = manager;
	}
	
}
