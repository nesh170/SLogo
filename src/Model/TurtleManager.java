package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TurtleManager {
	private Map<Integer, ModelTurtle> myTurtles;
	private List<Integer> myActiveTurtleIDs;
	private List<ModelTurtle> myActiveTurtles;
	
	public TurtleManager(){
		myTurtles = new HashMap<>();
		myActiveTurtleIDs = new ArrayList<>();
		myActiveTurtles =   new ArrayList<>();
	}
	
	public void setActiveTurtles(List<Integer> activeTurtleIDs){
		clearActiveTurtleIDs();
		clearActiveTurtleList();
		for(Integer i: activeTurtleIDs){
			myActiveTurtleIDs.add(i);
			myActiveTurtles.add(myTurtles.get(i));
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
	public List<Integer> getActiveTurtles(){
		return myActiveTurtleIDs;
	}
	
	public void doToActiveTurtles(Consumer<? super ModelTurtle> action){
		myActiveTurtles.forEach((Consumer<? super ModelTurtle>)action);
	}
	
	public void addTurtle(Integer turtleID){
		myTurtles.put(turtleID, new ModelTurtle(turtleID));
		//automatically activates new turtles
		myActiveTurtleIDs.add(turtleID);
	}
	
	public ModelTurtle getTurtle(Integer ID){
		return myTurtles.get(ID);
	}
}
