package Statements.action_command;

import java.util.List;

import exceptions.ExecutionException;
import view.ViewAbstract;
import Model.ITurtle;
import Statements.Statement;

public abstract class SetVisual extends ActionCommand {
	private List<String> myVisualList;
	private String myErrorMessage;
	
	public SetVisual(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> listOfVisualItems) {
		super(statements, view, turtleManager);
		myVisualList = listOfVisualItems;
		myErrorMessage = "Invalid index.";
	}

	@Override
	public double execute() {
		int index = (int) getMyStatements().get(0).execute();
		try{
			if(index >= myVisualList.size() || index < 0){
				throw new ExecutionException(myErrorMessage);
			}else{
				completeOperation(index);
			}
		}catch(ExecutionException e){
			getMyView().printError(e.toString());
		}
		return (double) index;
	}
	
	public void setErrorMessage(String message){
		myErrorMessage = message;
	}

	public abstract void completeOperation(int index);
	
	public List<String> getVisualList(){
		return myVisualList;
	}
	
}
