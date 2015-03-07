package programBuilder;

import java.util.List;

import exceptions.ParserException;
import Model.MethodManager;
import Model.Program;
import Model.MultipleTurtles;
import Model.VariableManager;
import Statements.*;
import view.ViewAbstract;
import parser.*;

public class ProgramBuilder {
	private ViewAbstract myView;
	private MultipleTurtles myTurtleManager;
	private VariableManager myVariableManager;
	private Regex myRegex;
	private MethodManager myMethodManager;
	private List<String> myColors;
	private List<String> myShapes;

	public ProgramBuilder(ViewAbstract view, MultipleTurtles turtles,
			VariableManager vars, Regex regex, MethodManager methodManager,
			List<String> colors, List<String> shapes) {
		myView = view;
		myTurtleManager = turtles;
		myVariableManager = vars;
		myRegex = regex;
		myMethodManager = methodManager;
		myColors = colors;
		myShapes = shapes;
	}

	public VariableManager getVariableManager() {
		return myVariableManager;
	}

	public Program buildProgram(List<ParseNode> topNodes)
			throws ParserException {
		Program newProg = new Program(myView);
		for (ParseNode curNode : topNodes) {
			newProg.addStatment(recursiveStatementBuilder(curNode));
		}
		return newProg;
	}

	public Statement recursiveStatementBuilder(ParseNode node)
			throws ParserException {
			return node.buildStatement(this, myView, myTurtleManager,
					myVariableManager, myRegex, myMethodManager, myColors,
					myShapes);
	}
}
