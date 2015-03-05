package view;

import javafx.scene.paint.Color;

public abstract class ViewAbstract {
	
	public abstract void drawTurtle(double X, double Y, int ID, Color penColor, double strokeWidth, String strokeType);
	
	public abstract void moveTurtle(double X, double Y, int ID);
	
	public abstract void rotateTurtle(double angle, int ID);
	
	public abstract void clearScreen();
	
	public abstract void printError(String message);
	
	public abstract void printMessage(String message);
	
	public abstract void addTurtle(String shapeType, double X, double Y, int ID);
	
	public abstract void addVariable(String variableName, Double value);
	
	public abstract void addMethodVariable(String methodName);
	
	public abstract void visibleTurtle(boolean hideOrShow, int ID);
	
}
