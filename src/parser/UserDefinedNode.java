package parser;

import exceptions.ParserException;

public class UserDefinedNode extends CommandNode {

	public UserDefinedNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	@Override
	public ParseNode finishProcessing() throws ParserException{
		int loopTimes = myParser.getUserMethodParams(getName());
		myParser.retrieveChildren(this, loopTimes);
		return this;
	}

}
