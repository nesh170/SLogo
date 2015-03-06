package Statements.loop;

import java.util.List;

import Constants.*;
import Model.*;
import Statements.Statement;

public class Repeat extends Loop{

	public Repeat(List<List<Statement>> params, VariableManager manager) {
		super(params, manager);
	}

	/**
	 * Returns the value of the last command
	 */
	@Override
	public double execute() {
		System.out.println("Got to repeat execute.");
		int loopTimes = (int)myParams.get(0).get(0).execute();
		System.out.println("Number of loops: " + loopTimes);
		//need to add repcount to the variable table
		//check if it's already there?
		//okay that this starts at 0 default value? it should always be changed
		double result = 0;
		for(int i = Constants.LOOP_START; i <= loopTimes; i++){
			myManager.addVariable(":repcount", (double) i);
			System.out.println("Number of params in repeat loop: " + myParams.get(1).size());
			
			for(int k = 0; k < myParams.get(1).size(); k++){
				System.out.println("Printing child of repeat: " +myParams.get(1).get(k).getClass().toString());
				result = myParams.get(1).get(k).execute();
			}
			//reset rep count
		}
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.REPEAT_PARAMS;
//	}

}
