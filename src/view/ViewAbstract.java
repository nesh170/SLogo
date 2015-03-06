package view;

import java.util.List;
import javafx.scene.Group;

public abstract class ViewAbstract {

	public abstract void drawShape(double X, double Y, int ID, String penColor,
			double strokeWidth, String strokeType);

	public abstract void moveShape(double X, double Y, int ID);

	public abstract void rotateShape(double angle, int ID);

	public abstract void clearScreen();

	public abstract void printError(String message);

	public abstract void printMessage(String message);

	public abstract void addShape(String shapeType, double X, double Y, int ID);

	public abstract void addVariable(String variableName, Double value);

	public abstract void addMethodVariable(String methodName);

	public abstract void visibleShape(boolean hideOrShow, int ID);

	public abstract void visualActiveShape(boolean activeOrInactive, int ID);

	public abstract Group getRoot();
	
	public abstract void changeShape(String shapeType, int ID);
	
	public abstract void getColorArray(List<String> colorList);
}
