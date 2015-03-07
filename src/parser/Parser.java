package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.*;
import Constants.*;

/** The parser analyzes the the command that the user types in 
 * to build a syntax tree consisted of nodes with command names. 
 * 
 * @author Yancheng, Sierra
 *
 */
public class Parser {
	
	/** The Constant USER_DEFINED. */
	public static final String USER_DEFINED = "UserDefined";
	
	/** The Constant OPEN_BRACKET. */
	public static final String OPEN_BRACKET = "[";
	
	/** The Constant CLOSED_BRACKET. */
	public static final String CLOSED_BRACKET = "]";
	
	/** The Constant GROUP. */
	public static final String GROUP = "Group";
	
	/** The Constant REPCOUNT. */
	public static final String REPCOUNT = ":repcount";
	
	/** The Constant TO. */
	public static final String TO = "MakeUserInstruction";
	
	/** The Constant MAKEVARIABLE. */
	public static final String MAKEVARIABLE = "MakeVariable";
	
	/** The Constant REPEAT. */
	public static final String REPEAT = "Repeat";
	
	/** The Constant CONSTANT. */
	public static final String CONSTANT = "Constant";
	
	/** The Constant VARIABLE. */
	public static final String VARIABLE = "Variable";
	
	/** The Constant COMMAND. */
	public static final String COMMAND = "Command";

	/** The my regex. */
	private Regex myRegex;
	
	/** The my cur index. */
	private int myCurIndex;
	
	/** The my cur program array. */
	private String[] myCurProgramArray;
	
	/** The my program variables. */
	private Set<String> myProgramVariables;
	
	/** The my prog methods and params. */
	private Map<String, Integer> myProgMethodsAndParams;

	/**
	 * Instantiates a new parser.
	 */
	public Parser() {
		myRegex = new Regex(Constants.SYNTAX, Constants.DEFAULT_LANGUAGE);
		myCurIndex = -1;
		myProgramVariables = new HashSet<>();
		myProgMethodsAndParams = new HashMap<>();
	}

	/**
	 * Gets the regex.
	 *
	 * @return the regex
	 */
	public Regex getRegex() {
		return myRegex;
	}
	
	/**
	 * Increment the current index.
	 */
	public void incrementCurIndex(){
		myCurIndex += 1;
	}
	
	/**
	 * Gets the current index.
	 *
	 * @return the current index
	 */
	public int getCurIndex(){
		return myCurIndex;
	}
	
	/**
	 * Gets the current element.
	 *
	 * @return the current element
	 */
	public String getCurrentElement(){
		return myCurProgramArray[myCurIndex];
	}
	
	/**
	 * Gets the element at index.
	 *
	 * @param index the index
	 * @return the element at index
	 */
	public String getElementAtIndex(int index){
		return myCurProgramArray[index];
	}
	
	/**
	 * Gets the next element.
	 *
	 * @return the next element
	 */
	public String getNextElement() {
		if(!atEndOfString()){
			return myCurProgramArray[myCurIndex+1];
		}
		return null;
	}
	
	/**
	 * Adds the variable to table.
	 *
	 * @param var the var
	 */
	public void addVariableToTable(String var){
		myProgramVariables.add(var);
	}
	
	/**
	 * Variable exists.
	 *
	 * @param var the var
	 * @return true, if successful
	 */
	public boolean variableExists(String var){
		return myProgramVariables.contains(var);
	}
	
	/**
	 * Adds the method.
	 *
	 * @param name the name
	 * @param numParams the num params
	 */
	public void addMethod(String name, int numParams){
		myProgMethodsAndParams.put(name, numParams);
	}
	
	/**
	 * Method exists.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean methodExists(String name){
		return myProgMethodsAndParams.containsKey(name);
	}
	
	/**
	 * Gets the user method params.
	 *
	 * @param methodName the method name
	 * @return the user method params
	 */
	public int getUserMethodParams(String methodName){
		return myProgMethodsAndParams.get(methodName);
	}

