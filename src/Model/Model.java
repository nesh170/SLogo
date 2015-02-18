package Model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	// private ViewAbstract myView;
	// private Parser myParser;
	private List<ModelTurtle> myTurtleList;
	private List<ModelTurtle> myActiveTurtles;

	// add view when siva pushes to master
	public Model() {
		// myView = view;
		// myParser = new Parser();
		myTurtleList = new ArrayList<>();
		myActiveTurtles = new ArrayList<>();
	}
	
	public void processCommand(String program){
		
	}
	
	public void changeLanguage(){
		
	}
	
}
