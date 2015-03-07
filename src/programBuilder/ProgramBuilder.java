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

/**This class traverses the built syntax tree and converts each node to a command statement.
 * @author Yancheng, Sierra
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
			return node.buildStatement(this, myView, myTurtleManager,
					myVariableManager, myRegex, myMethodManager, myColors,
					myShapes);
	}
}
