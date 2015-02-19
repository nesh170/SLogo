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
			//check if its in the list, if not create it, then add all to active list
		}
	}
	
	private void clearActiveTurtles(){
		myActiveTurtles = new ArrayList<>();
	}
}
