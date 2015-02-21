package Statements;

import java.util.List;
import Constants.*;
import Model.*;

public class Repeat extends Loop{

	public Repeat(List<Statement> list1, List<Statement> list2, VariableManager manager) {
		super(list1, list2, manager);
	}

	/**
	 * Returns the value of the last command
	 */
	@Override
	public double execute() {
		int loopTimes = (int)myFirstList.get(0).execute();
		//need to add repcount to the variable table
		//check if it's already there?
		//okay that this starts at 0 default value? it should always be changed
		double result = 0;
		for(int i = Constants.LOOP_START; i <= loopTimes; i++){
			
			for(int k = 0; i < mySecondList.size(); k++){
				result = mySecondList.get(k).execute();
			}
			//reset rep count
		}
		return result;
	}

}
