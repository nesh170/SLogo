package parser;

import exceptions.ParserException;

/**RepeatNode handles commands that involve for loops.
 * 
 * @author Sierra, Yancheng
 *
 */
public class RepeatNode extends CommandNode {

	/**
	 * Constructor for RepeatNode.
	 * @param name String
	 * @param parser Parser
	 * @param commandType String
	 */
	public RepeatNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	/**
	 * Method finishProcessing.
	 * @return ParseNode
	 * @throws ParserException
	 */
	@Override
	public ParseNode finishProcessing() throws ParserException{
		getMyParser().addVariableToTable(Parser.REPCOUNT);
		getMyParser().retrieveChildren(this, getNumParams());
		return this;
	}

}
