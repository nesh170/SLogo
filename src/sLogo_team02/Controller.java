package sLogo_team02;

import java.util.List;

import parser.*;
import programBuilder.*;
import Model.Model;
import Model.Program;
import view.ViewAbstract;
import view.ViewFX;
import javafx.scene.Scene;
import javafx.stage.Stage;
import exceptions.*;

public class Controller {
	public static final String TITLE ="SLOGO Team_02";
	private Stage myStage;
	private Scene myScene;
	private ViewAbstract myView;
	private Model myModel;
	private Parser myParser;
	private ProgramBuilder myProgBuilder;

	public Controller(Stage stage){
		myStage = stage;
		myParser = new Parser();
	}

	private void initializeViewAndModel(){
		myView = new ViewFX(this);
		myModel = new Model(myView);
		myProgBuilder = new ProgramBuilder(myView, myModel.getTurtleManager(), 
				myModel.getVariableManager(), myParser.getRegex());
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
		myModel.updateVariable(variableName, value);
	}

	public void updateMethodVariable(String variableName){
	    //TODO for model
	}

	public void executeProgram(String codeData) {
		System.out.println(codeData);
		try{
			List<ParseNode> topNodes = myParser.parse(codeData);
			System.out.println("Printing after parsing: " + topNodes.size());
			Program newProg = myProgBuilder.buildProgram(topNodes);
			myModel.processCommand(newProg);
		}catch(ParserException e){
			myView.printError(e.toString());
		}catch(NullPointerException e){
			//If the user pressed run without any input, preprocess will return null, and we just want to ignore it
		}
	}

	public void changeLanguage(String languagePath) {
		System.out.println(languagePath);
		myParser.changeLanguage(languagePath);
	}
}
