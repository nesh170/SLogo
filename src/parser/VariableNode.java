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

public class VariableNode extends ParseNode {

	public VariableNode(String name, Parser parser) {
		super(name, parser);
	}

	@Override
	public ParseNode finishProcessing() throws ParserException{
		if(!myParser.variableExists(getName())){
			throw new ParserException(Constants.MISSING_VARIABLE_MESSAGE);
		}
		return this;
	}

	@Override
	public Statement buildStatement(ProgramBuilder builder,ViewAbstract myView,
			MultipleTurtles myTurtleManager, VariableManager myVariableManager,
			Regex myRegex, MethodManager myMethodManager, List<String> colors,
			List<String> shapes) throws ParserException {
		return new Variable(getName(), myVariableManager);
	}

}
