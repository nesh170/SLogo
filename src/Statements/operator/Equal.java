package Statements.operator;

import java.util.List;

import Statements.Statement;
/**
 * 
 * @author Sivaneshwaran
 *
 */
public class Equal extends Operator {

	public Equal(List<Statement> statements) {
		super(statements);
	}

	@Override
	public double execute() {
		if(getMyStatements().get(0).execute() == getMyStatements().get(1).execute()){
			return 1;
		}
		return 0;
	}

}
