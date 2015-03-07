package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import view.*;
import Constants.*;

public class MultipleTurtles implements ITurtle {
	private Map<Integer, SingleTurtle> myTurtles;
	private List<Integer> myActiveTurtleIDs;
	private List<SingleTurtle> myActiveTurtles;
	private ViewAbstract myView;

	public MultipleTurtles(ViewAbstract view){
		myTurtles = new HashMap<>();
		myActiveTurtleIDs = new ArrayList<>();
		myActiveTurtles =   new ArrayList<>();
		myView = view;
	}

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
	public List<Integer> getActiveTurtleIDs(){
		return myActiveTurtleIDs;
	}
	
	public List<SingleTurtle> getActiveTurtles(){
		return myActiveTurtles;
	}

	public void doToActiveTurtles(Consumer<? super SingleTurtle> action){
		myActiveTurtles.forEach(action);
	}
	
	private void doToSpecifiedTurtles(List<SingleTurtle> turtles, Consumer<? super SingleTurtle> action){
		turtles.forEach(action);
	}

	public void addTurtle(Integer turtleID){
		myTurtles.put(turtleID, new SingleTurtle(turtleID));
		//automatically activates new turtles
		myActiveTurtleIDs.add(turtleID);
		myActiveTurtles.add(myTurtles.get(turtleID));
		myView.addShape(Constants.DEFAULT_SHAPE, 0, 0, turtleID);
	}
	
	public void addTurtle(double X, double Y, int turtleID){
	    addTurtle(turtleID);
	    myTurtles.get(turtleID).setX(X);myTurtles.get(turtleID).setY(Y);
	    myView.moveShape(X, Y, turtleID);
	}

	public SingleTurtle getTurtle(Integer ID){
		return myTurtles.get(ID);
	}
	
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
	
	public double moveTurtle(double distance, double angle){
		doToActiveExceptFirst(e -> e.moveTurtle(distance, angle));
		return myActiveTurtles.get(0).moveTurtle(distance, angle);
	}
	
	public double relocateTurtle(double x, double y){
		doToActiveExceptFirst(t -> t.relocateTurtle(x, y));
		return myActiveTurtles.get(0).relocateTurtle(x, y);
	}
	
	public void setShapeIndex(int index){
		myActiveTurtles.forEach(e -> e.setShapeIndex(index));
	}
	
	public void setHiding(boolean hiding){
		myActiveTurtles.forEach(e -> e.setHiding(hiding));
	}
	
	public void setDrawing(boolean drawing){
		myActiveTurtles.forEach(e -> e.setDrawing(drawing));
	}
	public void setPenColor(int index){
		myActiveTurtles.forEach(e -> e.setPenColor(index));
	}
	
	public void setPenSize(double pixels){
		myActiveTurtles.forEach(e -> e.setPenSize(pixels));
	}
	
	public void jump(double x, double y){
		myActiveTurtles.forEach(e -> e.jump(x, y));
	}
	
	public void rotate(double angle){
		myActiveTurtles.forEach(e -> e.rotate(angle));
	}
	
	public double setAngle(double angle){
		doToActiveExceptFirst(e -> e.setAngle(angle));
		return myActiveTurtles.get(0).setAngle(angle);
	}
	
	private void doToActiveExceptFirst(Consumer<? super SingleTurtle> action){
		List<SingleTurtle> activeCopy = new ArrayList<>(myActiveTurtles);
		activeCopy.remove(0);
		doToSpecifiedTurtles(activeCopy, action);
	}
	
	public Map<Integer, SingleTurtle> getTurtleMap(){
	    return Collections.unmodifiableMap(myTurtles);
	}
	
	
}
