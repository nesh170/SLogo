package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleManager {
	private Map<Integer, ModelTurtle> myTurtles;
	private List<Integer> myActiveTurtles;
	
	public TurtleManager(){
		myTurtles = new HashMap<>();
		myActiveTurtles = new ArrayList<>();
	}
	
	public void setActiveTurtles(List<Integer> activeTurtleIDs){
		clearActiveTurtles();
		for(Integer i: activeTurtleIDs){
			myActiveTurtles.add(i);
		}
	}
	
	private void clearActiveTurtles(){
		myActiveTurtles = new ArrayList<>();
	}
	
	//should return immutable list instead
	public List<Integer> getActiveTurtles(){
		return myActiveTurtles;
	}
	
	public void addTurtle(Integer turtleID){
		myTurtles.put(turtleID, new ModelTurtle(turtleID));
	}
	
	public ModelTurtle getTurtle(Integer ID){
		return myTurtles.get(ID);
	}
}
