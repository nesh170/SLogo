package parser;

import Constants.Constants;
import exceptions.*;

public class CommandNode extends ParseNode {

	private String mySpecificCommand;
	
	public CommandNode(String name, Parser parser, String commandType) {
		super(name, parser);
		mySpecificCommand = commandType;
	}

	@Override
	public ParseNode finishProcessing() throws ParserException{
		int loopTimes = 0;
			try {
				loopTimes = Constants.getNumParam(mySpecificCommand);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		myParser.retrieveChildren(this, loopTimes);
		return this;
	}

}