	/**
	 * This method starts the parsing process and creates a head node for each 
	 * independent command
	 *
	 * @param program the program
	 * @return a list of independent command nodes
	 * @throws ParserException the parser exception
	 */
	public List<ParseNode> parse(String program) throws ParserException {
		myCurIndex = -1;
		String processed = preProcessString(program);
		if (processed == null) {
			return null;
		}
		List<ParseNode> topNodes = new ArrayList<>();
		// loop through the string array until it's empty{
		while (myCurIndex < myCurProgramArray.length - 1) {
			ParseNode curNode = NodeFactory.createNode(myRegex, myCurProgramArray[++myCurIndex], this);
			//ParseNode curNode = new ParseNode(myCurProgramArray[++myCurIndex]);
			//System.out.println("Root Node: " + curNode.getName());
			ParseNode processedNode = recursiveCommandBuilder(curNode);
			topNodes.add(processedNode);
		}
		return topNodes;
	}

	/**
	 * Change language.
	 *
	 * @param language the language
	 */
	public void changeLanguage(String language) {
		myRegex.changeLanguagePattern(language);
	}

	/**
	 * This method pre-processes the user-typed command string and get rids of 
	 * comments and new lines in the string. It also splits up the string into 
	 * a string array based on white spaces. 
	 *
	 * @param user-typed command string
	 * @return the pre-processed string
	 */
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
	
	/**
	 * This method deletes the comments in the command string.
	 *
	 * @param originalArray the original array
	 * @param processedArray the processed array
	 */
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

	/**
	 * A generic call the triggers the parsing process on the current node.
	 *
	 * @param current the current
	 * @return the parses the node
	 * @throws ParserException the parser exception
	 */
	public ParseNode recursiveCommandBuilder(ParseNode current)
			throws ParserException {
			return current.finishProcessing();
	}

	// duplicated with method below, refactor
	/**
	 * In the syntax tree, if a node is a group then it will 
	 * process its child nodes here.
	 *
	 * @param groupLeader the group leader
	 * @return the group kids
	 * @throws ParserException the parser exception
	 */
	public void getGroupKids(ParseNode groupLeader) throws ParserException {
		boolean groupComplete = false;
		while (!groupComplete) {
			checkStringEndsEarly(ErrorMessage.MISSING_END_BRACKET.getVal());
			String next = myCurProgramArray[++myCurIndex];
			if (next.equals(CLOSED_BRACKET)) {
				groupComplete = true;
			} 
			else{
				retrieveGenericKids(groupLeader, next);
			}
		}
	}

	/**
	 * This is the generic method for nodes to add their child nodes and calls the 
	 * method on the child nodes to recursively build the tree. 
	 *
	 * @param current the current
	 * @param next the next
	 * @throws ParserException the parser exception
	 */
	public void retrieveGenericKids(ParseNode current, String next) throws ParserException{
		if (next.equals(OPEN_BRACKET)) {
			ParseNode newGroup = NodeFactory.createNode(myRegex, GROUP, this);
			ParseNode processedNode = recursiveCommandBuilder(newGroup);
			current.addChild(processedNode);
		}
		else {
			ParseNode newNode = NodeFactory.createNode(myRegex, next, this);
			ParseNode processedNode = recursiveCommandBuilder(newNode);
			current.addChild(processedNode);
		}
	}
	
	/**
	 * This method retrieves a node's expected number of child nodes and detects 
	 * end bracket syntax error.
	 *
	 * @param current the current
	 * @param loopTimes the loop times
	 * @throws ParserException the parser exception
	 */
	public void retrieveChildren(ParseNode current, int loopTimes)
			throws ParserException {
		while (current.getChildCount() < loopTimes) {
			checkStringEndsEarly(ErrorMessage.MISSING_PARAMETER.getVal()
						+ current.getName());
			String next = myCurProgramArray[++myCurIndex];
			if (next.equals(CLOSED_BRACKET)) {
				throw new ParserException(ErrorMessage.MISSING_PARAMETER.getVal()
						+ current.getName()
						+ ErrorMessage.BEFORE_END_BRACKET);
			} else {
				retrieveGenericKids(current, next);
			}
		}
	}
	
	/**
	 * Check if the command string reaches the end before expected command 
	 * elements are processed.
	 *
	 * @param error the error
	 * @throws ParserException the parser exception
	 */
	public void checkStringEndsEarly(String error) throws ParserException{
		if (atEndOfString()) {
			throw new ParserException(error);
		}
	}

	/**
	 * Check if it has reached the end of the command string.
	 *
	 * @return true, if at the end
	 */
	public boolean atEndOfString() {
		return (myCurIndex == myCurProgramArray.length - 1);
	}
}
