package view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ViewConstants {
	BUTTON_START_Y("buttonStartY"),BUTTON_WIDTH("buttonWidth"),BUTTON_HEIGHT("buttonHeight"),
	HISTORY_START_X("historyStartX"),HISTORY_WIDTH("historyWidth"),HISTORY_HEIGHT("historyHeight"),
	VARIABLE_TABLE_WIDTH("vTableWidth"),VARIABLE_TABLE_HEIGHT("vTableHeight"),VARIABLE_TABLE_X("vTableX"),
	VARIABLE_TABLE_PADDING("vTablePadding"),VARIABLE_TABLE_SPACING("vTableSpacing"),
	ORIGIN_X("originX"),ORIGIN_Y("originY"),REVERSE_DIRECTION("reverseDirection"),TURTLE_SIZE("turtleSize"),
	STAGE_HEIGHT("stageHeight"),STAGE_WIDTH("stageWidth"),
	CODE_AREA_WIDTH("codeAreaWidth"),CODE_AREA_HEIGHT("codeAreaHeight"),TERMINAL_WIDTH("terminalWidth"),TERMINAL_HEIGHT("terminalHeight"),
	ENTER_WIDTH("enterWidth"),ENTER_HEIGHT("enterHeight"),CODE_PANE_Y("codePaneY"),
	TERMINAL_SCROLL_WIDTH_PADDING("terminalScrollWidthPadding"),TERMINAL_SCROLL_HEIGHT_PADDING("terminalScrollHeightPadding"),
	PLAYGROUND_HEIGHT("playgroundHeight"),PLAYGROUND_WIDTH("playgroundWidth");
	
	
	private String myString;
	private ViewConstants(String constantText){
		myString = constantText;
	}
	
	public double getVal(){
		ResourceBundle myNumberResources = ResourceBundle.getBundle("resources.View.ViewConstants", new Locale("en", "US"));
		return Double.parseDouble(myNumberResources.getString(this.myString));
	}
	
	
	
	
}
