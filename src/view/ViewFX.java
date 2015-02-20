package view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import sLogo_team02.Controller;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class ViewFX extends ViewAbstract {
	public static final int REVERSE_DIRECTION = -1;
	private static final double TURTLE_SIZE = 10;
	private Group myRoot;
	private Group myLineRoot;
	private Group myTurtleRoot;
	private CodePane myCodeElements;
	private VariablePane myVariableElements;
	private Map<String,ViewVariable> myVariableMap=new HashMap<String, ViewVariable>();
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	private Map<Integer,ViewTurtle> myViewTurtlesMap;
	private Controller myController;
	
	public ViewFX(Controller controller){
		myController = controller;
		initializeView();
	}
	
	private void initializeView(){
		myRoot = new Group();
		myLineRoot = new Group();
		myTurtleRoot = new Group();
		myViewTurtlesMap = new HashMap<>();
		Scene viewScene = new Scene(myRoot,VIEW_WIDTH,VIEW_HEIGHT,Color.ALICEBLUE);
		myCodeElements = new CodePane(myRoot, e->pushCodeToController());
		myVariableElements = new VariablePane(myRoot);
		myRoot.getChildren().addAll(myLineRoot, myTurtleRoot);
		myController.setScene(viewScene);
		test();
	}

	
	private void test(){
		addTurtle(0,0, 0);
		drawTurtle(350,250,0);
		rotateTurtle(235,0);
		drawTurtle(100,100,0);
		
//		drawTurtle(15, 18, 0);
//		clearScreen();
//		addTurtle(0,0,0);
		addVariable("lol", 62.0);
	}


	@Override
	public void drawTurtle(double X, double Y, int ID) {
		Line tempLine = myViewTurtlesMap.get(ID).drawLine(new Point2D(X + ViewTurtle.ORIGIN_X, REVERSE_DIRECTION*Y + ViewTurtle.ORIGIN_Y));
		myLineRoot.getChildren().add(tempLine);
		moveTurtle(X,Y,ID);
	}

	@Override
	public void moveTurtle(double X, double Y, int ID){
		Point2D point = new Point2D(X, REVERSE_DIRECTION*Y);
		myViewTurtlesMap.get(ID).setNewLocation(point);
	}
	
	@Override
	public void rotateTurtle(double angle, int ID) {
		myViewTurtlesMap.get(ID).rotate(angle);
	}

	@Override
	public void clearScreen(){
		myLineRoot.getChildren().clear();
		myTurtleRoot.getChildren().clear();
		myCodeElements.clearTerminal();
	}

	@Override
	public void printError(String message) {
		myCodeElements.addTerminalText(message, Color.RED);
	}

	@Override
	public void printMessage(String message) {
		myCodeElements.addTerminalText(message, Color.YELLOW);
	}

	@Override
	public void addTurtle(double X, double Y, int ID){
		Point2D point = new Point2D(X, Y);
		ViewTurtle vt = new ViewTurtle(point, TURTLE_SIZE);
		vt.setNewLocation(point);
		myViewTurtlesMap.put(ID,vt);
		myTurtleRoot.getChildren().add(vt.getViewTurtles());
	}

	@Override
	public void addVariable(String variableName, Double value) {
		if(myVariableMap.containsKey(variableName)){
			myVariableMap.get(variableName).updateValue(value);
		}
		else{
			ViewVariable tempVariable = new ViewVariable(variableName, value);
			myVariableElements.add(tempVariable.generateVisualVariable(e->{
				if(e.getCode().equals(KeyCode.ENTER)){
					updateVariableFromView(variableName);
				}
			}));
			myVariableMap.put(variableName, tempVariable);
		}
		
	}
	
	private void pushCodeToController(){
		myController.executeCommand(myCodeElements.getCodeData());
	}
	
	private void updateVariableFromView(String variableName){
		try{
		double newVariableValue = myVariableMap.get(variableName).getValueInField();
		System.out.println(variableName + " " + newVariableValue);
		//TODO add methods to controller for updating variable;
		}
		catch(Exception e){
			printError(myStringResources.getString("wrongNumberType"));
		}
		
	}


	
}
