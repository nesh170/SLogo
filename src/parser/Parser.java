package parser;

import java.util.ArrayList;
import java.util.Arrays;
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
	public static final String MAKEVARIABLE = "MakeVariable";
	public static final String REPEAT = "Repeat";
	public static final String CONSTANT = "Constant";
	public static final String VARIABLE = "Variable";
	

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
		myCurIndex = -1;
		String processed = preProcessString(program);
		if (processed == null) {
			return null;
		}
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
		if (splitProgram.length < 1 | program.length() < 1) {
			//System.out.println("String empty or full of spaces");
			return null;
		}
		List<Character> programArray = new ArrayList<Character>();
		List<Character> correctArray = new ArrayList<Character>();
		for (char c : program.toCharArray()) {
			programArray.add(c);
		}

		deleteComments(programArray, correctArray);
		StringBuilder builder = new StringBuilder(correctArray.size());
		for (Character c : correctArray) {
			builder.append(c);
		}
		String toDeleteWhiteSpace = builder.toString();
		ArrayList<String> voidOfSpace = new ArrayList<>();
		List<String> spaceProcessing = Arrays.asList(toDeleteWhiteSpace.split(" "));
		for(String e: spaceProcessing){
			if(!e.equals("")){
				//System.out.println("the valid command is "+e);
				voidOfSpace.add(e);
			}
		}
		String[] validCommand = new String[voidOfSpace.size()];
		for(int i = 0; i < voidOfSpace.size(); i++){
			validCommand[i] = voidOfSpace.get(i);
		}
		myCurProgramArray = validCommand;
		return builder.toString();
	}
	
	private void deleteComments(List<Character> originalArray, List<Character> processedArray){
		boolean check = false;
		for (int i = 0; i < originalArray.size(); i++) {
			if (originalArray.get(i) == '#') {
				check = true;
			}
			if (originalArray.get(i) == '\n') {
				check = false;
				continue;
			}
			if (!check) {
				processedArray.add(originalArray.get(i));
			}
		}
		return;
	}

	public ParseNode recursiveCommandBuilder(ParseNode current)
			throws ParserException {
			return current.finishProcessing();
	}

	// duplicated with method below, refactor
	public void getGroupKids(ParseNode groupLeader) throws ParserException {
		boolean groupComplete = false;
		while (!groupComplete) {
			checkStringEndsEarly("Invalid grouping format: missing end bracket.");
			String next = myCurProgramArray[++myCurIndex];
			if (next.equals(CLOSED_BRACKET)) {
				groupComplete = true;
			} 
			else{
				retrieveGenericKids(groupLeader, next);
			}
		}
	}

	public void retrieveGenericKids(ParseNode current, String next) throws ParserException{
		if (next.equals(OPEN_BRACKET)) {
			ParseNode newGroup = NodeFactory.createNode(myRegex, GROUP, this);
			ParseNode processedNode = recursiveCommandBuilder(newGroup);
			current.addChild(processedNode);
		}
		else {
			ParseNode newNode = NodeFactory.createNode(myRegex, next, this);
			System.out.println("New Node: " + newNode.getName());
			ParseNode processedNode = recursiveCommandBuilder(newNode);
			current.addChild(processedNode);
		}
	}
	
	public void retrieveChildren(ParseNode current, int loopTimes)
			throws ParserException {
		while (current.getChildCount() < loopTimes) {
			checkStringEndsEarly("Missing parameters for command: "
						+ current.getName());
			String next = myCurProgramArray[++myCurIndex];
			if (next.equals(CLOSED_BRACKET)) {
				throw new ParserException("Insufficient arguemnts for command "
						+ current.getName()
						+ " before encountering end bracket.");
			} else {
				retrieveGenericKids(current, next);
			}
		}
	}
	
	public void checkStringEndsEarly(String error) throws ParserException{
		if (atEndOfString()) {
			throw new ParserException(error);
		}
	}

	public boolean atEndOfString() {
		return (myCurIndex == myCurProgramArray.length - 1);
	}

}
