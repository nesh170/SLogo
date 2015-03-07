package Model;

import java.util.ArrayList;
import java.util.List;

import view.ViewAbstract;
import Constants.*;

public class Model {
	private ViewAbstract myView;
	private MultipleTurtles myTurtleManager;
	private VariableManager myVariableManager;
	private MethodManager myMethodManager;
	private List<String> myShapes;
	private List<String> myColors;

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
	
	public List<String> getColors(){
		return myColors;
	}
	
	public List<String> getShapes(){
		return myShapes;
	}
	
	public void initializeDefaultShapes(){
		myShapes = new ArrayList<>();
		myShapes.addAll(Constants.DEFAULT_SHAPES);
	}
	
	public void initializeDefaultColors(){
		myColors = new ArrayList<>();
		myColors.addAll(Constants.DEFAULT_COLORS);
	}
	
	public MultipleTurtles getTurtleManager(){
		return myTurtleManager;
	}
	
	public VariableManager getVariableManager(){
		return myVariableManager;
	}
	
	public MethodManager getMethodManager(){
		return myMethodManager;
	}

	public void processCommand(Program program) {
		program.execute();
	}
	
	public void processToggle(Integer ID){
		myTurtleManager.toggleTurtle(ID);
	}

	public void updateVariable(String name, double newValue){
		myVariableManager.addVariable(name, newValue);
		System.out.println("added variable to manager: " + myVariableManager.getVarValue(name));
	}
	
	public void sendInfoForDialog(int ID){
		myView.setUpDialogBox(myTurtleManager.getTurtle(ID).getPen(), ID, myColors);
	}
	
}
