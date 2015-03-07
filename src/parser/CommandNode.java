package parser;

import java.util.ArrayList;
import java.util.List;

import programBuilder.CommandFactory;
import programBuilder.ProgramBuilder;
import view.ViewAbstract;
import Model.MethodManager;
import Model.MultipleTurtles;
import Model.VariableManager;
import Statements.Statement;
import Constants.Constants;
import exceptions.*;

public class CommandNode extends ParseNode {
	private String mySpecificCommand;
	private int myNumChildren;
	private List<List<Statement>> myParams;
	private List<Statement> curList;

	public CommandNode(String name, Parser parser, String commandType) {
		super(name, parser);
		mySpecificCommand = commandType;
	}

	@Override
	public ParseNode finishProcessing() throws ParserException {
		myParser.retrieveChildren(this, getNumParams());
		return this;
	}

	public int getNumParams() {
		int loopTimes = 0;
		try {
			loopTimes = Constants.getNumParam(mySpecificCommand);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return loopTimes;
	}

	@Override
	public Statement buildStatement(ProgramBuilder builder,
			ViewAbstract myView, MultipleTurtles myTurtleManager,
			VariableManager myVariableManager, Regex myRegex,
			MethodManager myMethodManager, List<String> colors,
			List<String> shapes) throws ParserException {

		List<List<Statement>> paramLists = new ArrayList<>();
		List<Statement> baseList = new ArrayList<>();
		paramLists.add(baseList);
		curList = baseList;
		myNumChildren = this.getChildCount();
		doSpecificPrep(baseList);
		for (int i = 0; i < myNumChildren; i++) {
			ParseNode curChild = this.getChildren().get(i);
			if (curChild.getName().equals(Parser.GROUP)) {
				if (i != 0) {
					curList = new ArrayList<>();
					paramLists.add(curList);
				}
				for (ParseNode groupKid : curChild.getChildren()) {
					curList.add(builder.recursiveStatementBuilder(groupKid));
				}
			} else {
				baseList.add(builder.recursiveStatementBuilder(curChild));
			}
		}

		String commandType = myRegex.matchCommand(this.getName());
		// if commandType is null, check if it is a user defined before sending
		// to command factory
		return CommandFactory.generateCommand(commandType, paramLists, myView,
				myTurtleManager, myVariableManager, myRegex, myMethodManager,
				colors, shapes);
	}
	
	public void doSpecificPrep(List<Statement> base){
	}
	
	public void resetCurList(){
		curList = new ArrayList<>();
	}
	
	public void addCurListToParams(){
		myParams.add(curList);
	}
	
	public void decrementNumChildren(){
		myNumChildren--;
	}

}
