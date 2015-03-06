package parser;

import exceptions.ParserException;

public class MakeVariableNode extends CommandNode {

	public MakeVariableNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	@Override
	public ParseNode finishProcessing() throws ParserException{
		if (myParser.atEndOfString()) {
			throw new ParserException(
					"Insufficient arguements for make command.");
		}
		String variable = myParser.getNextElement();
				//myCurProgramArray[myCurIndex + 1];
		if (!(myParser.getRegex().matchSyntax(variable).equals("Variable"))) {
			throw new ParserException(
					"Incorrect format for variable declared after make.");
		}
		//note that we never clear the program variable table
		myParser.addVariableToTable(variable);
		myParser.retrieveChildren(this, getNumParams());
		return this;
	}
}
