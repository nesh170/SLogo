package parser;

import java.util.ArrayList;
import java.util.List;

import programBuilder.CommandFactory;
import programBuilder.ProgramBuilder;
import view.ViewAbstract;
import Model.MethodManager;
import Model.MultipleTurtles;
import Model.VariableManager;
import Statements.Statement;
import Constants.Constants;
import exceptions.*;

/**CommandNode is the node that provides implementation for normal 
 * commands (not including constant, group and to) in parsing and program building.
 * 
 * @author Yancheng, Sierra
 *
 */
public class CommandNode extends ParseNode {
	private String mySpecificCommand;
	private int myNumChildren;
	private List<List<Statement>> myParams;
	private List<Statement> curList;

	/**
	 * Constructor for CommandNode.
	 * @param name String
	 * @param parser Parser
	 * @param commandType String
	 */
	public CommandNode(String name, Parser parser, String commandType) {
		super(name, parser);
		mySpecificCommand = commandType;
	}

	/**This method collects the child nodes for the current node.
	 * Method finishProcessing.
	 * @return ParseNode
	 * @throws ParserException
	 */
	@Override
	public ParseNode finishProcessing() throws ParserException {
		myParser.retrieveChildren(this, getNumParams());
		return this;
	}

	/** This method fetches the expected number of the child nodes.
	 * Method getNumParams.
	 * @return int
	 */
	public int getNumParams() {
		int loopTimes = 0;
		try {
			loopTimes = Constants.getNumParam(mySpecificCommand);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return loopTimes;
	}

	/** This method adds child commands to the parent's list and calls recursion on 
	 * each child. 
	 * Method buildStatement.
	 * @param builder ProgramBuilder
	 * @param myView ViewAbstract
	 * @param myTurtleManager MultipleTurtles
	 * @param myVariableManager VariableManager
	 * @param myRegex Regex
	 * @param myMethodManager MethodManager
	 * @param colors List<String>
	 * @param shapes List<String>
	 * @return Statement
	 * @throws ParserException
	 */
	@Override
	public Statement buildStatement(ProgramBuilder builder,
			ViewAbstract myView, MultipleTurtles myTurtleManager,
			VariableManager myVariableManager, Regex myRegex,
			MethodManager myMethodManager, List<String> colors,
			List<String> shapes) throws ParserException {

		List<List<Statement>> paramLists = new ArrayList<>();
		List<Statement> baseList = new ArrayList<>();
		paramLists.add(baseList);
		curList = baseList;
		myNumChildren = this.getChildCount();
		doSpecificPrep(baseList);
		for (int i = 0; i < myNumChildren; i++) {
			ParseNode curChild = this.getChildren().get(i);
			if (curChild.getName().equals(Parser.GROUP)) {
				if (i != 0) {
					curList = new ArrayList<>();
					paramLists.add(curList);
				}
				for (ParseNode groupKid : curChild.getChildren()) {
					curList.add(builder.recursiveStatementBuilder(groupKid));
				}
			} else {
				baseList.add(builder.recursiveStatementBuilder(curChild));
			}
		}

		String commandType = myRegex.matchCommand(this.getName());
		return CommandFactory.generateCommand(commandType, paramLists, myView,
				myTurtleManager, myVariableManager, myRegex, myMethodManager,
				colors, shapes);
	}
	
	/**
	 * Method doSpecificPrep.
	 * @param base List<Statement>
	 */
	public void doSpecificPrep(List<Statement> base){
	}
	
	public void resetCurList(){
		curList = new ArrayList<>();
	}
	
	public void addCurListToParams(){
		myParams.add(curList);
	}
	
	public void decrementNumChildren(){
		myNumChildren--;
	}

}
