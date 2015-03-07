package Model;

import java.util.function.Consumer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITurtle.
 */
public interface ITurtle {

	/**
	 * Move turtle.
	 *
	 * @param distance the distance
	 * @param angle the angle
	 * @return the double
	 */
	public double moveTurtle(double distance, double angle);
	
	/**
	 * Sets the shape index.
	 *
	 * @param index the new shape index
	 */
	public void setShapeIndex(int index);
	
	/**
	 * Jump.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void jump(double x, double y);
	
	/**
	 * Rotate.
	 *
	 * @param angle the angle
	 */
	public void rotate(double angle);
	//public void setY(double yCor);
	/**
	 * Sets the hiding.
	 *
	 * @param hiding the new hiding
	 */
	public void setHiding(boolean hiding);
	
	/**
	 * Sets the angle.
	 *
	 * @param angle the angle
	 * @return the double
	 */
	public double setAngle(double angle);
	
	/**
	 * Sets the drawing.
	 *
	 * @param drawing the new drawing
	 */
	public void setDrawing(boolean drawing);
	
	/**
	 * Sets the pen color.
	 *
	 * @param index the new pen color
	 */
	public void setPenColor(int index);
	
	/**
	 * Sets the pen size.
	 *
	 * @param pixels the new pen size
	 */
	public void setPenSize(double pixels);
	
	/**
	 * Do to active turtles.
	 *
	 * @param action the action
	 */
	public void doToActiveTurtles(Consumer<? super SingleTurtle> action);
	
	/**
	 * Relocate turtle.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the double
	 */
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
