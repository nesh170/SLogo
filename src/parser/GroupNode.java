package parser;

import exceptions.ParserException;

/**Group node handles groups in the command string defined by brackets.
 * 
 * @author Yancheng, Sierra
 *
 */
public class GroupNode extends CommandNode{

	/**
	 * Constructor for GroupNode.
	 * @param name String
	 * @param parser Parser
	 * @param commandType String
	 */
	public GroupNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}

	/**
	 * Method finishProcessing.
	 * @return ParseNode
	 * @throws ParserException
	 */
	@Override
	public ParseNode finishProcessing() throws ParserException{
		myParser.getGroupKids(this);
		return this;
	}
}
