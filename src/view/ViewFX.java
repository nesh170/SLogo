package view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import sLogo_team02.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import view.ViewConstants;

public class ViewFX extends ViewAbstract {
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
		new ButtonPane(myRoot,e-> changeBackgroundImage(),new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number value, Number newValue){
				changeLanuageinController(newValue.intValue());
			}	
		});
		TurtlePlayground playground = new TurtlePlayground(myRoot);
		Scene viewScene = new Scene(myRoot,ViewConstants.STAGE_WIDTH.getVal(),ViewConstants.STAGE_HEIGHT.getVal(),Color.ALICEBLUE);
		myCodeElements = new CodePane(myRoot, e->pushCodeToController());
		myVariableElements = new VariablePane(myRoot);
		myRoot.getChildren().addAll(myLineRoot, myTurtleRoot);
		myController.setScene(viewScene);
//		test();
	}

	
	private void test(){
		addTurtle(0,0, 0);
		drawTurtle(350,250,0);
		rotateTurtle(180,0);
		drawTurtle(100,100,0);
		rotateTurtle(90, 0);
		drawTurtle(15, 18, 0);
//		clearScreen();
//		addTurtle(0,0,0);
		addVariable("lol", 62.0);
	}


	@Override
	public void drawTurtle(double X, double Y, int ID) {
		Line tempLine = myViewTurtlesMap.get(ID).drawLine(new Point2D(X + ViewTurtle.ORIGIN_X, ViewConstants.REVERSE_DIRECTION.getVal()*Y + ViewTurtle.ORIGIN_Y));
		myLineRoot.getChildren().add(tempLine);
		moveTurtle(X,Y,ID);
	}

	@Override
	public void moveTurtle(double X, double Y, int ID){
		Point2D point = new Point2D(X, ViewConstants.REVERSE_DIRECTION.getVal()*Y);
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
		ViewTurtle vt = new ViewTurtle(point, ViewConstants.TURTLE_SIZE.getVal());
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
		myController.updateVariable(variableName, newVariableValue);
		}
		catch(Exception e){
			printError(myStringResources.getString("wrongNumberType"));
		}
		
	}
	
	private void changeBackgroundImage(){
		//TODO add code to change the Image of the TurtlePlayGround with turtlePlaygroundBackGround
	}
	
	private void changeLanuageinController(int index){
		ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
		myController.changeLanguage(myStringResources.getString("languageFile").split("\\s+")[index]);
	}


	
}
