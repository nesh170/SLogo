package view;

import javafx.scene.Scene;

public abstract class ViewAbstract {
	public static final int VIEW_HEIGHT=768;
	public static final int VIEW_WIDTH=1024;
	
	//TODO: figure out if return line or add it to root
	public abstract void drawTurtle(double[] newLocation, int ID);
	
	public abstract void moveTurtle(double[] newLocation, int ID);
	
	public abstract void clearScreen();
	
	public abstract void printError(String message);
	
	public abstract void printMessage(String message);
	
	public abstract void addTurtle(double[] newLocation, int ID);
	
	public abstract void addVariable(String variableName, Double value);
	
	public abstract Scene initializeView();
}
