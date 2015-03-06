package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import view.*;
import Constants.*;

public class TurtleManager {
	private Map<Integer, ModelTurtle> myTurtles;
	private List<Integer> myActiveTurtleIDs;
	private List<ModelTurtle> myActiveTurtles;
	private ViewAbstract myView;

	public TurtleManager(ViewAbstract view){
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
	}

	//should return immutable list instead
	public List<Integer> getActiveTurtleIDs(){
		return myActiveTurtleIDs;
	}
	
	public List<ModelTurtle> getActiveTurtles(){
		return myActiveTurtles;
	}

	public void doToActiveTurtles(Consumer<? super ModelTurtle> action){
		myActiveTurtles.forEach((Consumer<? super ModelTurtle>)action);
	}

	public void addTurtle(Integer turtleID){
		myTurtles.put(turtleID, new ModelTurtle(turtleID));
		//automatically activates new turtles
		myActiveTurtleIDs.add(turtleID);
		myActiveTurtles.add(myTurtles.get(turtleID));
		myView.addShape(Constants.DEFAULT_SHAPE, 0, 0, turtleID);
	}

	public ModelTurtle getTurtle(Integer ID){
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
	
	public void moveTurtle(double distance, double angle){
		myActiveTurtles.forEach(e -> e.moveTurtle(distance, angle));
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
}
