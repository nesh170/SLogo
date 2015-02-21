package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ViewVariable {
	private String myVariableName;
	private double myVariableValue;
	private HBox myBox;
	private TextField myVariableValueField;
	private Text myValueText;
	
	public ViewVariable(String VariableName, double VariableValue){
		myVariableName=VariableName;
		myVariableValue=VariableValue;
	}
	
	public HBox generateVisualVariable(EventHandler<KeyEvent> handler){
		myBox = new HBox();
		myBox.setPadding(new Insets(15, 12, 15, 12));
	    myBox.setSpacing(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
	    generateField(handler);
	    return myBox;
	}
	
	private void generateField(EventHandler<KeyEvent> handler) {
		myVariableValueField = new TextField();
		myValueText=new Text(Double.toString(myVariableValue));
		myVariableValueField.setOnKeyReleased(handler);
		myBox.getChildren().addAll(new Text(myVariableName + ":"),myValueText,myVariableValueField);
	}

	public void updateValue(Double value) {
		myVariableValue=value;
		myValueText.setText(Double.toString(myVariableValue));
	}
	
	public Double getValueInField(){
		updateValue(Double.parseDouble(myVariableValueField.getText()));
		myVariableValueField.clear();
		return this.myVariableValue;
	}
	
}
