package view;

import java.io.File;
import slogoEnums.ViewConstants;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
	
	public void changeBackground(String path){
		File imageFile = new File(path);
		myPlayground.setFill(new ImagePattern(new Image(imageFile.toURI().toString())));
	}
	
	
}
