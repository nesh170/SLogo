package Statements;

import java.util.List;
import java.util.Random;

import view.ViewAbstract;

public class RandomGen extends Command{
	private Random myRandom;
	
	public RandomGen(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
		myRandom = new Random();
	}

	@Override
	public double execute() {
		double result = myRandom.nextDouble()*myStatements.get(0).execute();
		myView.printMessage("" + result);
		return result;
	}

}
