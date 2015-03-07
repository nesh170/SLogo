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
		int index = (int) getMyStatements().get(0).execute();
		try {
			if (index < 0 || index > myColors.size()) {
				throw new ExecutionException(
						"Specified index for new color is out of bounds.");
			} else if(index<myColors.size()) {
				myColors.set(index,calculateStringColor((int) getMyStatements().get(1)
						.execute(), (int) getMyStatements().get(2).execute(),
						(int) getMyStatements().get(3).execute()));
				getMyView().updateColorListView(myColors);
			} else if(index==myColors.size()){
                                myColors.add(calculateStringColor((int) getMyStatements().get(1)
                                               .execute(), (int) getMyStatements().get(2).execute(),
                                                (int) getMyStatements().get(3).execute()));
                                getMyView().updateColorListView(myColors);
			}
		} catch (ExecutionException e) {
			getMyView().printError(e.toString());
		}
		return (double) index;
	}

	public String calculateStringColor(int r, int g, int b) {
		return String.format("#%02X%02X%02X", r,g,b);
	}

}
