package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Constants.Constants;

public class SingleTurtle implements ITurtle {
	private double myCurX;
	private double myCurY;
	private double myAngle;
	private boolean isHiding;
	private double myTotalDistance;
	private Pen myPen;
	private int myID;
	private int myShape;

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

	public double moveTurtle(double distance, double angle) {
		myCurX += Math.sin(Math.toRadians((angle + myAngle) % Constants.FULL_CIRCLE)) * distance;
		System.out.println("myCurX is "+myCurX);
		myCurY += Math.cos(Math.toRadians((angle + myAngle) % Constants.FULL_CIRCLE)) * distance;
		System.out.println("myCurY is "+myCurY);
		System.out.println();
		myTotalDistance += distance;
		return distance;
	}
	
	public double relocateTurtle(double x, double y){
		double distanceTraveled = Math.sqrt(Math.pow(x - myCurX, 2)
				+ Math.pow(y - myCurY, 2));
		myCurX = x;
		myCurY = y;
		myTotalDistance += distanceTraveled;
		return distanceTraveled;
	}

	public void setShapeIndex(int index){
		myShape = index;
	}
	
	public int getShapeIndex(){
		return myShape;
	}
	
	public int getID() {
		return myID;
	}

	public void jump(double x, double y) {
		myCurX = x;
		myCurY = y;
	}

	public void rotate(double angle) {
		myAngle = (myAngle + angle) % Constants.FULL_CIRCLE;
	}

	public double getY() {
		return myCurY;
	}

	public double getTotalDistance() {
		return myTotalDistance;
	}

	public double getX() {
		return myCurX;
	}
	
	public void setX(double xCor){
		myCurX = xCor;
	}
	
	public void setY(double yCor){
		myCurY = yCor;
	}

	public boolean isHiding() {
		return isHiding;
	}

	public void setHiding(boolean hiding) {
		isHiding = hiding;
	}
	
	public double setAngle(double angle){
		double deltaAngle = Math.abs(myAngle - (angle % Constants.FULL_CIRCLE));
		myAngle = angle % Constants.FULL_CIRCLE;
		return deltaAngle;
	}

	public double getAngle() {
		return myAngle;
	}

	public void setDrawing(boolean drawing) {
		myPen.setPenDown(drawing);
	}
	
	public boolean isDrawing() {
		return myPen.isDown();
	}
	
	public Pen getPen(){
		return myPen;
	}
	
	public void setPenColor(int index){
		myPen.setColorIndex(index);
	}
	
	public void setPenSize(double pixels){
		myPen.setPenThickness(pixels);
	}
	
	public void doToActiveTurtles(Consumer<? super SingleTurtle> action){
		List<SingleTurtle> thisTurtle = new ArrayList<>();
		thisTurtle.add(this);
		thisTurtle.forEach((Consumer<? super SingleTurtle>)action);
	}

}
