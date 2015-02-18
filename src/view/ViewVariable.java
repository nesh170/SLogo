package view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ViewVariable {
	private String myVariableName;
	private double myVariableValue;
	private Group myRoot;
	public static final int SIDE_SPACE=10;
	
	public ViewVariable(String VariableName, double VariableValue){
		myVariableName=VariableName;
		myVariableValue=VariableValue;
	}
	
	private void generateVisualVariable(){
		myRoot = new Group();
		createVariableNameText();
	}
	
	private void createVariableNameText(){
		Text variableText = new Text(myVariableName + ":");
		//TODO adjust the size and stuff later
		myRoot.getChildren().add(variableText);
	}
	
	private Group generateTextField() {
		TextField ParamText = new TextField();
		ParamText.setPrefColumnCount(5);
		ParamText.setPromptText(Double.toString(myVariableValue));
		Button Enter = new Button();
		Enter.setTranslateX(ParamText.getLayoutX() + SIDE_SPACE);
		Enter.setTranslateY(ParamText.getLayoutY());
		Enter.setOnAction(event -> updateValue(ParamText));
		root.getChildren().addAll(FieldName, ParamText, Enter);
		return root;
	}
	
}
