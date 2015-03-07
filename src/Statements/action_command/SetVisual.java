package Statements.action_command;

import java.util.List;

import exceptions.ExecutionException;
import view.ViewAbstract;
import Model.ITurtle;
import Statements.Statement;

/** This class handles commands that involve the visual settings of the turtle.
 * @author Yancheng, Sierra
 */
public abstract class SetVisual extends ActionCommand {
	private List<String> myVisualList;
	private String myErrorMessage;
	
	/**
	 * Constructor for SetVisual.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 * @param listOfVisualItems List<String>
	 */
	public SetVisual(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager, List<String> listOfVisualItems) {
		super(statements, view, turtleManager);
		myVisualList = listOfVisualItems;
		myErrorMessage = "Invalid index.";
	}

	/**
	 * Method execute.
	 * @return double
	 */
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
	
	/**
	 * Method setErrorMessage.
	 * @param message String
	 */
	public void setErrorMessage(String message){
		myErrorMessage = message;
	}

	/**
	 * Method completeOperation.
	 * @param index int
	 */
	public abstract void completeOperation(int index);
	
	/**
	 * Method getVisualList.
	 * @return List<String>
	 */
	public List<String> getVisualList(){
		return myVisualList;
	}
	
}
