package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class ViewFX extends ViewAbstract {
	private Group myRoot;
	private CodePane myCodeElements;
	private VariablePane myVariableElements;
	private Map<String,ViewVariable> myVariableMap=new HashMap<String, ViewVariable>();
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	private ArrayList<ViewTurtle> myViewTurtles;
	
	@Override
	public Scene initializeView(){
		myRoot = new Group();
		myViewTurtles = new ArrayList<ViewTurtle>();
		double[] temp = new double[2];
		addTurtle(temp, 0);
		temp[0] = 100;
		temp[1] = 150;
		drawTurtle(temp, 0);
		Scene viewScene = new Scene(myRoot,VIEW_WIDTH,VIEW_HEIGHT,Color.ALICEBLUE);
		myCodeElements = new CodePane();
		myVariableElements = new VariablePane();
		myRoot.getChildren().addAll(myCodeElements.initializeCodePane(),myVariableElements.generateVariablePane());
		addVariable("lol", 62.0);
		return viewScene;
	}

	
	@Override
	public void drawTurtle(double[] newLocation, int ID) {
		myRoot.getChildren().add(myViewTurtles.get(ID).drawLine(new Point2D(newLocation[0] + 350, newLocation[1] * (-1) + 250)));
		moveTurtle(newLocation, ID);
	}

	@Override
	public void moveTurtle(double[] newLocation, int ID) {
		Point2D point = new Point2D(newLocation[0] + 350, newLocation[1] * (-1) + 250);
		myViewTurtles.get(ID).setNewLocation(point);
	}

	@Override
	public void clearScreen() {
		//TODO Clear the group that contains the lines
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
	public void addTurtle(double[] newLocation, int ID) {
		// TODO Auto-generated method stub
		Point2D point = new Point2D(newLocation[0], newLocation[1]*(-1));
		ViewTurtle vt = new ViewTurtle(point);
		myViewTurtles.add(vt);
		myRoot.getChildren().add(vt.getViewTurtles());
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
	
	private void updateVariableFromView(String variableName){
		try{
		double newVariableValue = myVariableMap.get(variableName).getValueInField();
//		System.out.println(variableName + " " + newVariableValue);
		}
		catch(Exception e){
			printError(myStringResources.getString("wrongNumberType"));
		}
		
		
		
		//TODO add methods to controller for updating variable;
	}
	
	




	
}
