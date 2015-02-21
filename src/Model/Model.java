package Model;

import java.util.ArrayList;
import java.util.List;
import Statements.*;
import view.ViewAbstract;

public class Model {
	private Parser myParser;
	private List<ModelTurtle> myTurtleList;
	private List<ModelTurtle> myActiveTurtles;
	private ViewAbstract myView;
	private TurtleManager myTurtleManager;

	public Model(ViewAbstract view) {
		myView = view;
		myParser = new Parser();
		myTurtleList = new ArrayList<>();
		myActiveTurtles = new ArrayList<>();
		myTurtleManager = new TurtleManager();
		myView.addTurtle(0, 0, 0);
		myTurtleManager.addTurtle(0);
	}

	public void processCommand(String program) {
		Statement value = new Value(50);
		Statement value2 = new Value(2);
		Statement value3 = new Value(3);
		List<Statement> subList = new ArrayList<>();
		List<Statement> subList2 = new ArrayList<>();
		List<Statement> subList3 = new ArrayList<>();
		subList.add(value);
		subList2.add(value2);
		subList2.add(value3);
		subList3.add(new Value(90));
		Statement fw = new Forward(subList, myView, myTurtleManager);
//		Statement xcordCheck = new XCor(myView, myTurtleManager);
//		Statement ycordCheck = new YCor(myView, myTurtleManager);
//		Statement pendownCheck = new PenDownP(myView, myTurtleManager);
//		Statement checkShowing = new ShowingP(myView, myTurtleManager);
//		Statement piCheck = new Pi(subList, myView);
//		Statement powCheck = new Pow(subList2, myView);
//		Statement logCheck = new Log(subList3, myView);
//		Statement atanCheck = new ATan(subList3, myView);
//		Statement tan = new Tan(subList3, myView);
//		Statement sin = new Sin(subList3, myView);
//		Statement cos = new Cos(subList3, myView);
//		Statement rand = new RandomGen(subList3, myView);
//		Statement minus = new Minus(subList3, myView);
//		Statement remainder = new Remainder(subList2, myView);
		List<Integer> turtleID = new ArrayList<>();
		turtleID.add(0);
		myTurtleManager.setActiveTurtles(turtleID);
		List<Statement> statementList = new ArrayList<>();
		statementList.add(fw);
//		statementList.add(xcordCheck);
//		statementList.add(ycordCheck);
//		statementList.add(pendownCheck);
//		statementList.add(checkShowing);
//		statementList.add(piCheck);
//		statementList.add(powCheck);
//		statementList.add(logCheck);
//		statementList.add(atanCheck);
//		statementList.add(tan);
//		statementList.add(sin);
//		statementList.add(cos);
//		statementList.add(rand);
//		statementList.add(minus);
//		statementList.add(remainder);
		Program localProgram = new Program(statementList);
		localProgram.execute();
	}

	public void changeLanguage() {

	}

}
