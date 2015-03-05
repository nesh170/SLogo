package parser;

import exceptions.*;

public class CommandNode extends ParseNode {

	public CommandNode(String name, Parser parser) {
		super(name, parser);
	}

	@Override
	public ParseNode finishProcessing() throws ParserException{
		// TODO Auto-generated method stub
		return null;
	}

}
