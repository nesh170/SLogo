package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.ViewAbstract;
import Constants.*;

/**
 * This class is the main backend class in the model.  It is what keeps track of and manages all of the data
 * for the current SLogo environment, modifying it when instructed and dispersing it to the classes that 
 * ask for it.  This class acts as the "model" in reference to the model-view-controller design pattern, 
 * receiving information from the controller and sending information to the view.
 * 
 * @author Sierra, Yancheng
 */
public class Model {
	
	/** The view object that this model uses to display information. */
	private ViewAbstract myView;
	
	/** The turtle manager that keeps track of the turtles in the model. */
	private MultipleTurtles myTurtleManager;
	
	/** The variable manager that keeps track of the variables available to any programs. */
	private VariableManager myVariableManager;
	
	/** The method manager that keeps track of any user defined methods. */
	private MethodManager myMethodManager;
	
	/** The list of shapes that turtles can assume. */
	private List<String> myShapes;
	
	/** The list of colors that are available to the user. */
	private List<String> myColors;

	/**
	 * Instantiates a new model and each of it's instance variables.
	 *
	 * @param view the view
	 */
	public Model(ViewAbstract view) {
		myView = view;
		myTurtleManager = new MultipleTurtles(myView);
		myTurtleManager.addTurtle(0);
		myVariableManager = new VariableManager(myView);
		myMethodManager = new MethodManager();
		initializeDefaultShapes();
		initializeDefaultColors();
		myView.updateColorListView(myColors);
	}
	
	/**
	 * Gets the list of colors available in this model.
	 *
	 * @return the list colors defined in the model and available to the user.
	 */
	public List<String> getColors(){
		return myColors;
	}
	
	/**
	 * Gets the list of shapes available in this model for turtles to assume.
	 *
	 * @return the list of shapes that turtles can take on
	 */
	public List<String> getShapes(){
		return myShapes;
	}
	
	/**
	 * Initializes the shapes array to contain default shapes defined in a constants class.
	 */
	public void initializeDefaultShapes(){
		myShapes = new ArrayList<>();
		myShapes.addAll(Constants.DEFAULT_SHAPES);
	}
	
	/**
	 * Initialize the color array list to contain the default colors defined in the constants class.
	 */
	public void initializeDefaultColors(){
		myColors = new ArrayList<>();
		myColors.addAll(Constants.DEFAULT_COLORS);
	}
	
	/**
	 * Returns the turtle manager for this model.
	 *
	 * @return the turtle manager
	 */
	public MultipleTurtles getTurtleManager(){
		return myTurtleManager;
	}
	
	/**
	 * Returns the variable manager for this model.
	 *
	 * @return the variable manager
	 */
	public VariableManager getVariableManager(){
		return myVariableManager;
	}
	
	/**
	 * Returns the method manager for this model.
	 *
	 * @return the method manager
	 */
	public MethodManager getMethodManager(){
		return myMethodManager;
	}

	/**
	 * Execute the given program.
	 * Assumes the program is valid.
	 *
	 * @param program the program
	 */
	public void processCommand(Program program) {
		program.execute();
	}
	
	/**
	 *Toggles whether or not the identified turtle is active.
	 *
	 * @param ID the id
	 */
	public void toggleActive(Integer ID){
		myTurtleManager.toggleTurtle(ID);
	}

	/**
	 * Updates the value of the specified variable in the variable table to be the value given.
	 *
	 * @param name the name
	 * @param newValue the new value
	 */
	public void updateVariable(String name, double newValue){
		myVariableManager.addVariable(name, newValue);
	}
	
	/**
	 * Calls on the view to initialize a dialog box, passing it the information needed about the turtle 
	 * and pen.
	 *
	 * @param ID the id
	 */
	public void sendInfoForDialog(int ID){
		myView.setUpDialogBox(myTurtleManager.getTurtle(ID).getPen(), ID, myColors);
	}
	
	/**
	 * Removes all of the turtles from the turtle manager, leaving the model without any turtles.
	 */
	public void clearTurtles(){
	    myTurtleManager.clearTurtles();
	}
	
	/**
	 * Adds a turtle to the model at the specified location (x,y) with the given ID.  If a turtle 
	 * with this ID already exists, this method will override that turtle with the new one.
	 * @param X the x coordinate of the new turtle
	 * @param Y the y coordinate of the new turtle
	 * @param ID of the new turtle
	 */
	public void addTurtle(double X, double Y, int ID){
	       myTurtleManager.addTurtle(X, Y, ID);
	}
	
	/**
	 * Returns an unmodifiable map of all current turtles that are in this model.  
	 * 
	 * @return the map of IDs to turtles currently in this model
	 */
	public Map<Integer, SingleTurtle> getTurtleMap(){
	    return myTurtleManager.getTurtleMap();
	}
}
