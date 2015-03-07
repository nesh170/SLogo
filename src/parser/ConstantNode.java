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

public class ConstantNode extends ParseNode{

	public ConstantNode(String name, Parser parser) {
		super(name, parser);
	}

	@Override
	public ParseNode finishProcessing() throws ParserException{
		return this;
	}

	@Override
	public Statement buildStatement(ProgramBuilder builder,ViewAbstract myView,
			MultipleTurtles myTurtleManager, VariableManager myVariableManager,
			Regex myRegex, MethodManager myMethodManager, List<String> colors,
			List<String> shapes) throws ParserException {
		return new Value(Double.parseDouble(getName()));
	}

}
