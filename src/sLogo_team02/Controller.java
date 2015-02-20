package sLogo_team02;

import Model.Model;
import view.ViewAbstract;
import view.ViewFX;
import javafx.stage.Stage;

public class Controller {
	public static final String TITLE ="SLOGO Team_02";
	private Stage myStage;
	private ViewAbstract myView;
	private Model myModel;
	
	public Controller(Stage stage){
		myStage = stage;
	}
	
	private void initializeViewAndModel(){
		myView = new ViewFX();
		myModel = new Model(myView);
	}
	
	public void setUpStage(){
		initializeViewAndModel();
		myStage.setScene(myView.initializeView());
		myStage.setTitle(TITLE);
		myStage.show();
		//test();
	}
	
	public void test(){
		myModel.processCommand("FirstTry");
	}
}
