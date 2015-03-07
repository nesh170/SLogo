package Model;

import Constants.*;

/**
 * This class keeps track of all pen properties including stroke, thickness, whether it's up or down, 
 * and the color index.  It provides get and set functionality for each property.
 * 
 * @author Sierra, Yancheng
 */
public class Pen {

	public static final String DASHED_STROKE = "Dashed";
	public static final String DOTTED_STROKE = "Dotted";
	public static final String SOLID_STROKE = "Regular";
	public static final String DEFAULT_STROKE = DASHED_STROKE;
	public static final double DEFAULT_THICKNESS = 1.0;
	// default color is black
	public static final String DEFAULT_COLOR = "#000000";

	private boolean isDown;
	private String myStroke;
	private double myThickness;
	private int myColorIndex;

	/**
	 * Creates a default pen object with dashed stroke, black color, and in the down position.
	 */
	public Pen() {
		isDown = Constants.SET_PEN_DOWN;
		myStroke = DEFAULT_STROKE;
		myThickness = DEFAULT_THICKNESS;
		myColorIndex = 0;
	}

	/**
	 * Sets the color index of the pen
	 * @param index int
	 */
	public void setColorIndex(int index) {
		myColorIndex = index;
	}
	
	/**
	 * Sets the stroke of the pen to be the specified stroke type
	 * @param stroke String
	 */
	public void setStroke(String stroke){
		myStroke = stroke;
	}
	
	/**
	 * Returns a boolean that represents whether or not the pen is down
	 * @return boolean
	 */
	public boolean isDown(){
		return isDown;
	}
	
	/**
	 * Sets the boolean that represents if the pen is down to the specified value
	 * @param drawing boolean
	 */
	public void setPenDown(boolean drawing){
		isDown = drawing;
	}
	
	/**
	 * Returns the current stroke type
	 * @return String
	 */
	public String getPenStroke(){
		return myStroke;
	}
	
	/**
	 * Returns the current pen thickness
	 * @return double
	 */
	public double getThickness(){
		return myThickness;
	}
	
	/**
	 * Returns the color index 
	 * @return int
	 */
	public int getColorIndex(){
		return myColorIndex;
	}
	
	/**
	 * Sets the pen thickness
	 * @param pixels double
	 */
	public void setPenThickness(double pixels){
		myThickness = pixels;
	}
	
}
