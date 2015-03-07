package parser;

import java.util.List;

import programBuilder.ProgramBuilder;
import view.ViewAbstract;
import Model.MethodManager;
import Model.MultipleTurtles;
import Model.VariableManager;
import Statements.Statement;
import Statements.Value;
import exceptions.*;

/**
 * 
 * @author Yancheng, Sierra
 *
 */
public class ConstantNode extends ParseNode{

	/**
	 * Constructor for ConstantNode.
	 * @param name String
	 * @param parser Parser
	 */
	public ConstantNode(String name, Parser parser) {
		super(name, parser);
	}

	/**For a constant node, since it does not have any child nodes, it will
	 * return itself.
	 * Method finishProcessing.
	 * @return ParseNode
	 * @throws ParserException
	 */
	@Override
	public ParseNode finishProcessing() throws ParserException{
		return this;
	}

	/** For the constant node, it returns a value statement which contains 
	 * its numerical value.
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
		return new Value(Double.parseDouble(getName()));
	}

}
