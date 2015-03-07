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
 */
public class MultipleTurtles implements ITurtle {
	private Map<Integer, SingleTurtle> myTurtles;
	private List<Integer> myActiveTurtleIDs;
	private List<SingleTurtle> myActiveTurtles;
	private ViewAbstract myView;

	/**
	 * Constructor for MultipleTurtles.
	 * @param view ViewAbstract
	 */
	public MultipleTurtles(ViewAbstract view){
		myTurtles = new HashMap<>();
		myActiveTurtleIDs = new ArrayList<>();
		myActiveTurtles =   new ArrayList<>();
		myView = view;
	}

	/**
	 * Method setActiveTurtles.
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

	private void clearActiveTurtleList() {
		myActiveTurtles.clear();
	}

	private void clearActiveTurtleIDs(){
		myActiveTurtleIDs = new ArrayList<>();
	}

	public void clearTurtles(){
		myTurtles = new HashMap<>();
		clearActiveTurtleIDs();
		clearActiveTurtleList();
	}

	//should return immutable list instead
	/**
	 * Method getActiveTurtleIDs.
	 * @return List<Integer>
	 */
	public List<Integer> getActiveTurtleIDs(){
		return myActiveTurtleIDs;
	}
	
	/**
	 * Method getActiveTurtles.
	 * @return List<SingleTurtle>
	 */
	public List<SingleTurtle> getActiveTurtles(){
		return myActiveTurtles;
	}

	/**
	 * Method doToActiveTurtles.
	 * @param action Consumer<? super SingleTurtle>
	 * @see Model.ITurtle#doToActiveTurtles(Consumer<? super SingleTurtle>)
	 */
	public void doToActiveTurtles(Consumer<? super SingleTurtle> action){
		myActiveTurtles.forEach(action);
	}
	
	/**
	 * Method doToSpecifiedTurtles.
	 * @param turtles List<SingleTurtle>
	 * @param action Consumer<? super SingleTurtle>
	 */
	private void doToSpecifiedTurtles(List<SingleTurtle> turtles, Consumer<? super SingleTurtle> action){
		turtles.forEach(action);
	}

	/**
	 * Method addTurtle.
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
	 * Method addTurtle.
	 * @param X double
	 * @param Y double
	 * @param turtleID int
	 */
	public void addTurtle(double X, double Y, int turtleID){
	    addTurtle(turtleID);
	    myTurtles.get(turtleID).setX(X);myTurtles.get(turtleID).setY(Y);
	    myView.moveShape(X, Y, turtleID);
	}

	/**
	 * Method getTurtle.
	 * @param ID Integer
	 * @return SingleTurtle
	 */
	public SingleTurtle getTurtle(Integer ID){
		return myTurtles.get(ID);
	}
	
	/**
	 * Method toggleTurtle.
	 * @param ID Integer
	 */
	public void toggleTurtle(Integer ID){
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
	//ITurtle Methods
	
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
	 * Method setPenColor.
	 * @param index int
	 * @see Model.ITurtle#setPenColor(int)
	 */
	public void setPenColor(int index){
		myActiveTurtles.forEach(e -> e.setPenColor(index));
	}
	
	/**
	 * Method setPenSize.
	 * @param pixels double
	 * @see Model.ITurtle#setPenSize(double)
	 */
	public void setPenSize(double pixels){
		myActiveTurtles.forEach(e -> e.setPenSize(pixels));
	}
	
	/**
	 * Method jump.
	 * @param x double
	 * @param y double
	 * @see Model.ITurtle#jump(double, double)
	 */
	public void jump(double x, double y){
		myActiveTurtles.forEach(e -> e.jump(x, y));
	}
	
	/**
	 * Method rotate.
	 * @param angle double
	 * @see Model.ITurtle#rotate(double)
	 */
	public void rotate(double angle){
		myActiveTurtles.forEach(e -> e.rotate(angle));
	}
	
	/**
	 * Method setAngle.
	 * @param angle double
	 * @return double
	 * @see Model.ITurtle#setAngle(double)
	 */
	public double setAngle(double angle){
		doToActiveExceptFirst(e -> e.setAngle(angle));
		return myActiveTurtles.get(0).setAngle(angle);
	}
	
	/**
	 * Method doToActiveExceptFirst.
	 * @param action Consumer<? super SingleTurtle>
	 */
	private void doToActiveExceptFirst(Consumer<? super SingleTurtle> action){
		List<SingleTurtle> activeCopy = new ArrayList<>(myActiveTurtles);
		activeCopy.remove(0);
		doToSpecifiedTurtles(activeCopy, action);
	}
	
	/**
	 * Method getTurtleMap.
	 * @return Map<Integer,SingleTurtle>
	 */
	public Map<Integer, SingleTurtle> getTurtleMap(){
	    return Collections.unmodifiableMap(myTurtles);
	}
	
	
}
