package parser;

import java.util.ArrayList;
import java.util.List;

import programBuilder.ProgramBuilder;
import view.ViewAbstract;
import exceptions.*;
import Model.MethodManager;
import Model.MultipleTurtles;
import Model.VariableManager;
import Statements.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ParseNode.
 */
public abstract class ParseNode {
	
	/** The my name. */
	private String myName;
	
	/** The my children. */
	private List<ParseNode> myChildren;
	
	/** The my parser. */
	protected Parser myParser;

	/**
	 * Instantiates a new parses the node.
	 *
	 * @param name the name
	 * @param parser the parser
	 */
	public ParseNode(String name, Parser parser) {
		myName = name;
		myChildren = new ArrayList<>();
		myParser = parser;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return myName;
	}

	/**
	 * Gets the child count.
	 *
	 * @return the child count
	 */
	public int getChildCount() {
		return myChildren.size();
	}

	/**
	 * Adds the child.
	 *
	 * @param newChild the new child
	 */
	public void addChild(ParseNode newChild) {
		myChildren.add(newChild);
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<ParseNode> getChildren() {
		return myChildren;
	}

	/**
	 * Removes the child.
	 *
	 * @param index the index
	 */
	public void removeChild(int index) {
		myChildren.remove(index);
	}

	/**
	 * Finish processing.
	 *
	 * @return the parses the node
	 * @throws ParserException the parser exception
	 */
	public abstract ParseNode finishProcessing() throws ParserException;

	/**
	 * Builds the statement.
	 *
	 * @param builder the builder
	 * @param myView the my view
	 * @param myTurtleManager the my turtle manager
	 * @param myVariableManager the my variable manager
	 * @param myRegex the my regex
	 * @param myMethodManager the my method manager
	 * @param colors the colors
	 * @param shapes the shapes
	 * @return the statement
	 * @throws ParserException the parser exception
	 */
	public abstract Statement buildStatement(ProgramBuilder builder, ViewAbstract myView,
			MultipleTurtles myTurtleManager, VariableManager myVariableManager,
			Regex myRegex, MethodManager myMethodManager, List<String> colors,
			List<String> shapes) throws ParserException;

}
