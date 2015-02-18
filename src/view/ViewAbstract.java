package view;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.text.TextFlow;

public abstract class ViewAbstract {
	public static final int VIEW_HEIGHT=768;
	public static final int VIEW_WIDTH=1024;
	
	//TODO: figure out if return line or add it to root
	public abstract void drawTurtle(Point2D newLocation, String ID);
	
	public abstract void moveTurtle(Point2D newLocation, String ID);
	
	public abstract void clearScreen(TextFlow terminal);
	
	public abstract void printToConsole(TextFlow terminal, String message);
	
	public abstract void addTurtle(Point2D newLocation, String ID);
	
	public abstract Scene initializeView();
}
