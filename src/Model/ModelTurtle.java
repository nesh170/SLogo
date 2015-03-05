package Model;

import Constants.Constants;

public class ModelTurtle {
	private double myCurX;
	private double myCurY;
	private double myAngle;
	private boolean isHiding;
	private double myTotalDistance;
	private Pen myPen;
	private int myID;
	private int myShape;
	private String myImagePath;

	public ModelTurtle(int ID) {
		isHiding = false;
		myCurX = 0;
		myCurY = 0;
		myAngle = 0;
		myTotalDistance = 0;
		myID = ID;
		myPen = new Pen();
	}

	public void moveTurtle(double distance, double angle) {
		myCurX += Math.sin(Math.toRadians((angle + myAngle) % Constants.FULL_CIRCLE)) * distance;
		System.out.println("myCurX is "+myCurX);
		myCurY += Math.cos(Math.toRadians((angle + myAngle) % Constants.FULL_CIRCLE)) * distance;
		System.out.println("myCurY is "+myCurY);
		System.out.println();
		myTotalDistance += distance;
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
	
	public void setAngle(double angle){
		myAngle = angle % Constants.FULL_CIRCLE;
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

}
