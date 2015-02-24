package sLogo_team02;

import parser.Parser;
import Model.Model;
import view.ViewAbstract;
import view.ViewFX;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
	public static final String TITLE ="SLOGO Team_02";
	private Stage myStage;
	private Scene myScene;
	private ViewAbstract myView;
	private Model myModel;
	private Parser myParser;
	
	public Controller(Stage stage){
		myStage = stage;
		myParser = new Parser();
	}
	
	private void initializeViewAndModel(){
		myView = new ViewFX(this);
		myModel = new Model(myView);
	}
	
	public void setUpStage(){
		initializeViewAndModel();
		myStage.setScene(myScene);
		myStage.setTitle(TITLE);
		myStage.show();
	}
	
	public void setScene(Scene s){
		myScene = s;
	}
	
	public void updateVariable(String variableName,double value){
		//TODO calls model to update the variable
		System.out.println(variableName + " " + value);
	}

	public void executeCommand(String codeData) {
		myParser.parse(codeData);
		myModel.processCommand("FirstTry");
	}

	public void changeLanguage(String languagePath) {
		//TODO add change lanuage stuff here. figure out if model wants a string or a resourcefile.
		System.out.println(languagePath);
	}
}
