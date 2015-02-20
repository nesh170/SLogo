package sLogo_team02;

import view.ViewAbstract;
import view.ViewFX;
import javafx.stage.Stage;

public class Controller {
	public static final String TITLE ="SLOGO Team_02";
	private Stage myStage;
	private ViewAbstract view;
	
	public Controller(Stage stage){
		myStage = stage;
	}
	
	private void initializeViewAndModel(){
		view = new ViewFX(this);
	}
	
	public void executeCommand(String codeParse){
		
	}
	
	public void setUpStage(){
		initializeViewAndModel();
		myStage.setScene(view.initializeView());
		myStage.setTitle(TITLE);
		myStage.show();
	}
}
