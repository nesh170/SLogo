package Model;

import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import Statements.*;
import Constants.*;
import view.ViewAbstract;

public class Model {
	private ViewAbstract myView;
	private TurtleManager myTurtleManager;
	private VariableManager myVariableManager;

	public Model(ViewAbstract view) {
		myView = view;
		myTurtleManager = new TurtleManager();
		myView.addTurtle(0, 0, 0);
		myTurtleManager.addTurtle(0);
		myVariableManager = new VariableManager();
	}

	public void processCommand(String program) {
//		Statement value = new Value(5);
////		Statement value2 = new Value(2);
////		Statement value3 = new Value(3);
//		Statement var = new Variable("Test", myVariableManager);
//		List<Statement> subList = new ArrayList<>();
//		List<Statement> subList8 = new ArrayList<>();
////		List<Statement> subList2 = new ArrayList<>();
////		List<Statement> subList3 = new ArrayList<>();
//		subList.add(value);
//		subList8.add(var);
//		subList8.add(value);
////		subList2.add(value2);
////		subList2.add(value3);
////		subList3.add(new Value(90));
//		Statement fw = new Forward(subList, myView, myTurtleManager);
//		Statement value2 = new Value(90);
//		List<Statement> subList2 = new ArrayList<>();
//		subList2.add(value2);
//		Statement left = new Left(subList2, myView, myTurtleManager);
//		
//		Statement value3 = new Value(225);
//		List<Statement> subList3 = new ArrayList<>();
//		subList3.add(value3);
//		Statement setH = new SetHeading(subList3, myView, myTurtleManager);
//		
//		Statement value4 = new Value(1);
//		Statement value5 = new Value(1);
//		List<Statement> subList4 = new ArrayList<>();
//		subList4.add(value4);
//		subList4.add(value5);
//		Statement towardsAngle = new TowardsAngle(subList4, myView, myTurtleManager);
//		
//		Statement value6 = new Value(100);
//		Statement value7 = new Value(25);
//		List<Statement> subList5 = new ArrayList<>();
//		subList5.add(value6);
//		subList5.add(value7);
//		Statement towardsPosition = new SetXY(subList5, myView, myTurtleManager);
//		
//		Statement make = new Make(subList8, myView, myVariableManager);
//		
//		List<Statement> subList6 = new ArrayList<>();
//		Statement home = new Home(subList6, myView, myTurtleManager);
//		
////		Statement xcordCheck = new XCor(myView, myTurtleManager);
////		Statement ycordCheck = new YCor(myView, myTurtleManager);
////		Statement pendownCheck = new PenDownP(myView, myTurtleManager);
////		Statement checkShowing = new ShowingP(myView, myTurtleManager);
////		Statement piCheck = new Pi(subList, myView);
////		Statement powCheck = new Pow(subList2, myView);
////		Statement logCheck = new Log(subList3, myView);
////		Statement atanCheck = new ATan(subList3, myView);
////		Statement tan = new Tan(subList3, myView);
////		Statement sin = new Sin(subList3, myView);
////		Statement cos = new Cos(subList3, myView);
////		Statement rand = new RandomGen(subList3, myView);
////		Statement minus = new Minus(subList3, myView);
////		Statement remainder = new Remainder(subList2, myView);
//		List<Integer> turtleID = new ArrayList<>();
//		turtleID.add(0);
//		myTurtleManager.setActiveTurtles(turtleID);
//		List<Statement> statementList = new ArrayList<>();
//		
//		statementList.add(fw);
//		statementList.add(left);
//		//statementList.add(setH);
//		//statementList.add(towardsAngle);
//		//statementList.add(towardsPosition);
//		//statementList.add(home);
//		
////		statementList.add(xcordCheck);
////		statementList.add(ycordCheck);
////		statementList.add(pendownCheck);
////		statementList.add(checkShowing);
////		statementList.add(piCheck);
////		statementList.add(powCheck);
////		statementList.add(logCheck);
////		statementList.add(atanCheck);
////		statementList.add(tan);
////		statementList.add(sin);
////		statementList.add(cos);
////		statementList.add(rand);
////		statementList.add(minus);
////		statementList.add(remainder);
//		statementList.add(make);
//		Program localProgram = new Program(statementList);
//		localProgram.execute();
	}

	public void changeLanguage() {

	}

	public void updateVariable(String name, double newValue){
		myVariableManager.addVariable(name, newValue);
		System.out.println("added variable to manager: " + myVariableManager.getVarValue(name));
	}
	
}
