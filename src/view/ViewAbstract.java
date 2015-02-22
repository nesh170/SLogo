package view;

public abstract class ViewAbstract {
	
	//TODO: figure out if return line or add it to root
	public abstract void drawTurtle(double X, double Y, int ID);
	
	public abstract void moveTurtle(double X, double Y, int ID);
	
	public abstract void rotateTurtle(double angle, int ID);
	
	public abstract void clearScreen();
	
	public abstract void printError(String message);
	
	public abstract void printMessage(String message);
	
	public abstract void addTurtle(double X, double Y, int ID);
	
	public abstract void addVariable(String variableName, Double value);
	
	public abstract void addMethodVariable(String methodName,String methodVariable);
	
}
