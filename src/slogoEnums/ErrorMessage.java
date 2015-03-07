package slogoEnums;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorMessage {
	INCORRECT_SYNTAX("syntax_cannot_find"),
	INVALID_COMMAND("invalid_command"),
	MISSING_END_BRACKET("missing_end_bracket"),
	BEFORE_END_BRACKET("before_bracket"),
	TYPE_MISMATCH("type_mismatch"),
	INVALID_SHAPE_INDEX("invalid_shape_index"),
	INVALID_COLOR_INDEX("invalid_color_index"),
	MISSING_PARAMETER("missing_parameter"),
	PEN_WITDTH("pen_width");
	private String myString;
	private ErrorMessage(String errorText){
		myString = errorText;
	}
	
	public String getVal(){
		ResourceBundle myStringResources = ResourceBundle.getBundle("resources.Model.ModelErrorText", new Locale("en", "US"));
		return myStringResources.getString(this.myString);
	}
}
