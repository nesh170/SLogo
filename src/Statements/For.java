package Statements;

import java.util.List;

import Model.VariableManager;

public class For extends Loop {

	public For(List<List<Statement>> params, VariableManager manager) {
		super(params, manager);
	}

	@Override
	public double execute() {
		System.out.println("Executing for Loop.");
		
		int start = (int) myParams.get(0).get(1).execute();
		int end = (int) myParams.get(0).get(2).execute();
		int increment = (int) myParams.get(0).get(3).execute();
		
		for(int i = start; i <= end; i += increment){
			myManager.addVariable(((Variable)myParams.get(0).get(0)).getName(), (double) i);
			
		}
		
		
		
		return 0;
	}

}
