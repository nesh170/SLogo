package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Statements.*;

/**
 * This class keeps track of the methods that the user has defined since the program has been running
 * or that have been read in from an XML file.  It maps a method name to a method container which stores
 * the information needed to implement and run the method.  It's functions are very simple: to add 
 * new methods and to hand out methods that are asked for.
 * 
 * @author Sierra, Yancheng
 */
public class MethodManager {
	private Map<String, MethodContainer> myMethods;
	
	public MethodManager(){
		myMethods = new HashMap<>();
	}
	
	/**
	 * Takes in the name, variables, and commands for a newly defined method, creates 
	 * a method container to store the information, and adds that container to the method
	 * map.  
	 * @param name String the method name
	 * @param vars List<Statement> the list of variables of the method
	 * @param commands List<Statement> the list of commands for the method
	 */
	public void addMethod(String name, List<Statement> vars, List<Statement> commands){
		myMethods.put(name, new MethodContainer(vars, commands));
	}
	
	/**
	 * Returns the method container that corresponds to the name given, if one exists. 
	 * Assumes that the specified method exists in the method table.
	 * @param name String the name of the method to be returned
	 * @return MethodContainer that hold the information about the method
	 */
	public MethodContainer getMethodFromName(String name){
		return myMethods.get(name);
	}
}
