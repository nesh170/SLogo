package parser;

import exceptions.ParserException;

public class NodeFactory {

	public static ParseNode createNode(Regex regex, String nodeName, Parser parser) throws ParserException{
		String type = regex.matchSyntax(nodeName);
		switch (type) {
		case "Command":
			return new CommandNode(nodeName, parser);
		case "Constant":
			return new ConstantNode(nodeName, parser);
		case "Variable":
			return new VariableNode(nodeName, parser);
		default:
			throw new ParserException("Type mismatch on element: " + nodeName);
		}
	}
}
