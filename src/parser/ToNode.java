package parser;

import java.util.List;

import Statements.MethodName;
import Statements.Statement;
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
					"Insufficient arguements for to command.");
		}	
		String methodName = myParser.getNextElement();
		myParser.incrementCurIndex();
		ParseNode newNode = new CommandNode(myParser.getCurrentElement(), myParser, Parser.USER_DEFINED);
		this.addChild(newNode);
		
		int i = myParser.getCurIndex() + 1;
		String curString = myParser.getElementAtIndex(i);
		if(!curString.equals(Parser.OPEN_BRACKET)){
			throw new ParserException(
					"Inaccurate syntax for to command.  Missing open bracket for variable declaration.");
		}
		i++;
		curString = myParser.getElementAtIndex(i);
		while(!curString.equals(Parser.CLOSED_BRACKET)){
			
			if (!(myParser.getRegex().matchSyntax(curString).equals("Variable"))) {
				throw new ParserException(
						"Incorrect variable list in method declaration");
			}
			myParser.addVariableToTable(curString);
			curString = myParser.getElementAtIndex(i);
			i++;
		}
		
		myParser.retrieveChildren(this, getNumParams());
		myParser.addMethod(methodName, this.getChildren().get(1).getChildCount());
		System.out.println("The user instruction is "+ methodName);
		System.out.println("In the myProgmehotdpasdfslkfjs the num is "+this.getChildren().get(1).getChildCount());
		System.out.println("In the myProgmehotdpasd the num is "+this.getChildren().get(2).getChildCount());
		return this;
	}
	
	@Override
	public void doSpecificPrep(List<Statement> base){
		decrementNumChildren();
		
		//		looptimes--;
		base.add(new MethodName(this.getChildren().get(0).getName()));
		this.removeChild(0);
		resetCurList();
		addCurListToParams();
//		curList = new ArrayList<>();
//		paramLists.add(curList);

		
	}
}
