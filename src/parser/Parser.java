package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.*;
import Constants.Constants;

public class Parser {
	public static final String USER_DEFINED = "UserDefined";
	public static final String OPEN_BRACKET = "[";
	public static final String CLOSED_BRACKET = "]";
	public static final String GROUP = "Group";
	public static final String REPCOUNT = ":repcount";
	public static final String TO = "MakeUserInstruction";

	private Regex myRegex;
	private int myCurIndex;
	private String[] myCurProgramArray;
	private Set<String> myProgramVariables;
	private Map<String, Integer> myProgMethodsAndParams;

	public Parser() {
		myRegex = new Regex(Constants.SYNTAX, Constants.DEFAULT_LANGUAGE);
		myCurIndex = -1;
		myProgramVariables = new HashSet<>();
		myProgMethodsAndParams = new HashMap<>();
	}

	public Regex getRegex() {
		return myRegex;
	}
	
	public void incrementCurIndex(){
		myCurIndex += 1;
	}
	
	public int getCurIndex(){
		return myCurIndex;
	}
	
	public String getCurrentElement(){
		return myCurProgramArray[myCurIndex];
	}
	
	public String getElementAtIndex(int index){
		return myCurProgramArray[index];
	}
	
	public String getNextElement() {
		if(!atEndOfString()){
			return myCurProgramArray[myCurIndex+1];
		}
		return null;
	}
	
	public void addVariableToTable(String var){
		myProgramVariables.add(var);
	}
	
	public boolean variableExists(String var){
		return myProgramVariables.contains(var);
	}
	
	public void addMethod(String name, int numParams){
		myProgMethodsAndParams.put(name, numParams);
	}
	
	public boolean methodExists(String name){
		return myProgMethodsAndParams.containsKey(name);
	}
	
	public int getUserMethodParams(String methodName){
		return myProgMethodsAndParams.get(methodName);
	}

	public List<ParseNode> parse(String program) throws ParserException {
		String processed = preProcessString(program);
		myCurIndex = -1;
		if (processed == null) {
			return null;
		}
		myCurProgramArray = processed.split(" ");
		System.out.println("The length is "+myCurProgramArray.length);
		//ArrayList<String> programArray = new ArrayList<>(Arrays.asList(myCurProgramArray));
		List<ParseNode> topNodes = new ArrayList<>();
		// loop through the string array until it's empty{
		while (myCurIndex < myCurProgramArray.length - 1) {
			ParseNode curNode = NodeFactory.createNode(myRegex, myCurProgramArray[++myCurIndex], this);
			//ParseNode curNode = new ParseNode(myCurProgramArray[++myCurIndex]);
			System.out.println("Root Node: " + curNode.getName());
			ParseNode processedNode = recursiveCommandBuilder(curNode);
			topNodes.add(processedNode);
		}
		return topNodes;
	}

	public void changeLanguage(String language) {
		myRegex.changeLanguagePattern(language);
	}

	public String preProcessString(String program) {
		String[] splitProgram = program.split(" ");
		// System.out.println("originally the program is " + program);
		// check if string is empty
		if (splitProgram.length < 1 | program.length() < 1) {
			System.out.println("String empty or full of spaces");
			return null;
		}
		List<Character> programArray = new ArrayList<Character>();
		List<Character> correctArray = new ArrayList<Character>();
		for (char c : program.toCharArray()) {
			programArray.add(c);
		}

		boolean deleteFlag = false;
		for (int i = 0; i < programArray.size(); i++) {
			if (programArray.get(i) == '#') {
				deleteFlag = true;
			}
			if (programArray.get(i) == '\n') {
				deleteFlag = false;
				continue;
			}
			if (!deleteFlag) {
				correctArray.add(programArray.get(i));
			}
		}
		StringBuilder builder = new StringBuilder(correctArray.size());
		for (Character c : correctArray) {
			builder.append(c);
		}
		return builder.toString();
	}

	public ParseNode recursiveCommandBuilder(ParseNode current)
			throws ParserException {
			return current.finishProcessing();
	}

	// duplicated with method below, refactor
	public void getGroupKids(ParseNode groupLeader) throws ParserException {
		boolean groupComplete = false;
		while (!groupComplete) {
			if (atEndOfString()) {
				System.out
				.println("Missing parameters for a something in a group");
				throw new ParserException(
						"Invalid format: missing end bracket.");
			}
			String next = myCurProgramArray[++myCurIndex];
			if (next.equals(CLOSED_BRACKET)) {
				System.out.println("Group ended");
				groupComplete = true;
			} else if (next.equals(OPEN_BRACKET)) {
				ParseNode newGroup = NodeFactory.createNode(myRegex, GROUP, this);
				System.out.println("The beginning of Group node: " + newGroup.getName());
				ParseNode processedNode = recursiveCommandBuilder(newGroup);
				groupLeader.addChild(processedNode);
			} else {
				ParseNode newNode = NodeFactory.createNode(myRegex, next, this);
				//ParseNode newNode = new ParseNode(next);
				System.out.println("New Node: " + newNode.getName());
				ParseNode processedNode = recursiveCommandBuilder(newNode);
				groupLeader.addChild(processedNode);
				System.out.println("Added node: " + processedNode.getName());
			}
		}
	}

	public void retrieveChildren(ParseNode current, int loopTimes)
			throws ParserException {
		while (current.getChildCount() < loopTimes) {
			if (atEndOfString()) {
				System.out.println("Missing parameters for command: "
						+ current.getName());
				throw new ParserException("Missing parameters for command: "
						+ current.getName());
			}
			String next = myCurProgramArray[++myCurIndex];
			if (next.equals(OPEN_BRACKET)) {
				ParseNode newGroup = NodeFactory.createNode(myRegex, GROUP, this);
				//ParseNode newGroup = new ParseNode(GROUP);
				System.out.println("The beginning of Group node: " + newGroup.getName());
				ParseNode processedNode = recursiveCommandBuilder(newGroup);
				current.addChild(processedNode);
			} else if (next.equals(CLOSED_BRACKET)) {
				System.out.println("Out of place end bracket");
				throw new ParserException("Insufficient arguemnts for command "
						+ current.getName()
						+ " before encountering end bracket.");
			} else {
				ParseNode newNode = NodeFactory.createNode(myRegex, next, this);
				//ParseNode newNode = new ParseNode(next);
				System.out.println("New Node: " + newNode.getName());
				ParseNode processedNode = recursiveCommandBuilder(newNode);
				current.addChild(processedNode);
				System.out.println("Added node: " + processedNode.getName());
			}
		}
	}
	
	public void retrieveGenericKids(ParseNode current, String next) throws ParserException{
		if (next.equals(OPEN_BRACKET)) {
			ParseNode newGroup = NodeFactory.createNode(myRegex, GROUP, this);
			System.out.println("The beginning of Group node: " + newGroup.getName());
			ParseNode processedNode = recursiveCommandBuilder(newGroup);
			current.addChild(processedNode);
		}
		else {
			ParseNode newNode = NodeFactory.createNode(myRegex, next, this);
			System.out.println("New Node: " + newNode.getName());
			ParseNode processedNode = recursiveCommandBuilder(newNode);
			current.addChild(processedNode);
			System.out.println("Added node: " + processedNode.getName());
		}
	}

	public boolean atEndOfString() {
		return (myCurIndex == myCurProgramArray.length - 1);
	}

}
