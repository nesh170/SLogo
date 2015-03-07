package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import view.*;
import Constants.*;

/**
 * This class is a turtle container object.  It is a composite object in our composite design implementation, which
 * has all of the capabilities defined by the ITurtle interface and additional functionality to add, delete, activate,
 * and clear turtles.
 * 
 * @author Sierra, Yancheng
 */
public class MultipleTurtles implements ITurtle {
	private Map<Integer, SingleTurtle> myTurtles;
	private List<Integer> myActiveTurtleIDs;
	private List<SingleTurtle> myActiveTurtles;
	private ViewAbstract myView;

	/**
	 * Constructor for MultipleTurtles. Takes in a view and initializes turtle list and active
	 * turtle list to be empty.
	 * @param view ViewAbstract
	 */
	public MultipleTurtles(ViewAbstract view){
		myTurtles = new HashMap<>();
		myActiveTurtleIDs = new ArrayList<>();
		myActiveTurtles =   new ArrayList<>();
		myView = view;
	}

	/**
	 * Takes in a list of integers and sets turtles with those IDs to be the active turtles
	 * @param activeTurtleIDs List<Integer>
	 */
	public void setActiveTurtles(List<Integer> activeTurtleIDs){
		clearActiveTurtleIDs();
		clearActiveTurtleList();
		for(Integer i: activeTurtleIDs){
			if(!myTurtles.keySet().contains(i)){
				addTurtle(i);
			}
			else{
				myActiveTurtleIDs.add(i);
				myActiveTurtles.add(myTurtles.get(i));
			}
		}
	}

	/**
	 * Cleares the list of active turtle objects
	 */
	private void clearActiveTurtleList() {
		myActiveTurtles.clear();
	}

	/**
	 * Clears the list of active turtle IDs.
	 */
	private void clearActiveTurtleIDs(){
		myActiveTurtleIDs = new ArrayList<>();
	}

	/**
	 * Removes all of the turtles from the model.
	 */
	public void clearTurtles(){
		myTurtles = new HashMap<>();
		clearActiveTurtleIDs();
		clearActiveTurtleList();
	}

	/**
	 *Returns a list of IDs of the active turtles.
	 * @return List<Integer>
	 */
	public List<Integer> getActiveTurtleIDs(){
		return Collections.unmodifiableList(myActiveTurtleIDs);
	}
	
	/**
	 * Returns a list of active turtle objects.
	 * @return List<SingleTurtle>
	 */
	public List<SingleTurtle> getActiveTurtles(){
		return myActiveTurtles;
	}

	/**
	 * Takes in a function and applies it to all active turtles.
	 * @param action Consumer<? super SingleTurtle>
	 * @see Model.ITurtle#doToActiveTurtles(Consumer<? super SingleTurtle>)
	 */
	public void doToActiveTurtles(Consumer<? super SingleTurtle> action){
		myActiveTurtles.forEach(action);
	}
	
	/**
	 * Takes in a function and applies it to the list of turtles passed in.
	 * @param turtles List<SingleTurtle>
	 * @param action Consumer<? super SingleTurtle>
	 */
	private void doToSpecifiedTurtles(List<SingleTurtle> turtles, Consumer<? super SingleTurtle> action){
		turtles.forEach(action);
	}

	/**
	 * Adds a new turtle with the ID specified.  Places the turtle at the home location and automatically
	 * activates it.
	 * @param turtleID Integer
	 */
	public void addTurtle(Integer turtleID){
		myTurtles.put(turtleID, new SingleTurtle(turtleID));
		//automatically activates new turtles
		myActiveTurtleIDs.add(turtleID);
		myActiveTurtles.add(myTurtles.get(turtleID));
		myView.addShape(Constants.DEFAULT_SHAPE, 0, 0, turtleID);
	}
	
	/**
	 * Adds a turtle with the given ID at the specified location (x,y) and automatically activates it.
	 * @param X double
	 * @param Y double
	 * @param turtleID int
	 */
	public void addTurtle(double X, double Y, int turtleID){
	    addTurtle(turtleID);
	    myTurtles.get(turtleID).setX(X);
	    myTurtles.get(turtleID).setY(Y);
	    myView.moveShape(X, Y, turtleID);
	}

	/**
	 * Returns the turtle with the given ID.  Assumes the turtle ID is valid.
	 * @param ID Integer
	 * @return SingleTurtle
	 */
	public SingleTurtle getTurtle(Integer ID){
		return myTurtles.get(ID);
	}
	
