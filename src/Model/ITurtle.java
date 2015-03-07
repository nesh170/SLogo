package Model;

import java.util.function.Consumer;

public interface ITurtle {

	public double moveTurtle(double distance, double angle);
	public void setShapeIndex(int index);
	public void jump(double x, double y);
	public void rotate(double angle);
	//public void setY(double yCor);
	public void setHiding(boolean hiding);
	public double setAngle(double angle);
	public void setDrawing(boolean drawing);
	public void setPenColor(int index);
	public void setPenSize(double pixels);
	public void doToActiveTurtles(Consumer<? super SingleTurtle> action);
	public double relocateTurtle(double x, double y);
	
	//tricky ones below.  We have only one copy of the command so we can only return once
	//Proposed solution: just return the value of the first active turtle
	//public int getShapeIndex();
	//public int getID();
	//public double getX();
	//public boolean isHiding();
	//public double getAngle();
	//public Pen getPen();
	//public boolean isDrawing();
}
