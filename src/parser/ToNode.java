package parser;

import exceptions.ParserException;

public class ToNode extends CommandNode {

	public ToNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	@Override
	public ParseNode finishProcessing() throws ParserException{
		if (myParser.atEndOfString()) {
			System.out.println("Missing the variable for to");
			throw new ParserException(
					"Insufficient arguements for make command.");
		}	
		String methodName = myParser.getNextElement();
				//myCurProgramArray[myCurIndex+1];
		myParser.incrementCurIndex();
		ParseNode newNode = NodeFactory.createNode(myParser.getRegex(), myParser.getCurrentElement(), myParser);
		//ParseNode newNode = new ParseNode(myCurProgramArray[++myCurIndex]);
		this.addChild(newNode);
		myParser.retrieveChildren(this, getNumParams());
		myParser.addMethod(methodName, this.getChildren().get(1).getChildCount());
		System.out.println("The user instruction is "+ methodName);
		System.out.println("In the myProgmehotdpasdfslkfjs the num is "+this.getChildren().get(1).getChildCount());
		System.out.println("In the myProgmehotdpasd the num is "+this.getChildren().get(2).getChildCount());
		return this;
	}
}
