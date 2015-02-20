package Model;

import java.util.ArrayList;
import java.util.List;

import Statements.Forward;
import Statements.Statement;
import Statements.Value;
import view.ViewAbstract;

public class Model {
	// private Parser myParser;
	private List<ModelTurtle> myTurtleList;
	private List<ModelTurtle> myActiveTurtles;
	private ViewAbstract myView;
	private TurtleManager myTurtleManager;

	public Model(ViewAbstract view) {
		myView = view;
		// myParser = new Parser();
		myTurtleList = new ArrayList<>();
		myActiveTurtles = new ArrayList<>();
		myTurtleManager = new TurtleManager();
		myView.addTurtle(0, 0, 0);
	}

	public void processCommand(String program) {
		Statement value = new Value(50);
		List<Statement> subList = new ArrayList<>();
		subList.add(value);
		Statement fw = new Forward(subList, myView, myTurtleManager);
		myTurtleManager.addTurtle(0);
		List<Integer> turtleID = new ArrayList<>();
		turtleID.add(0);
		myTurtleManager.setActiveTurtles(turtleID);
		List<Statement> statementList = new ArrayList<>();
		statementList.add(fw);
		Program localProgram = new Program(statementList);
		localProgram.execute();
	}

	public void changeLanguage() {

	}

}
