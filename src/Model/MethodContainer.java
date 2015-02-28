package Model;

import java.util.List;
import Statements.*;

public class MethodContainer {
	private List <Statement> myVars;
	private List <Statement> myCommands;
	
	public MethodContainer(List<Statement> variables, List <Statement> commands){
		myVars = variables;
		myCommands = commands;
	}
	
	public List<Statement> getVars(){
		return myVars;
	}
	
	public List<Statement> getCommands(){
		return myCommands;
	}
	
}
