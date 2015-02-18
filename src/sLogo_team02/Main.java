package sLogo_team02;

import view.ViewAbstract;
import view.ViewFX;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String TITLE ="SLOGO Team_02";

	@Override
	public void start(Stage s) throws Exception {
		ViewAbstract view = new ViewFX();
		s.setScene(view.initializeView());
		s.setTitle(TITLE);
		s.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
