package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import view.*;

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
				System.out.println("ADDING TURTLE TO LIST 1");
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
	public List<Integer> getActiveTurtles(){
		return myActiveTurtleIDs;
	}

	public void doToActiveTurtles(Consumer<? super ModelTurtle> action){
		myActiveTurtles.forEach((Consumer<? super ModelTurtle>)action);
	}

	public void addTurtle(Integer turtleID){
		System.out.println("ADDING TURTLE TO LIST 2");
		myTurtles.put(turtleID, new ModelTurtle(turtleID));
		//automatically activates new turtles
		myActiveTurtleIDs.add(turtleID);
		myActiveTurtles.add(myTurtles.get(turtleID));
		myView.addTurtle(0, 0, turtleID);
	}

	public ModelTurtle getTurtle(Integer ID){
		return myTurtles.get(ID);
	}
}
