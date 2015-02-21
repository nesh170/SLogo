package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TurtlePlayground {
	private Rectangle myPlayground;
	
	public TurtlePlayground(Group root){
		initializePlayground(root);
	}

	private void initializePlayground(Group root) {
		myPlayground = new Rectangle(ViewConstants.PLAYGROUND_WIDTH.getVal(), ViewConstants.PLAYGROUND_HEIGHT.getVal(),Color.ROSYBROWN);
		myPlayground.toBack();
		root.getChildren().add(myPlayground);
	}
	
	
}
