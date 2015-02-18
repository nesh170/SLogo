package Model;

import Constants.Constants;

public class ModelTurtle {
	private double myCurX;
	private double myCurY;
	private double myAngle;
	private boolean myHiding;
	private double myTotalDistance;
	private boolean myPenUp;
	private int myID;

	public ModelTurtle(int ID) {
		myHiding = false;
		myCurX = 0;
		myCurY = 0;
		myAngle = 0;
		myTotalDistance = 0;
		myID = ID;
	}

	public void moveTurtle(double distance, double angle) {
		myCurX += Math.sin(angle) * distance;
		myCurY += Math.cos(angle) * distance;
		myTotalDistance += distance;
	}
	
	public int getID(){
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
	
	public double getTotalDistance(){
		return myTotalDistance;
	}

	public double getX() {
		return myCurX;
	}
	
	public boolean isHiding(){
		return myHiding;
	}
	
	public void setHiding(boolean hiding){
		myHiding = hiding;
	}
	
	public double getAngle(){
		return myAngle;
	}
	
	public void setPenUp(boolean penUp){
		myPenUp = penUp;
	}
	
	public boolean isPenUp(){
		return myPenUp;
	}

}