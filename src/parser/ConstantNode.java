package parser;

import exceptions.*;

public class ConstantNode extends ParseNode{

	public ConstantNode(String name, Parser parser) {
		super(name, parser);
	}

	@Override
	public ParseNode finishProcessing() throws ParserException{
		return this;
	}

}
