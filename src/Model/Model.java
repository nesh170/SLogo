package Model;

import view.ViewAbstract;

public class Model {
	private ViewAbstract myView;
	private TurtleManager myTurtleManager;
	private VariableManager myVariableManager;
	private MethodManager myMethodManager;

	public Model(ViewAbstract view) {
		myView = view;
		myTurtleManager = new TurtleManager(myView);
		myTurtleManager.addTurtle(0);
		myVariableManager = new VariableManager(myView);
		myMethodManager = new MethodManager();
	}
	
	public TurtleManager getTurtleManager(){
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
	
}
