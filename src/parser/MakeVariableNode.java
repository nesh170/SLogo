package parser;

import exceptions.ParserException;

public class MakeVariableNode extends CommandNode {

	public MakeVariableNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	@Override
	public ParseNode finishProcessing() throws ParserException{
		if (getMyParser().atEndOfString()) {
			throw new ParserException(
					"Insufficient arguements for make command.");
		}
		String variable = getMyParser().getNextElement();
				//myCurProgramArray[myCurIndex + 1];
		if (!(getMyParser().getRegex().matchSyntax(variable).equals("Variable"))) {
			throw new ParserException(
					"Incorrect format for variable declared after make.");
		}
		//note that we never clear the program variable table
		getMyParser().addVariableToTable(variable);
		getMyParser().retrieveChildren(this, getNumParams());
		return this;
	}
}
