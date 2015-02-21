package Model;

import java.util.ArrayList;
import java.util.List;

import Statements.Forward;
import Statements.Home;
import Statements.Left;
import Statements.SetHeading;
import Statements.SetXY;
import Statements.Statement;
import Statements.TowardsAngle;
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
		myTurtleManager.addTurtle(0);
	}

	public void processCommand(String program) {
		Statement value = new Value(50);
		List<Statement> subList = new ArrayList<>();
		subList.add(value);
		Statement fw = new Forward(subList, myView, myTurtleManager);
		
		Statement value2 = new Value(90);
		List<Statement> subList2 = new ArrayList<>();
		subList2.add(value2);
		Statement left = new Left(subList2, myView, myTurtleManager);
		
		Statement value3 = new Value(225);
		List<Statement> subList3 = new ArrayList<>();
		subList3.add(value3);
		Statement setH = new SetHeading(subList3, myView, myTurtleManager);
		
		Statement value4 = new Value(1);
		Statement value5 = new Value(1);
		List<Statement> subList4 = new ArrayList<>();
		subList4.add(value4);
		subList4.add(value5);
		Statement towardsAngle = new TowardsAngle(subList4, myView, myTurtleManager);
		
		Statement value6 = new Value(100);
		Statement value7 = new Value(25);
		List<Statement> subList5 = new ArrayList<>();
		subList5.add(value6);
		subList5.add(value7);
		Statement towardsPosition = new SetXY(subList5, myView, myTurtleManager);
		
		List<Statement> subList6 = new ArrayList<>();
		Statement home = new Home(subList6, myView, myTurtleManager);
		
		List<Integer> turtleID = new ArrayList<>();
		turtleID.add(0);
		myTurtleManager.setActiveTurtles(turtleID);
		List<Statement> statementList = new ArrayList<>();
		
		//statementList.add(left);
		statementList.add(fw);
		//statementList.add(setH);
		//statementList.add(towardsAngle);
		statementList.add(towardsPosition);
		statementList.add(home);
		
		Program localProgram = new Program(statementList);
		localProgram.execute();
	}

	public void changeLanguage() {

	}

}
