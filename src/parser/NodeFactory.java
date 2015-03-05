package parser;

import exceptions.ParserException;

public class NodeFactory {

	public static ParseNode createNode(Regex regex, String nodeName, Parser parser) throws ParserException{
		String type = regex.matchSyntax(nodeName);
		switch (type) {
		case "Command":
			String commandType = regex.matchCommand(nodeName);
			if (commandType == null) {
				if(parser.methodExists(nodeName)){
					commandType = Parser.USER_DEFINED;
				}
				else if (nodeName.equals(Parser.GROUP)) {
					commandType = Parser.GROUP;
				} else {
					throw new ParserException("Invalid Command");
				}
			}
			switch(commandType){
				
			default:
				return new CommandNode(nodeName, parser, commandType);
			}
			
			
			
		case "Constant":
			return new ConstantNode(nodeName, parser);
		case "Variable":
			return new VariableNode(nodeName, parser);
		default:
			throw new ParserException("Type mismatch on element: " + nodeName);
		}
	}
}
