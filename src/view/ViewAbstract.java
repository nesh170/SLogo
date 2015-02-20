package view;

import javafx.scene.Scene;

public abstract class ViewAbstract {
	public static final int VIEW_HEIGHT=768;
	public static final int VIEW_WIDTH=1366;
	

	
	//TODO: figure out if return line or add it to root
	public abstract void drawTurtle(double X, double Y, int ID);
	
	public abstract void moveTurtle(double X, double Y, int ID);
	
	public abstract void rotateTurtle(double angle, int ID);
	
	public abstract void clearScreen();
	
	public abstract void printError(String message);
	
	public abstract void printMessage(String message);
	
	public abstract void addTurtle(double X, double Y, int ID);
	
	public abstract void addVariable(String variableName, Double value);
	
}
