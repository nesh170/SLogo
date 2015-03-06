package programBuilder;

import java.util.ArrayList;
import java.util.List;

import Constants.Constants;
import Model.MethodManager;
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
	private MethodManager myMethodManager;

	public ProgramBuilder(ViewAbstract view, TurtleManager turtles, VariableManager vars, Regex regex, MethodManager methodManager){
		myView = view;
		myTurtleManager = turtles;
		myVariableManager = vars;
		myRegex = regex;
		myMethodManager = methodManager;
	}
	
	public Program buildProgram(List<ParseNode> topNodes){
		Program newProg = new Program(myView);
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
		List<List<Statement>> paramLists = new ArrayList<>();
		switch (type){
		case "Constant":
			System.out.println("creating constant: " + Double.parseDouble(nodeName));
			return new Value(Double.parseDouble(nodeName));
		case "Variable":
			System.out.println("creating variable");
			return new Variable(nodeName, myVariableManager);
		default:
			List<Statement> baseList = new ArrayList<>();
			paramLists.add(baseList);
			List<Statement> curList = baseList;
			int looptimes = node.getChildCount();
			if(node.getName().equals("to")){
				looptimes--;
				baseList.add(new MethodName(node.getChildren().get(0).getName()));
				node.removeChild(0);
				curList = new ArrayList<>();
				paramLists.add(curList);
			}
			for(int i = 0; i < looptimes; i++){
				ParseNode curChild = node.getChildren().get(i);
				if(curChild.getName().equals("Group")){
					if(i != 0){
						curList = new ArrayList<>();
						paramLists.add(curList);
					}
					for(ParseNode groupKid: curChild.getChildren()){
						curList.add(recursiveStatementBuilder(groupKid));
					}
				}
				else{
					baseList.add(recursiveStatementBuilder(curChild));
				}
			}
		}
		String commandType = myRegex.matchCommand(nodeName);
		//if commandType is null, check if it is a user defined before sending to command factory
		return CommandFactory.generateCommand(commandType, paramLists, myView, 
				myTurtleManager, myVariableManager, myRegex, myMethodManager);
	}
}
