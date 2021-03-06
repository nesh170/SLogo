package parser;

import java.util.List;

import Statements.MethodName;
import Statements.Statement;
import exceptions.ParserException;

/**
 * 
 * @author Yancheng, Sierra
 */
public class ToNode extends CommandNode {

	/**ToNode handles the command used to create user-defined methods. 
	 * Constructor for ToNode.
	 * @param name String
	 * @param parser Parser
	 * @param commandType String
	 */
	public ToNode(String name, Parser parser, String commandType) {
		super(name, parser, commandType);
	}
	
	/**
	 * Method finishProcessing.
	 * @return ParseNode
	 * @throws ParserException
	 */
	@Override
	public ParseNode finishProcessing() throws ParserException{
		if (getMyParser().atEndOfString()) {
			System.out.println("Missing the variable for to");
			throw new ParserException(
					"Insufficient arguements for to command.");
		}	
		String methodName = getMyParser().getNextElement();
		getMyParser().incrementCurIndex();
		ParseNode newNode = new CommandNode(getMyParser().getCurrentElement(), getMyParser(), Parser.USER_DEFINED);
		this.addChild(newNode);
		
		int i = getMyParser().getCurIndex() + 1;
		String curString = getMyParser().getElementAtIndex(i);
		if(!curString.equals(Parser.OPEN_BRACKET)){
			throw new ParserException(
					"Inaccurate syntax for to command.  Missing open bracket for variable declaration.");
		}
		i++;
		curString = getMyParser().getElementAtIndex(i);
		while(!curString.equals(Parser.CLOSED_BRACKET)){
			
			if (!(getMyParser().getRegex().matchSyntax(curString).equals("Variable"))) {
				throw new ParserException(
						"Incorrect variable list in method declaration");
			}
			getMyParser().addVariableToTable(curString);
			curString = getMyParser().getElementAtIndex(i);
			i++;
		}
		
		getMyParser().retrieveChildren(this, getNumParams());
		getMyParser().addMethod(methodName, this.getChildren().get(1).getChildCount());
		System.out.println("The user instruction is "+ methodName);
		System.out.println("In the myProgmehotdpasdfslkfjs the num is "+this.getChildren().get(1).getChildCount());
		System.out.println("In the myProgmehotdpasd the num is "+this.getChildren().get(2).getChildCount());
		return this;
	}
	
	/**This method adds the name of the user-defined method as a child node 
	 * and calls recursion on the next node.
	 * Method doSpecificPrep.
	 * @param base List<Statement>
	 */
	@Override
	public void doSpecificPrep(List<Statement> base){
		decrementNumChildren();
		base.add(new MethodName(this.getChildren().get(0).getName()));
		this.removeChild(0);
		resetCurList();
		addCurListToParams();		
	}
}
