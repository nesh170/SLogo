package parser;

import exceptions.ParserException;

public class RepeatNode extends CommandNode {

	public RepeatNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	@Override
	public ParseNode finishProcessing() throws ParserException{
		myParser.addVariableToTable(Parser.REPCOUNT);
		myParser.retrieveChildren(this, getNumParams());
		return this;
	}

}
