package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class ViewFX extends ViewAbstract {
	private Group myRoot;
	private CodePane codeElements;
	
	@Override
	public Scene initializeView(){
		myRoot = new Group();
		Scene viewScene = new Scene(myRoot,VIEW_WIDTH,VIEW_HEIGHT);
		codeElements = new CodePane();
		myRoot.getChildren().add(codeElements.initializeCodePane());
		return viewScene;
	}

	@Override
	public void drawTurtle(double[] newLocation, int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveTurtle(double[] newLocation, int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearScreen() {
		//TODO Clear the group that contains the lines
		
		codeElements.clearTerminal();
	}

	@Override
	public void printError(String message) {
		codeElements.addTerminalText(message, Color.RED);
	}

	@Override
	public void printMessage(String message) {
		codeElements.addTerminalText(message, Color.YELLOW);
	}

	@Override
	public void addTurtle(double[] newLocation, int ID) {
		// TODO Auto-generated method stub
		
	}



	
}
