package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Constants.Constants;
import Model.Program;
public class Parser {
	private static final String USER_DEFINED = "UserDefined";
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSED_BRACKET = "]";
	private static final String GROUP = "Group";
	
	private Regex myRegex;
	private int myCurIndex;
	private String[] myCurProgramArray;
	private Set<String> myProgramVariables;
	private Map<String, Integer> myProgMethodsAndParams;

	public Parser(){
		myRegex = new Regex(Constants.SYNTAX, Constants.DEFAULT_LANGUAGE);
		myCurIndex = -1;
		myProgramVariables = new HashSet<>();
		myProgMethodsAndParams = new HashMap<>();
	}
	//returns a program, once that class is created
	public List<ParseNode> parse(String program){
//		preProcess the string;
		String processed = preProcessString(program);
		if(processed == null){
			return null;
		}
		myCurProgramArray = processed.split(" ");
		ArrayList<String> programArray = new ArrayList<>(Arrays.asList(myCurProgramArray));
		System.out.println(programArray);
//		create a program;
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
		myCurIndex = -1;
		return topNodes;
	}
	
	public void changeLanguage(String language){
		 
	}
	
	public String preProcessString(String program){
		String[] splitProgram = program.split(" ");
		System.out.println("originally the program is "+program);
//		check if string is empty
		if(splitProgram.length < 1 | program.length() < 1){
			System.out.println("String empty or full of spaces");
			return null;
		}
		List<Character> programArray = new ArrayList<Character>();
		List<Character> correctArray = new ArrayList<Character>();
		for(char c: program.toCharArray()){
			programArray.add(c);
		}
		System.out.println("the programarray is "+programArray);
		
		boolean deleteFlag = false;
		for(int i = 0; i < programArray.size(); i++){
			if(programArray.get(i) == '#'){
				deleteFlag = true;
			}
			if(programArray.get(i) == '\n'){
				System.out.println("ever comes here");
				deleteFlag = false;
				continue;
			}
			if(!deleteFlag){
				correctArray.add(programArray.get(i));
				System.out.println("added is "+programArray.get(i));
			}
		}
		System.out.println("the correct array is");
		StringBuilder builder = new StringBuilder(correctArray.size());
		for(Character c: correctArray){
			//System.out.print((c));
			builder.append(c);
		}
		System.out.println();
		
		//pre-process the string to remove comments
		return builder.toString();
	}
	
	private ParseNode recursiveCommandBuilder(ParseNode current){
		String nodeName = current.getName();
		
		String type = myRegex.matchSyntax(nodeName);
		
		switch(type){
		case "Command":
			String commandType = myRegex.matchCommand(nodeName);
			//check if commmandtype is null, this means it may be a user defined command and call appropriate helper
			if(commandType == null){
				//check if user defined method and act accordingly
				System.out.println("Command type is null");
				return null;
			}
			 int loopTimes = 0;
             try {
                 loopTimes = Constants.getNumParam(commandType);
             }
             catch (IllegalArgumentException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             catch (IllegalAccessException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
			switch(commandType){
			case USER_DEFINED:
				loopTimes = myProgMethodsAndParams.get(nodeName);
				retrieveChildren(current, loopTimes);
				break;
			case "MakeVariable":
				if(atEndOfString()){
					System.out.println("Missing the variable for make");
					return null;
				}
				String variable = myCurProgramArray[myCurIndex+1];
				if(!(myRegex.matchSyntax(variable).equals("Variable"))){
					System.out.println("Thing after make is not of variable format");
					return null;
				}
				myProgramVariables.add(variable);
				System.out.println("Added variable to variable set. "+ myProgramVariables.contains(variable));
				retrieveChildren(current, loopTimes);
				break;
			default:
				retrieveChildren(current, loopTimes);
				break;
			}
			return current;
			
		case "Constant":
			return current;
		case "Variable":
			if(!myProgramVariables.contains(nodeName))
				System.out.println("Variable not in table");
			return current;
		default:
			System.out.println("YOU SCREWED UP SOMEWHERE");
		}
		return null;
	}
	
	private void retrieveChildren(ParseNode current, int loopTimes) {
		while(current.getNumChildren() < loopTimes){
			if(atEndOfString()){
				System.out.println("Missing parameters for a command");
				//NOTE, ADD ERROR CHECKING HERE, WANT TO THROW ERROR
				return;
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
	
	private boolean atEndOfString(){
		return (myCurIndex == myCurProgramArray.length-1);
	}
	
	
	
}
