package Model;

import java.util.function.Consumer;

/**
 * This ITurtle interface outlines methods that are to be used by any turtle object or an object that holds turtles.
 * With reference to the composite pattern, this interface acts as the "component" class that outlines the abstraction
 * that any classes in the turtle composition depend on and adhere to.  
 * 
 * @author Sierra, Yancheng
 */
public interface ITurtle {

	/**
	 * Move the turtle the given distance in the direction of the specified angle plus the turtle's current angle.  For 
	 * example, if the turtle's angle is 90 and the angle given is 180, this method should move the turtle towards 270 degrees.
	 *
	 * @param distance the distance
	 * @param angle the angle
	 * @return the double
	 */
	public double moveTurtle(double distance, double angle);
	
	/**
	 * Sets the shape index of the current turtle and returns the index value that was passed in.  
	 * The index refers to a shape in an array of shape objects that
	 * is unknown to the turtle or turtle container.   
	 *
	 * @param index the new shape index
	 */
	public void setShapeIndex(int index);
	
	/**
	 * "Jumps" the turtle to the new location (x, y) given, without incrementing the distance 
	 * traveled by the turtle.  The intent of this method is not to be used by the user
	 * but by special cases when the turtle must be moved for some internal reason, such 
	 * as the case when it's about to go off the edge of the screen.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void jump(double x, double y);
	
	/**
	 * Rotates the turtle by "angle" degrees.  The angle is added to the current turtle's angle
	 * and that new angle becomes the turtle's new heading.
	 *
	 * @param angle the angle
	 */
	public void rotate(double angle);
	
	/**
	 * Sets the boolean in the turtle equal to the boolean value given 
	 * that keeps track of whether or not it's hiding.  
	 *
	 * @param hiding the new hiding
	 */
	public void setHiding(boolean hiding);
	
	/**
	 * Sets the angle of the turtle to match the angle passed in.  
	 *
	 * @param angle the angle
	 * @return the double
	 */
	public double setAngle(double angle);
	
	/**
	 * Sets the boolean of the turtle that keeps track of whether it is "drawing" (whether the pen is down) to
	 * be the value passed in.
	 *
	 * @param drawing the new drawing
	 */
	public void setDrawing(boolean drawing);
	
	/**
	 * Sets the color index of the pen object of the current turtle.  The index refers to a color array
	 * that is unknown to the turtle or the pen.
	 *
	 * @param index the new pen color
	 */
	public void setPenColor(int index);
	
	/**
	 * Sets the width of the lines drawn by the pen to be the number of pixels passed in.
	 *
	 * @param pixels the new pen size
	 */
	public void setPenSize(double pixels);
	
	/**
	 * Applies the function passed in to all active turtles. If the implementing class is a single turtle,
	 * the function is applied to that single turtle.  If the implementing class is a multiple turtle class,
	 * the function is applied to all active turtles determined by that class.
	 *
	 * @param action the action to be applied to the turtles
	 */
	public void doToActiveTurtles(Consumer<? super SingleTurtle> action);
	
	/**
	 * Relocate turtle to the given location (x,y) and increment the total distance 
	 * traveled by this turtle to add the distance between the previous and this new location.
	 *
	 * @param x the x coordinate of the new position
	 * @param y the y coordinate of the new position
	 * @return the double distance the turtle moved to relocate
	 */
	public double relocateTurtle(double x, double y);
}
