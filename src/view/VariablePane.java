package view;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VariablePane {
	public static final int TABLE_WIDTH=342;
	public static final int TABLE_X=1024;
	public static final double TABLE_PADDING = 10;
	public static final double TABLE_SPACING = 8;
	
	private VBox myVariableTable;
	
	public VBox generateVariablePane(){
		myVariableTable = new VBox();
		myVariableTable.setPadding(new Insets(TABLE_PADDING));
	    myVariableTable.setSpacing(TABLE_SPACING);
		myVariableTable.setMinHeight(ViewAbstract.VIEW_HEIGHT);
		myVariableTable.setMinWidth(TABLE_WIDTH);
		myVariableTable.setTranslateX(TABLE_X);
		myVariableTable.getChildren().add(new Text("Variable Table"));
		return myVariableTable;
	}

	public void add(HBox variableBox) {
		myVariableTable.getChildren().add(variableBox);
	}
	
	
}
