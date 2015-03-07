package parser;

import exceptions.ParserException;


/**
 * @author Sierra, Yancheng
 *
 */
public class ForNode extends CommandNode {

	public ForNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	@Override
	public ParseNode finishProcessing() throws ParserException{
		String variableForLoop = getMyParser().getElementAtIndex(getMyParser().getCurIndex()+2);
		getMyParser().addVariableToTable(variableForLoop);
		getMyParser().retrieveChildren(this, getNumParams());
		return this;
	}

}
