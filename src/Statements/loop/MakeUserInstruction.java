package Statements.loop;

import java.util.List;

import Model.MethodManager;
import Model.VariableManager;
import Statements.MethodName;
import Statements.Statement;

public class MakeUserInstruction extends Loop {
	
	private MethodManager myMethodManager;
	
	public MakeUserInstruction(List<List<Statement>> params,
			VariableManager manager, MethodManager methodManager) {
		super(params, manager);
		myMethodManager = methodManager;
	}

	@Override
	public double execute() {
		String methodName = ((MethodName) getMyParams().get(0).get(0)).getName();
		myMethodManager.addMethod(methodName, getMyParams().get(1), getMyParams().get(2));
		System.out.println("Very successfully added a method"+ methodName);
		return 1;
	}

}
