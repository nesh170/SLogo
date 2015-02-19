package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ViewVariable {
	private String myVariableName;
	private double myVariableValue;
	private HBox myBox;
	private TextField myVariableValueField;
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	
	public ViewVariable(String VariableName, double VariableValue){
		myVariableName=VariableName;
		myVariableValue=VariableValue;
	}
	
	public HBox generateVisualVariable(EventHandler<ActionEvent> handler){
		myBox = new HBox();
		myBox.setPadding(new Insets(15, 12, 15, 12));
	    myBox.setSpacing(VariablePane.TABLE_SPACING);
	    generateField(handler);
	    return myBox;
	}
	
	private void generateField(EventHandler<ActionEvent> handler) {
		myVariableValueField = new TextField();
		myVariableValueField.setPromptText(Double.toString(myVariableValue));
		Button Enter = new Button(myStringResources.getString("update"));
		Enter.setOnAction(handler);
		myBox.getChildren().addAll(new Text(myVariableName + ":"),myVariableValueField,Enter);
	}

	public void updateValue(Double value) {
		myVariableValue=value;
		myVariableValueField.setPromptText(Double.toString(myVariableValue));
	}
	
	public Double getValueInField(){
		updateValue(Double.parseDouble(myVariableValueField.getText()));
		myVariableValueField.clear();
		return this.myVariableValue;
	}
	
}
