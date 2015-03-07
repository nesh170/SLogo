package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.ViewAbstract;
import Constants.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Model.
 */
public class Model {
	
	/** The my view. */
	private ViewAbstract myView;
	
	/** The my turtle manager. */
	private MultipleTurtles myTurtleManager;
	
	/** The my variable manager. */
	private VariableManager myVariableManager;
	
	/** The my method manager. */
	private MethodManager myMethodManager;
	
	/** The my shapes. */
	private List<String> myShapes;
	
	/** The my colors. */
	private List<String> myColors;

	/**
	 * Instantiates a new model.
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
	 * Gets the colors.
	 *
	 * @return the colors
	 */
	public List<String> getColors(){
		return myColors;
	}
	
	/**
	 * Gets the shapes.
	 *
	 * @return the shapes
	 */
	public List<String> getShapes(){
		return myShapes;
	}
	
	/**
	 * Initialize default shapes.
	 */
	public void initializeDefaultShapes(){
		myShapes = new ArrayList<>();
		myShapes.addAll(Constants.DEFAULT_SHAPES);
	}
	
	/**
	 * Initialize default colors.
	 */
	public void initializeDefaultColors(){
		myColors = new ArrayList<>();
		myColors.addAll(Constants.DEFAULT_COLORS);
	}
	
	/**
	 * Gets the turtle manager.
	 *
	 * @return the turtle manager
	 */
	public MultipleTurtles getTurtleManager(){
		return myTurtleManager;
	}
	
	/**
	 * Gets the variable manager.
	 *
	 * @return the variable manager
	 */
	public VariableManager getVariableManager(){
		return myVariableManager;
	}
	
	/**
	 * Gets the method manager.
	 *
	 * @return the method manager
	 */
	public MethodManager getMethodManager(){
		return myMethodManager;
	}

	/**
	 * Process command.
	 *
	 * @param program the program
	 */
	public void processCommand(Program program) {
		program.execute();
	}
	
	/**
	 * Process toggle.
	 *
	 * @param ID the id
	 */
	public void processToggle(Integer ID){
		myTurtleManager.toggleTurtle(ID);
	}

	/**
	 * Update variable.
	 *
	 * @param name the name
	 * @param newValue the new value
	 */
	public void updateVariable(String name, double newValue){
		myVariableManager.addVariable(name, newValue);
		//System.out.println("added variable to manager: " + myVariableManager.getVarValue(name));
	}
	
	/**
	 * Send info for dialog.
	 *
	 * @param ID the id
	 */
	public void sendInfoForDialog(int ID){
		myView.setUpDialogBox(myTurtleManager.getTurtle(ID).getPen(), ID, myColors);
	}
	
	public void clearTurtles(){
	    myTurtleManager.clearTurtles();
	}
	
	public void addTurtle(double X, double Y, int ID){
	       myTurtleManager.addTurtle(X, Y, ID);
	}
	
	public Map<Integer, SingleTurtle> getTurtleMap(){
	    return myTurtleManager.getTurtleMap();
	}
}
