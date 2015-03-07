package Model;

import java.util.List;
import Statements.*;

/**
 * The purpose of this class is to store a user defined command.  It keeps track of a list of variables
 * which are the parameters for the method and a list of commands which are the body of the method.
 * It acts as a container in that it never uses the commands or variables, but hands them out to other
 * classes that ask for them.  The intended user of this class is a UserDefinedMethod statement object.  
 * While this class does not use the variables or commands, the classes that use this class will likely
 * depend on the fact that the variables are in fact variable objects and that the commands are command
 * objects. 
 * 
 * @author Sierra, Yancheng
 */
public class MethodContainer {
	private List <Statement> myVars;
	private List <Statement> myCommands;
	
	/**
	 * Creates a MethodContainer, which consists of a parameter list and a list of commands that 
	 * are the method body.
	 * @param variables List<Statement>
	 * @param commands List<Statement>
	 */
	public MethodContainer(List<Statement> variables, List <Statement> commands){
		myVars = variables;
		myCommands = commands;
	}
	
	/**
	 * Returns the variable statement objects that act as the parameters for this user
	 * defined method.
	 * @return List<Statement>
	 */
	public List<Statement> getVars(){
		return myVars;
	}
	
	/**
	 * Returns the list of statement commands that represent the implementation body of 
	 * this user defined method.
	 * @return List<Statement>
	 */
	public List<Statement> getCommands(){
		return myCommands;
	}
	
}
