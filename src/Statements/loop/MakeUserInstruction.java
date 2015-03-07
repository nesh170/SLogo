package Statements.loop;

import java.util.List;

import Model.MethodManager;
import Model.VariableManager;
import Statements.MethodName;
import Statements.Statement;

/**
 * @author Yancheng, Sierra
 */
public class MakeUserInstruction extends Loop {
	
	private MethodManager myMethodManager;
	
	/**
	 * Constructor for MakeUserInstruction.
	 * @param params List<List<Statement>>
	 * @param manager VariableManager
	 * @param methodManager MethodManager
	 */
	public MakeUserInstruction(List<List<Statement>> params,
			VariableManager manager, MethodManager methodManager) {
		super(params, manager);
		myMethodManager = methodManager;
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		String methodName = ((MethodName) getMyParams().get(0).get(0)).getName();
		myMethodManager.addMethod(methodName, getMyParams().get(1), getMyParams().get(2));
		System.out.println("Very successfully added a method"+ methodName);
		return 1;
	}

}
