package Statements;

import java.util.List;

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
			} else {
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
		return String.format("#%02X%02X%02X", (int) (r * 255), (int) (g * 255),
				(int) (b * 255));
	}

}
