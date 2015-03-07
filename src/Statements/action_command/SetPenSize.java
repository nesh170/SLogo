package Statements.action_command;

import java.util.List;

import slogoEnums.ErrorMessage;
import view.ViewAbstract;
import Model.*;
import Statements.Statement;
import exceptions.*;

/**
 * @author Yancheng, Sierra
 */
public class SetPenSize extends ActionCommand {

	/**
	 * Constructor for SetPenSize.
	 * @param statements List<Statement>
	 * @param view ViewAbstract
	 * @param turtleManager ITurtle
	 */
	public SetPenSize(List<Statement> statements, ViewAbstract view,
			ITurtle turtleManager) {
		super(statements, view, turtleManager);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		double newSize = getMyStatements().get(0).execute();
		try{
			if(newSize < 0){
				throw new ExecutionException(ErrorMessage.PEN_WITDTH.getVal());
			}else{
				getMyTurtles().setPenSize(newSize);
			}
		}catch (ExecutionException e){
			getMyView().printError(e.toString());
		}
		return newSize;
	}

}
