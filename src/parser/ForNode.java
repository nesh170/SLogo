package parser;

import exceptions.ParserException;


/**
 * @author Sierra, Yancheng
 *
 */
public class ForNode extends CommandNode {

    public ForNode (String name, Parser parser, String commandType) {
        super(name, parser, commandType);
    }

    @Override
    public ParseNode finishProcessing () throws ParserException {
        String variableForLoop = myParser.getElementAtIndex(myParser.getCurIndex() + 2);
        myParser.addVariableToTable(variableForLoop);
        myParser.retrieveChildren(this, getNumParams());
        return this;
    }

}
