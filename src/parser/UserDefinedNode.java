package parser;

import exceptions.ParserException;

/**
 */
public class UserDefinedNode extends CommandNode {

	/**UserDefinedNode handles command that has been defined by the user.
	 * Constructor for UserDefinedNode.
	 * @param name String
	 * @param parser Parser
	 * @param commandType String
	 */
	public UserDefinedNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	/**
	 * Method finishProcessing.
	 * @return ParseNode
	 * @throws ParserException
	 */
	@Override
	public ParseNode finishProcessing() throws ParserException{
		int loopTimes = myParser.getUserMethodParams(getName());
		myParser.retrieveChildren(this, loopTimes);
		return this;
	}

}
