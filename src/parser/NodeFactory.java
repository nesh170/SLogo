package parser;

import Constants.ErrorMessage;
import exceptions.ParserException;

public class NodeFactory {

	public static ParseNode createNode(Regex regex, String nodeName, Parser parser) throws ParserException{
		String type = regex.matchSyntax(nodeName);
		if(type == null){
			throw new ParserException(ErrorMessage.INCORRECT_SYNTAX.getVal()+ nodeName);
		}
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
					throw new ParserException(ErrorMessage.INVALID_COMMAND.getVal() + nodeName);
				}
			}
			switch(commandType){
			case Parser.USER_DEFINED:
				
			case Parser.GROUP:
				return new GroupNode(nodeName, parser, commandType);
			case Parser.MAKEVARIABLE:
				return new MakeVariableNode(nodeName, parser, commandType);
			case Parser.REPEAT:
				return new RepeatNode(nodeName, parser, commandType);
			case Parser.TO:
				return new ToNode(nodeName, parser, commandType);
			default:
				return new CommandNode(nodeName, parser, commandType);
			}
		case Parser.CONSTANT:
			return new ConstantNode(nodeName, parser);
		case Parser.VARIABLE:
			return new VariableNode(nodeName, parser);
		default:
			throw new ParserException("Type mismatch on element: " + nodeName);
		}
	}
}