	/**
	 * Toggles whether or not the turtle with the given ID is active.  Assumes the turtle
	 * ID is valid.
	 * @param ID Integer
	 */
	public void toggleActive(Integer ID){
		boolean found = false;
		for(Integer i: myActiveTurtleIDs){
			if(ID.equals(i)){
				found = true;
				break;
			}	
		}
		if(found){
			myActiveTurtleIDs.remove(ID);
			myView.visualActiveShape(false, ID);
			System.out.println("Set Turtle to Inactive");
		}
		else{
			myActiveTurtleIDs.add(ID);
			myView.visualActiveShape(true, ID);
			System.out.println("Set Turtle to Active");
		}
	}
	
	/**
	 * Method moveTurtle.
	 * @param distance double
	 * @param angle double
	 * @return double
	 * @see Model.ITurtle#moveTurtle(double, double)
	 */
	public double moveTurtle(double distance, double angle){
		doToActiveExceptFirst(e -> e.moveTurtle(distance, angle));
		return myActiveTurtles.get(0).moveTurtle(distance, angle);
	}
	
	/**
	 * Method relocateTurtle.
	 * @param x double
	 * @param y double
	 * @return double
	 * @see Model.ITurtle#relocateTurtle(double, double)
	 */
	public double relocateTurtle(double x, double y){
		doToActiveExceptFirst(t -> t.relocateTurtle(x, y));
		return myActiveTurtles.get(0).relocateTurtle(x, y);
	}
	
	/**
	 * Method setShapeIndex.
	 * @param index int
	 * @see Model.ITurtle#setShapeIndex(int)
	 */
	public void setShapeIndex(int index){
		myActiveTurtles.forEach(e -> e.setShapeIndex(index));
	}
	
	/**
	 * Method setHiding.
	 * @param hiding boolean
	 * @see Model.ITurtle#setHiding(boolean)
	 */
	public void setHiding(boolean hiding){
		myActiveTurtles.forEach(e -> e.setHiding(hiding));
	}
	
	/**
	 * Method setDrawing.
	 * @param drawing boolean
	 * @see Model.ITurtle#setDrawing(boolean)
	 */
	public void setDrawing(boolean drawing){
		myActiveTurtles.forEach(e -> e.setDrawing(drawing));
	}
	/**
	 * Sets pen color index of the active turtles
	 * @param index int
	 * @see Model.ITurtle#setPenColor(int)
	 */
	public void setPenColor(int index){
		myActiveTurtles.forEach(e -> e.setPenColor(index));
	}
	
	/**
	 * Sets the pen size of all of the active turtles
	 * @param pixels double
	 * @see Model.ITurtle#setPenSize(double)
	 */
	public void setPenSize(double pixels){
		myActiveTurtles.forEach(e -> e.setPenSize(pixels));
	}
	
	/**
	 * Jumps all the active turtles to the specified location.
	 * @param x double
	 * @param y double
	 * @see Model.ITurtle#jump(double, double)
	 */
	public void jump(double x, double y){
		myActiveTurtles.forEach(e -> e.jump(x, y));
	}
	
	/**
	 * Rotates the active turtles
	 * @param angle double
	 * @see Model.ITurtle#rotate(double)
	 */
	public void rotate(double angle){
		myActiveTurtles.forEach(e -> e.rotate(angle));
	}
	
	/**
	 * Sets the angle of active turtles
	 * @param angle double
	 * @return double
	 * @see Model.ITurtle#setAngle(double)
	 */
	public double setAngle(double angle){
		doToActiveExceptFirst(e -> e.setAngle(angle));
		return myActiveTurtles.get(0).setAngle(angle);
	}
	
	/**
	 * Applies the action to all actives turtles except the first one
	 * @param action Consumer<? super SingleTurtle>
	 */
	private void doToActiveExceptFirst(Consumer<? super SingleTurtle> action){
		List<SingleTurtle> activeCopy = new ArrayList<>(myActiveTurtles);
		activeCopy.remove(0);
		doToSpecifiedTurtles(activeCopy, action);
	}
	
	/**
	 * Returns an unmodifiable map of turtle IDs to SingleTurtle objects
	 * @return Map<Integer,SingleTurtle>
	 */
	public Map<Integer, SingleTurtle> getTurtleMap(){
	    return Collections.unmodifiableMap(myTurtles);
	}
	
	
}
