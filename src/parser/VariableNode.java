package parser;

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

}
