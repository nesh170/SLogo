package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Constants.Constants;

/**
 * This class acts as a single turtle object, holding all state for a turtle including current position, angle, 
 * whether or not the turtle is hiding, total distance traveled, a pen object, an ID, and a shape.  It implements getter
 * and setter methods for it's values as well as makes special calculations for its instance variables when 
 * certain functions are called. It is a "leaf" node in the composite pattern implementation of turtles.
 * 
 * @author Sierra, Yancheng
 */
public class SingleTurtle implements ITurtle {
	private double myCurX;
	private double myCurY;
	private double myAngle;
	private boolean isHiding;
	private double myTotalDistance;
	private Pen myPen;
	private int myID;
	private int myShape;

	/**
	 * Default constructor for SingleTurtle.
	 * @param ID int
	 */
	public SingleTurtle(int ID) {
		isHiding = false;
		myCurX = 0;
		myCurY = 0;
		myAngle = 0;
		myTotalDistance = 0;
		myID = ID;
		myPen = new Pen();
		myShape = 1;
	}

	/**
	 * Moves the turtle the specified distance in the direction of "angle" plus the current turtle's angel.
	 * @param distance double
	 * @param angle double
	 * @return double
	 * @see Model.ITurtle#moveTurtle(double, double)
	 */
	public double moveTurtle(double distance, double angle) {
		myCurX += Math.sin(Math.toRadians((angle + myAngle) % Constants.FULL_CIRCLE)) * distance;
		System.out.println("myCurX is "+myCurX);
		myCurY += Math.cos(Math.toRadians((angle + myAngle) % Constants.FULL_CIRCLE)) * distance;
		System.out.println("myCurY is "+myCurY);
		System.out.println();
		myTotalDistance += distance;
		return distance;
	}
	
	/**
	 * Method relocateTurtle.
	 * @param x double
	 * @param y double
	 * @return double
	 * @see Model.ITurtle#relocateTurtle(double, double)
	 */
	public double relocateTurtle(double x, double y){
		double distanceTraveled = Math.sqrt(Math.pow(x - myCurX, 2)
				+ Math.pow(y - myCurY, 2));
		myCurX = x;
		myCurY = y;
		myTotalDistance += distanceTraveled;
		return distanceTraveled;
	}

	/**
	 * Method setShapeIndex.
	 * @param index int
	 * @see Model.ITurtle#setShapeIndex(int)
	 */
	public void setShapeIndex(int index){
		myShape = index;
	}
	
	/**
	 * Returns the shape index of this turtle.
	 * @return int
	 */
	public int getShapeIndex(){
		return myShape;
	}
	
	/**
	 * Returns the ID of this turtle.
	 * @return int
	 */
	public int getID() {
		return myID;
	}

	/**
	 * Method jump.
	 * @param x double
	 * @param y double
	 * @see Model.ITurtle#jump(double, double)
	 */
	public void jump(double x, double y) {
		myCurX = x;
		myCurY = y;
	}

	/**
	 * Method rotate.
	 * @param angle double
	 * @see Model.ITurtle#rotate(double)
	 */
	public void rotate(double angle) {
		myAngle = (myAngle + angle) % Constants.FULL_CIRCLE;
	}

	/**
	 * Returns the Y value of the turtle's current position.
	 * @return double
	 */
	public double getY() {
		return myCurY;
	}

	/**
	 * Returns the total distance traveled by this turtle so far.
	 * @return double
	 */
	public double getTotalDistance() {
		return myTotalDistance;
	}

	/**
	 * Returns the current x coordinate of this turtle
	 * @return double
	 */
	public double getX() {
		return myCurX;
	}
	
	/**
	 * Sets the x coordinate of this turtle to the given value
	 * @param xCor double
	 */
	public void setX(double xCor){
		myCurX = xCor;
	}
	
	/**
	 * Sets the y coordinate of this turtle to the given value 
	 * @param yCor double
	 */
	public void setY(double yCor){
		myCurY = yCor;
	}

	/**
	 * Returns true if the turtle is hiding and false if it is visible
	 * @return boolean
	 */
	public boolean isHiding() {
		return isHiding;
	}

	/**
	 * Sets the hiding boolean of the turtle to the value given
	 * @param hiding boolean
	 * @see Model.ITurtle#setHiding(boolean)
	 */
	public void setHiding(boolean hiding) {
		isHiding = hiding;
	}
	
	/**
	 * Method setAngle.
	 * @param angle double
	 * @return double
	 * @see Model.ITurtle#setAngle(double)
	 */
	public double setAngle(double angle){
		double deltaAngle = Math.abs(myAngle - (angle % Constants.FULL_CIRCLE));
		myAngle = angle % Constants.FULL_CIRCLE;
		return deltaAngle;
	}

	/**
	 * Returns the current angle of this turtle
	 * @return double
	 */
	public double getAngle() {
		return myAngle;
	}

	/**
	 * Method setDrawing.
	 * @param drawing boolean
	 * @see Model.ITurtle#setDrawing(boolean)
	 */
	public void setDrawing(boolean drawing) {
		myPen.setPenDown(drawing);
	}
	
	/**
	 * Returns true if the turtle's pen is down and false if it is not
	 * @return boolean
	 */
	public boolean isDrawing() {
		return myPen.isDown();
	}
	
	/**
	 * Returns the pen of this turtle
	 * @return Pen
	 */
	public Pen getPen(){
		return myPen;
	}
	
	/**
	 * Method setPenColor.
	 * @param index int
	 * @see Model.ITurtle#setPenColor(int)
	 */
	public void setPenColor(int index){
		myPen.setColorIndex(index);
	}
	
	/**
	 * Method setPenSize.
	 * @param pixels double
	 * @see Model.ITurtle#setPenSize(double)
	 */
	public void setPenSize(double pixels){
		myPen.setPenThickness(pixels);
	}
	
	/**
	 * Method doToActiveTurtles.
	 * @param action Consumer<? super SingleTurtle>
	 * @see Model.ITurtle#doToActiveTurtles(Consumer<? super SingleTurtle>)
	 */
	public void doToActiveTurtles(Consumer<? super SingleTurtle> action){
		List<SingleTurtle> thisTurtle = new ArrayList<>();
		thisTurtle.add(this);
		thisTurtle.forEach((Consumer<? super SingleTurtle>)action);
	}

}
