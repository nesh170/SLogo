package Statements;

import java.util.List;

import view.ViewAbstract;
import Model.ModelTurtle;
import Model.TurtleManager;

public class SetPosition extends ActionCommand{
	protected double myExecuteResultX;
	protected double myExecuteResultY;
	
	
	public SetPosition(List<Statement> statements, ViewAbstract view,
			TurtleManager turtleManager) {
		super(statements, view, turtleManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double execute() {
		double distanceTraveled = 0;
		for(Integer ID: myTurtleManager.getActiveTurtles()){
			ModelTurtle currentTurtle = myTurtleManager.getTurtle(ID);
			double oldX = currentTurtle.getX();
			double oldY = currentTurtle.getY();
			currentTurtle.setX(myExecuteResultX);
			currentTurtle.setY(myExecuteResultY);
			distanceTraveled = Math.sqrt(Math.pow(myExecuteResultX - oldX, 2) + Math.pow(myExecuteResultY - oldY, 2));
			System.out.println("X is "+currentTurtle.getX()+" Y is "+currentTurtle.getY());
			if(currentTurtle.isPenUp()){
				myView.moveTurtle(currentTurtle.getX(), currentTurtle.getY(), currentTurtle.getID());
			} else {
				myView.drawTurtle(currentTurtle.getX(), currentTurtle.getY(), currentTurtle.getID());
			}
		}
		return distanceTraveled;
	}

}
