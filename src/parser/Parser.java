package parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Constants.Constants;
import Model.Program;
public class Parser {
	private static final String DEFAULT_LANGUAGE = "resources/languages/English";
	private static final String SYNTAX = "resources/languages/Syntax";
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSED_BRACKET = "]";
	private static final String GROUP = "Group";
	
	private Regex myRegex;
	private int myCurIndex;
	private String[] myCurProgramArray;
	private Set<String> myProgramVariables;
	private Set<String> myProgMethods;

	public Parser(){
		Constants.initializeMap();
		myRegex = new Regex(SYNTAX, DEFAULT_LANGUAGE);
		myCurIndex = -1;
		myProgramVariables = new HashSet<>();
		myProgMethods = new HashSet<>();
	}
	//returns a program, once that class is created
	public void parse(String program){
//		preProcess the string;
		String processed = preProcessString(program);
		if(processed == null){
			return;
		}
		myCurProgramArray = processed.split(" ");
//		create a program;
		Program curProgram = new Program();
		List<ParseNode> topNodes = new ArrayList<>();
//		loop through the string array until it's empty{
		while(myCurIndex < myCurProgramArray.length-1){
			ParseNode curNode = new ParseNode(myCurProgramArray[++myCurIndex]);
			System.out.println("Root Node: " + curNode.getName());
			ParseNode processedNode = recursiveCommandBuilder(curNode);
			System.out.println("Printing in parse while loop");
			topNodes.add(processedNode);
			System.out.println("Added node: " + processedNode.getName());
		}
//			create new node; //the index of the string is index + 1
//			increment index;
//			call parsenode on the current node;
//			add current node (head node of each independent command) to the statement list; 
//		}
//		make and return program;
	}
	
	public void changeLanguage(String language){
		 
	}
	
	public String preProcessString(String program){
		String[] splitProgram = program.split(" ");
//		check if string is empty
		if(splitProgram.length < 1 | program.length() < 1){
			System.out.println("String empty or full of spaces");
			return null;
		}
		
		//pre-process the string to remove comments
		return program;
	}
	
	private ParseNode recursiveCommandBuilder(ParseNode current){
		String nodeName = current.getName();
		
		String type = myRegex.matchSyntax(nodeName);
		
		switch(type){
		case "Command":
			String commandType = myRegex.matchCommand(nodeName);
			//check if commmandtype is null, this means it may be a user defined command and call appropriate helper
			switch(commandType){
			case "MakeVariable":
			
			default:
				int loopTimes = Constants.myStatementParamMap.get(commandType);
				while(current.getNumChildren() < loopTimes){
					if(atEndOfString()){
						System.out.println("Missing parameters for a command");
						return null;
					}
					String next = myCurProgramArray[++myCurIndex];
					if(next.equals(OPEN_BRACKET)){
						ParseNode newGroup = new ParseNode(GROUP);
						System.out.println("New Node: " + newGroup.getName());
						ParseNode processedNode = recursiveCommandBuilder(newGroup);
						current.addChild(processedNode);
					}
					else if(next.equals(CLOSED_BRACKET)){
						System.out.println("Out of place end bracket");
					}
					else{
						ParseNode newNode = new ParseNode(next);
						System.out.println("New Node: " + newNode.getName());
						ParseNode processedNode = recursiveCommandBuilder(newNode);
						current.addChild(processedNode);
						System.out.println("Added node: " + processedNode.getName());
					}
				}
			}
			
		case "Constant":
			return current;
		case "Variable":
			if(!myProgramVariables.contains(nodeName))
				System.out.println("Variable not in table");
			return current;
		default:
			System.out.println("YOU SCREWED UP SOMEWHERE");
		}
		
//		run regex to recognize the pattern;
//		switch "pattern"
//		case make:
//		case to:
//		case loop:
//		case command:
//			group here
//		case constant:
//		case group:
//		case variable:
//		
		return null;
	}
	
	private boolean atEndOfString(){
		return (myCurIndex == myCurProgramArray.length-1);
	}
	
	
	
}
