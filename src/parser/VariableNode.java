package parser;

import java.util.List;

import programBuilder.ProgramBuilder;
import view.ViewAbstract;
import Model.MethodManager;
import Model.MultipleTurtles;
import Model.VariableManager;
import Statements.Statement;
import Statements.Variable;
import exceptions.*;
import Constants.*;

/**
 * 
 * @author Yancheng, Sierra
 *
 */
public class VariableNode extends ParseNode {

	/**VariableNode handles variables in the command.
	 * Constructor for VariableNode.
	 * @param name String
	 * @param parser Parser
	 */
	public VariableNode(String name, Parser parser) {
		super(name, parser);
	}

	/**
	 * Method finishProcessing.
	 * @return ParseNode
	 * @throws ParserException
	 */
	@Override
	public ParseNode finishProcessing() throws ParserException{
		if(!getMyParser().variableExists(getName())){
			throw new ParserException(Constants.MISSING_VARIABLE_MESSAGE);
		}
		return this;
	}

	/**
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
	public Statement buildStatement(ProgramBuilder builder,ViewAbstract myView,
			MultipleTurtles myTurtleManager, VariableManager myVariableManager,
			Regex myRegex, MethodManager myMethodManager, List<String> colors,
			List<String> shapes) throws ParserException {
		return new Variable(getName(), myVariableManager);
	}

}
