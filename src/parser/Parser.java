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
	private static final String USER_DEFINED = "UserDefined";
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSED_BRACKET = "]";
	private static final String GROUP = "Group";
	private static final String REPCOUNT = ":repcount";
	private static final String TO = "MakeUserInstruction";

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

	public List<ParseNode> parse(String program) throws ParserException {
		String processed = preProcessString(program);
		if (processed == null) {
			return null;
		}
		myCurProgramArray = processed.split(" ");
		//ArrayList<String> programArray = new ArrayList<>(Arrays.asList(myCurProgramArray));
		List<ParseNode> topNodes = new ArrayList<>();
		// loop through the string array until it's empty{
		while (myCurIndex < myCurProgramArray.length - 1) {
			//NODE FACTORY HERE
			ParseNode curNode = new ParseNode(myCurProgramArray[++myCurIndex]);
			System.out.println("Root Node: " + curNode.getName());
			ParseNode processedNode = recursiveCommandBuilder(curNode);
			// System.out.println("Printing in parse while loop");
			topNodes.add(processedNode);
			// System.out.println("Added node: " + processedNode.getName());
		}
		// create new node; //the index of the string is index + 1
		// increment index;
		// call parsenode on the current node;
		// add current node (head node of each independent command) to the
		// statement list;
		// }
		myCurIndex = -1;
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
		// System.out.println("the programarray is " + programArray);

		boolean deleteFlag = false;
		for (int i = 0; i < programArray.size(); i++) {
			if (programArray.get(i) == '#') {
				deleteFlag = true;
			}
			if (programArray.get(i) == '\n') {
				// System.out.println("ever comes here");
				deleteFlag = false;
				continue;
			}
			if (!deleteFlag) {
				correctArray.add(programArray.get(i));
				// System.out.println("added is " + programArray.get(i));
			}
		}
		// System.out.println("the correct array is");
		StringBuilder builder = new StringBuilder(correctArray.size());
		for (Character c : correctArray) {
			// System.out.print((c));
			builder.append(c);
		}
		// System.out.println();
		// pre-process the string to remove comments
		return builder.toString();
	}

	private ParseNode recursiveCommandBuilder(ParseNode current)
			throws ParserException {
		String nodeName = current.getName();

		String type = myRegex.matchSyntax(nodeName);
		//potential null pointer exception
		switch (type) {
		case "Command":
			String commandType = myRegex.matchCommand(nodeName);
			System.out.println("The type for the node " + nodeName + " is "
					+ commandType);
			// check if commmandtype is null, this means it may be a user
			// defined command and call appropriate helper
			if (commandType == null) {
				// check if user defined method and act accordingly
				//if user begins with bracket then screwed
				if (nodeName.equals(GROUP)) {
					commandType = GROUP;
				} else {
					// System.out.println("Command type is null");
					// return null;
					throw new ParserException("Invalid Command");
				}
			}
			//need to define user-defined command beforehand
			//and the numParam needs to be accessible from the parse method table
			int loopTimes = 0;
			if(!commandType.equals(USER_DEFINED)){
				try {
					loopTimes = Constants.getNumParam(commandType);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			switch (commandType) {
			//potentially catch missing parameters
			case USER_DEFINED:
				loopTimes = myProgMethodsAndParams.get(nodeName);
				retrieveChildren(current, loopTimes);
				break;
			case "MakeVariable":
				if (atEndOfString()) {
					System.out.println("Missing the variable for make");
					throw new ParserException(
							"Insufficient arguements for make command.");
				}
				String variable = myCurProgramArray[myCurIndex + 1];
				if (!(myRegex.matchSyntax(variable).equals("Variable"))) {
					System.out
					.println("Thing after make is not of variable format");
					throw new ParserException(
							"Incorrect format for variable declared after make.");
				}
				//note that we never clear the program variable table
				myProgramVariables.add(variable);
				System.out.println("Added variable to variable set. "
						+ myProgramVariables.contains(variable));
				retrieveChildren(current, loopTimes);
				break;
			case TO:
				if (atEndOfString()) {
					System.out.println("Missing the variable for to");
					throw new ParserException(
							"Insufficient arguements for make command.");
				}	
				String methodName = myCurProgramArray[myCurIndex+1];
				ParseNode newNode = new ParseNode(myCurProgramArray[++myCurIndex]);
				current.addChild(newNode);
				retrieveChildren(current, loopTimes);
				myProgMethodsAndParams.put(methodName, current.getChildren().get(1).getNumChildren());
				System.out.println("The user instruction is "+ methodName);
				System.out.println("In the myProgmehotdpasdfslkfjs the num is "+current.getChildren().get(1).getNumChildren());
				System.out.println("In the myProgmehotdpasd the num is "+current.getChildren().get(2).getNumChildren());
				break;
			case GROUP:
				getGroupKids(current);
				break;
			case "Repeat":
				myProgramVariables.add(REPCOUNT);
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
			//remember to throw exception here
			if (!myProgramVariables.contains(nodeName))
				System.out.println("Variable not in table");
			return current;
		default:
			System.out.println("YOU SCREWED UP SOMEWHERE");
		}
		throw new ParserException("Type mismatch on element: " + nodeName);
	}

	// duplicated with method below, refactor
	private void getGroupKids(ParseNode groupLeader) throws ParserException {
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
				ParseNode newGroup = new ParseNode(GROUP);
				System.out.println("The beginning of Group node: " + newGroup.getName());
				ParseNode processedNode = recursiveCommandBuilder(newGroup);
				groupLeader.addChild(processedNode);
			} else {
				ParseNode newNode = new ParseNode(next);
				System.out.println("New Node: " + newNode.getName());
				ParseNode processedNode = recursiveCommandBuilder(newNode);
				groupLeader.addChild(processedNode);
				System.out.println("Added node: " + processedNode.getName());
			}
		}
	}

	private void retrieveChildren(ParseNode current, int loopTimes)
			throws ParserException {
		while (current.getNumChildren() < loopTimes) {
			if (atEndOfString()) {
				System.out.println("Missing parameters for command: "
						+ current.getName());
				throw new ParserException("Missing parameters for command: "
						+ current.getName());
			}
			String next = myCurProgramArray[++myCurIndex];
			if (next.equals(OPEN_BRACKET)) {
				//NODE FACTORY HERE
				ParseNode newGroup = new ParseNode(GROUP);
				System.out.println("The beginning of Group node: " + newGroup.getName());
				ParseNode processedNode = recursiveCommandBuilder(newGroup);
				current.addChild(processedNode);
			} else if (next.equals(CLOSED_BRACKET)) {
				System.out.println("Out of place end bracket");
				throw new ParserException("Insufficient arguemnts for command "
						+ current.getName()
						+ " before encountering end bracket.");
			} else {
				//NODE FACTORY HERE
				ParseNode newNode = new ParseNode(next);
				System.out.println("New Node: " + newNode.getName());
				ParseNode processedNode = recursiveCommandBuilder(newNode);
				current.addChild(processedNode);
				System.out.println("Added node: " + processedNode.getName());
			}
		}
	}

	private boolean atEndOfString() {
		return (myCurIndex == myCurProgramArray.length - 1);
	}

}
