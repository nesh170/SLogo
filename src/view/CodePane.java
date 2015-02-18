package view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CodePane {
	//TODO switch this to an enum or leave it as a string map
	public static final String TERMINAL_ID = "Terminal";
	public static final Map<String,Integer> VALUE_MAP = new HashMap<>();
	static{
		VALUE_MAP.put("CodeAreaWidth",650);
		VALUE_MAP.put("CodeAreaHeight", 200);
		VALUE_MAP.put("TerminalWidth",324);
		VALUE_MAP.put("TerminalHeight", 200);
		VALUE_MAP.put("ButtonWidth", 50);
		VALUE_MAP.put("ButtonHeight", 230);
		VALUE_MAP.put("codePaneY",568);
	}
	private TextArea myCodeArea;
	private TextFlow myTerminal;
	private Button myEnterButton;
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.ViewText.ViewText",new Locale("en", "US"));
	private int myTerminalLineNumber=1;
	
	public GridPane initializeCodePane(){
		GridPane codePane=new GridPane();
		generateCodeArea();generateTerminal();generateEnterButton();
		Node[] nodeArray = {myCodeArea,myEnterButton,myTerminal};
		for(int col=0;col<nodeArray.length;col++){
			codePane.add(nodeArray[col], col, 1);
		}
		codePane.setTranslateY(VALUE_MAP.get("codePaneY"));
		return codePane;
	}
	
	private void generateCodeArea(){
		//TODO figure out the need for a scrollbar
		myCodeArea = new TextArea();
		myCodeArea.setPromptText(myStringResources.getString("codeAreaPrompt"));
		myCodeArea.setMinWidth(VALUE_MAP.get("CodeAreaWidth"));myCodeArea.setMinHeight(VALUE_MAP.get("CodeAreaHeight"));
	}
	
	private void generateTerminal(){
		Text termText = new Text(myStringResources.getString("terminalText"));
		termText.setFill(Color.YELLOW);
		myTerminal = new TextFlow(termText);
		myTerminal.setPrefWidth(VALUE_MAP.get("TerminalWidth"));myTerminal.setPrefHeight(VALUE_MAP.get("TerminalHeight"));
		Background termBlack = new Background(new BackgroundFill(Color.BLACK, null, null));
		myTerminal.setBackground(termBlack);
	}
	
	private void generateEnterButton(){
		myEnterButton = new Button(myStringResources.getString("enter"));
		myEnterButton.setMinWidth(VALUE_MAP.get("ButtonWidth"));myEnterButton.setMinHeight(VALUE_MAP.get("ButtonHeight"));
		myEnterButton.setStyle(myStringResources.getString("enterButtonStyle"));
	}
	
	public void setEnterButtonAction(EventHandler<ActionEvent> handler){
		myEnterButton.setOnAction(handler);
	}
	
	public String getCodeData(){
		return myCodeArea.getText();
	}
	
	public void addTerminalText(String text){
		StringBuilder tempBuilder = new StringBuilder(Integer.toString(myTerminalLineNumber));
		tempBuilder.append(". ");
		tempBuilder.append(text);
		tempBuilder.append("\n"); //Adds a newLine character to ensure it goes to the next line
		Text TerminalText = new Text(tempBuilder.toString());
		if(text.toString().startsWith(myStringResources.getString("error"))){
			TerminalText.setFill(Color.RED); //Sets color to red if it is an error
		}
		else{
			TerminalText.setFill(Color.YELLOW);
		}
		myTerminal.getChildren().add(TerminalText);
		myTerminalLineNumber++;
	}

	public void clearTerminal(){
		myTerminal.getChildren().clear();
		myTerminalLineNumber=0;
	}
	
}
