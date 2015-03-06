package Statements.operator;

import java.util.List;

import Statements.Statement;

public class Equal extends Operator {

	public Equal(List<Statement> statements) {
		super(statements);
	}

	@Override
	public double execute() {
		if(myStatements.get(0).execute() == myStatements.get(1).execute()){
			return 1;
		}
		return 0;
	}

}
