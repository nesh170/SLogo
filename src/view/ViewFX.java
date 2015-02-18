package view;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.TextFlow;

public class ViewFX extends ViewAbstract {
	private Group myRoot;
	private CodePane codeElements;
	

	@Override
	public void drawTurtle(Point2D newLocation, String ID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveTurtle(Point2D newLocation, String ID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearScreen(TextFlow terminal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printToConsole(TextFlow terminal, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTurtle(Point2D newLocation, String ID) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Scene initializeView(){
		//TODO Refactor this later
		myRoot = new Group();
		Scene viewScene = new Scene(myRoot,VIEW_WIDTH,VIEW_HEIGHT);
		codeElements = new CodePane();
		myRoot.getChildren().add(codeElements.initializeCodePane());
		return viewScene;
	}
	
}
