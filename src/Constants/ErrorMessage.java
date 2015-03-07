package Constants;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorMessage {
	INCORRECT_SYNTAX("syntax_cannot_find"),
	INVALID_COMMAND("invalid_command");
	private String myString;
	private ErrorMessage(String errorText){
		myString = errorText;
	}
	
	public String getVal(){
		ResourceBundle myStringResources = ResourceBundle.getBundle("resources.Model.ModelErrorText", new Locale("en", "US"));
		return myStringResources.getString(this.myString);
	}
}
