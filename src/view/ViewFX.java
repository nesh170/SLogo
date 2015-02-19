package view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

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
	
	@Override
	public Scene initializeView(){
		myRoot = new Group();
		Scene viewScene = new Scene(myRoot,VIEW_WIDTH,VIEW_HEIGHT,Color.ALICEBLUE);
		myCodeElements = new CodePane();
		myVariableElements = new VariablePane();
		myRoot.getChildren().addAll(myCodeElements.initializeCodePane(),myVariableElements.generateVariablePane());
		addVariable("lol", 62.0);
		return viewScene;
	}

	@Override
	public void drawTurtle(double[] newLocation, int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveTurtle(double[] newLocation, int ID) {
		// TODO Auto-generated method stub
		
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
