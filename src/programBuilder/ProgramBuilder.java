package programBuilder;

import java.util.ArrayList;
import java.util.List;

import Constants.Constants;
import Model.Program;
import Model.TurtleManager;
import Model.VariableManager;
import Statements.*;
import view.ViewAbstract;
import parser.*;

public class ProgramBuilder {
	private ViewAbstract myView;
	private TurtleManager myTurtleManager;
	private VariableManager myVariableManager;
	private Regex myRegex;
	
	public ProgramBuilder(ViewAbstract view, TurtleManager turtles, VariableManager vars){
		myView = view;
		myTurtleManager = turtles;
		myVariableManager = vars;
		myRegex = new Regex(Constants.SYNTAX, Constants.DEFAULT_LANGUAGE);
	}
	
	public Program buildProgram(List<ParseNode> topNodes){
		Program newProg = new Program();
		for(ParseNode curNode: topNodes){
			newProg.addStatment(recursiveStatementBuilder(curNode));
		}
		return newProg;
	}
	
	private Statement recursiveStatementBuilder(ParseNode node){
		String nodeName = node.getName();
		//get type string: if constant --> "constant", if variable --> "variable", if userdefined --> "user"
		//other wise type = node name
		String type = myRegex.matchSyntax(nodeName);
		ArrayList<ArrayList<Statement>> paramLists = new ArrayList<>();
		switch (type){
		case "Constant":
			System.out.println("creating constant: " + Double.parseDouble(nodeName));
			return new Value(Double.parseDouble(nodeName));
		case "Variable":
			System.out.println("creating variable");
			return new Variable(nodeName, myVariableManager);
		default:
			ArrayList<Statement> baseList = new ArrayList<>();
			paramLists.add(baseList);
			ArrayList<Statement> curList = baseList;
			for(int i = 0; i < node.getNumChildren(); i++){
				ParseNode curChild = node.getChildren().get(i);
				if(curChild.getName().equals("Group")){
					if(i != 0){
						curList = new ArrayList<>();
						paramLists.add(curList);
						//loop through children and add them to the list 
					}
				}
				else{
					baseList.add(recursiveStatementBuilder(curChild));
				}
			}
		}
		
		String commandType = myRegex.matchCommand(nodeName);
		switch(commandType){
		case "Forward":
			System.out.println("Making forward object");
			return new Forward(paramLists.get(0), myView, myTurtleManager);
		case "Sum":
			System.out.println("Making sum object");
			return new Sum(paramLists.get(0), myView);
		default:
			//throw an error
			return new Forward(paramLists.get(0), myView, myTurtleManager);
		}
	}
}
