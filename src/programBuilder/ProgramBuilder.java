package programBuilder;

import java.util.List;

import exceptions.ParserException;
import Model.MethodManager;
import Model.Program;
import Model.MultipleTurtles;
import Model.VariableManager;
import Statements.*;
import view.ViewAbstract;
import parser.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgramBuilder.
 */
public class ProgramBuilder {
	
	/** The my view. */
	private ViewAbstract myView;
	
	/** The my turtle manager. */
	private MultipleTurtles myTurtleManager;
	
	/** The my variable manager. */
	private VariableManager myVariableManager;
	
	/** The my regex. */
	private Regex myRegex;
	
	/** The my method manager. */
	private MethodManager myMethodManager;
	
	/** The my colors. */
	private List<String> myColors;
	
	/** The my shapes. */
	private List<String> myShapes;

	/**
	 * Instantiates a new program builder.
	 *
	 * @param view the view
	 * @param turtles the turtles
	 * @param vars the vars
	 * @param regex the regex
	 * @param methodManager the method manager
	 * @param colors the colors
	 * @param shapes the shapes
	 */
	public ProgramBuilder(ViewAbstract view, MultipleTurtles turtles,
			VariableManager vars, Regex regex, MethodManager methodManager,
			List<String> colors, List<String> shapes) {
		myView = view;
		myTurtleManager = turtles;
		myVariableManager = vars;
		myRegex = regex;
		myMethodManager = methodManager;
		myColors = colors;
		myShapes = shapes;
	}

	/**
	 * Gets the variable manager.
	 *
	 * @return the variable manager
	 */
	public VariableManager getVariableManager() {
		return myVariableManager;
	}

	/**
	 * Builds the program.
	 *
	 * @param topNodes the top nodes
	 * @return the program
	 * @throws ParserException the parser exception
	 */
	public Program buildProgram(List<ParseNode> topNodes)
			throws ParserException {
		Program newProg = new Program(myView);
		for (ParseNode curNode : topNodes) {
			newProg.addStatment(recursiveStatementBuilder(curNode));
		}
		return newProg;
	}

	/**
	 * Recursive statement builder.
	 *
	 * @param node the node
	 * @return the statement
	 * @throws ParserException the parser exception
	 */
	public Statement recursiveStatementBuilder(ParseNode node)
			throws ParserException {
//		String nodeName = node.getName();
		// get type string: if constant --> "constant", if variable -->
		// "variable", if userdefined --> "user"
		// other wise type = node name
//		String type = myRegex.matchSyntax(nodeName);
//		List<List<Statement>> paramLists = new ArrayList<>();
//		switch (type) {
//		case "Constant":
			return node.buildStatement(this, myView, myTurtleManager,
					myVariableManager, myRegex, myMethodManager, myColors,
					myShapes);
//		case "Variable":
//			return node.buildStatement(this, myView, myTurtleManager,
//					myVariableManager, myRegex, myMethodManager, myColors,
//					myShapes);
//		default:
//			List<Statement> baseList = new ArrayList<>();
//			paramLists.add(baseList);
//			List<Statement> curList = baseList;
//			int looptimes = node.getChildCount();
//			if (node.getName().equals("to")) {
//				looptimes--;
//				baseList.add(new MethodName(node.getChildren().get(0).getName()));
//				node.removeChild(0);
//				curList = new ArrayList<>();
//				paramLists.add(curList);
//			}
//			for (int i = 0; i < looptimes; i++) {
//				ParseNode curChild = node.getChildren().get(i);
//				if (curChild.getName().equals("Group")) {
//					if (i != 0) {
//						curList = new ArrayList<>();
//						paramLists.add(curList);
//					}
//					for (ParseNode groupKid : curChild.getChildren()) {
//						curList.add(recursiveStatementBuilder(groupKid));
//					}
//				} else {
//					baseList.add(recursiveStatementBuilder(curChild));
//				}
//			}
//			return node.buildStatement(this, myView, myTurtleManager,
//					myVariableManager, myRegex, myMethodManager, myColors,
//					myShapes);
//		}
//		String commandType = myRegex.matchCommand(nodeName);
//		// if commandType is null, check if it is a user defined before sending
//		// to command factory
//		return CommandFactory.generateCommand(commandType, paramLists, myView,
//				myTurtleManager, myVariableManager, myRegex, myMethodManager,
//				myColors, myShapes);
	}
}
