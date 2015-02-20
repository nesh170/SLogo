package sLogo_team02;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	

	@Override
	public void start(Stage s) throws Exception {
		Controller control = new Controller(s);
		control.setUpStage();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
