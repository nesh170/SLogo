package Statements.command;

import java.util.List;

import Statements.Statement;
import view.ViewAbstract;
import exceptions.*;

public class SetPalette extends Command {
	private List<String> myColors;

	public SetPalette(List<Statement> statements, ViewAbstract view,
			List<String> colors) {
		super(statements, view);
		myColors = colors;
	}

	@Override
	public double execute() {
		int index = (int) myStatements.get(0).execute();
		try {
			if (index < 0 || index > myColors.size()) {
				throw new ExecutionException(
						"Specified index for new color is out of bounds.");
			} else if(index<myColors.size()) {
				myColors.set(index,calculateStringColor((int) myStatements.get(1)
						.execute(), (int) myStatements.get(2).execute(),
						(int) myStatements.get(3).execute()));
				myView.updateColorListView(myColors);
			} else if(index==myColors.size()){
                                myColors.add(calculateStringColor((int) myStatements.get(1)
                                               .execute(), (int) myStatements.get(2).execute(),
                                                (int) myStatements.get(3).execute()));
                                myView.updateColorListView(myColors);
			}
		} catch (ExecutionException e) {
			myView.printError(e.toString());
		}
		return (double) index;
	}

	public String calculateStringColor(int r, int g, int b) {
		return String.format("#%02X%02X%02X", r,g,b);
	}

}
