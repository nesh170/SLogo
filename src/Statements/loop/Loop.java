package Statements.loop;

import java.util.List;

import Model.*;
import Statements.Statement;

/**This class handles all the commands that involve looping and new variable defining.
 * @author Yancheng, Sierra
 */
public abstract class Loop extends Statement{
	private List<List<Statement>> myParams;
	private VariableManager myManager;
	
	/**
	 * Constructor for Loop.
	 * @param params List<List<Statement>>
	 * @param manager VariableManager
	 */
	public Loop(List<List<Statement>> params, VariableManager manager){
		myParams = params;
		myManager = manager;
	}
	
	/**
	 * Method getMyManager.
	 * @return VariableManager
	 */
	public VariableManager getMyManager() {
		return myManager;
	}

	
	/**
	 * Method getMyParams.
	 * @return List<List<Statement>>
	 */
	public List<List<Statement>> getMyParams() {
		return myParams;
	}

}
