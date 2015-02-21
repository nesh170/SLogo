package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VariablePane {
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	
	private VBox myVariableTable;
	
	public VariablePane(Group root) {
		generateVariablePane(root);
	}

	public void generateVariablePane(Group root){
		myVariableTable = new VBox();
		myVariableTable.setPadding(new Insets(ViewConstants.VARIABLE_TABLE_PADDING.getVal()));
	    myVariableTable.setSpacing(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
		myVariableTable.setMaxSize(ViewConstants.VARIABLE_TABLE_WIDTH.getVal(), ViewConstants.VARIABLE_TABLE_HEIGHT.getVal());
		myVariableTable.setTranslateX(ViewConstants.VARIABLE_TABLE_X.getVal());
		myVariableTable.getChildren().add(new Text(myStringResources.getString("variableTableTitle")));
		root.getChildren().add(myVariableTable);
	}

	public void add(HBox variableBox) {
		myVariableTable.getChildren().add(variableBox);
	}
	
	
}
