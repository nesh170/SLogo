package Statements.loop;

import java.util.List;

import Model.VariableManager;
import Statements.Statement;
import Statements.Variable;

/**
 * @author Yancheng, Sierra
 */
public class For extends Loop {

	/**
	 * Constructor for For.
	 * @param params List<List<Statement>>
	 * @param manager VariableManager
	 */
	public For(List<List<Statement>> params, VariableManager manager) {
		super(params, manager);
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {		
		int start = (int) getMyParams().get(0).get(1).execute();
		int end = (int) getMyParams().get(0).get(2).execute();
		int increment = (int) getMyParams().get(0).get(3).execute();
		double result = 0;
		for(int i = start; i <= end; i += increment){
			getMyManager().addVariable(((Variable)getMyParams().get(0).get(0)).getName(), (double) i);
			for(Statement curStatement: getMyParams().get(1)){
				result = curStatement.execute();
			}
		}
		return result;
	}

}
