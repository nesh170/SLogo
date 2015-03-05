package parser;

import exceptions.ParserException;

public class GroupNode extends CommandNode{

	public GroupNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}

	@Override
	public ParseNode finishProcessing() throws ParserException{
		myParser.getGroupKids(this);
		return this;
	}
}
