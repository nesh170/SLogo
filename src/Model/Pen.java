package Model;

import Constants.*;

public class Pen {

	public static final String DASHED_STROKE = "Dashed";
	public static final String DOTTED_STROKE = "Dotted";
	public static final String SOLID_STROKE = "Regular";
	public static final String DEFAULT_STROKE = DASHED_STROKE;
	public static final double DEFAULT_THICKNESS = 1.0;
	// defualt color is black
	public static final String DEFAULT_COLOR = "#000000";

	private boolean isDown;
	private String myStroke;
	private double myThickness;
	private String myStringColor;

	public Pen() {
		isDown = Constants.SET_PEN_DOWN;
		myStroke = DEFAULT_STROKE;
		myThickness = DEFAULT_THICKNESS;
		myStringColor = DEFAULT_COLOR;
	}

	public void calculateAndSetColor(int r, int g, int b) {
		myStringColor = String.format("#%02X%02X%02X", (int) (r * 255),
				(int) (g * 255), (int) (b * 255));
	}
	
	public void setStroke(String stroke){
		myStroke = stroke;
	}
	
	public boolean isDown(){
		return isDown;
	}
	
	public void setPenDown(boolean drawing){
		isDown = drawing;
	}
	
	//public void drawYourself(ViewAbstract)
	
	public String getPenStroke(){
		return myStroke;
	}
	
	public double getThickness(){
		return myThickness;
	}
	
	public String getStringColor(){
		return myStringColor;
	}

}
