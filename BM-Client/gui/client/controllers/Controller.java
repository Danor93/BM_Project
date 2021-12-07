package client.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller  {
	/** setImage this method setImage for give imageview */
	public void setImage(ImageView img, String ImageName) {
		Image image;
		image = new Image(getClass().getResourceAsStream("/Image/" + ImageName));
		img.setImage(image);
	}
}

