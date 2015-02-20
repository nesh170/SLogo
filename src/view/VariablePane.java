package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VariablePane {
	public static final int TABLE_WIDTH=250;
	public static final int TABLE_HEIGHT=568;
	public static final int TABLE_X=700;
	public static final double TABLE_PADDING = 10;
	public static final double TABLE_SPACING = 8;
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	
	private VBox myVariableTable;
	
	public VBox generateVariablePane(){
		myVariableTable = new VBox();
		myVariableTable.setPadding(new Insets(TABLE_PADDING));
	    myVariableTable.setSpacing(TABLE_SPACING);
		myVariableTable.setMaxHeight(TABLE_HEIGHT);
		myVariableTable.setMaxWidth(TABLE_WIDTH);
		myVariableTable.setTranslateX(TABLE_X);
		myVariableTable.getChildren().add(new Text(myStringResources.getString("variableTableTitle")));
		return myVariableTable;
	}

	public void add(HBox variableBox) {
		myVariableTable.getChildren().add(variableBox);
	}
	
	
}
