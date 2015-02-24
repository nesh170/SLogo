package Statements;

import java.util.List;

import view.ViewAbstract;
import Constants.*;

public class Product extends Command{

	public Product(List<Statement> statements, ViewAbstract view) {
		super(statements, view);
	}

	@Override
	public double execute() {
		double result = myStatements.get(0).execute() * myStatements.get(1).execute();
		myView.printMessage("" + result);
		return result;
	}

//	@Override
//	public int getNumParams() {
//		return Constants.PRODUCT_PARAMS;
//	}

}
