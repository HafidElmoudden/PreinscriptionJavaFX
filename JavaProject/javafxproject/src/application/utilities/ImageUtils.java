package application.utilities;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtils {
    public static void setButtonImage(Class _class,Button btn, String imageName, int height, int width, boolean isMargin) {
    	Image deleteImage = new Image(_class.getResourceAsStream(imageName));
    	ImageView imageView = new ImageView(deleteImage);
    	imageView.setFitHeight(height);
    	imageView.setFitWidth(width);
    	btn.setMinHeight(height);
    	btn.setMinWidth(width);
    	if(isMargin)
    		btn.setPadding(new Insets(0,6,0,0));
    	btn.setStyle("-fx-background-color: transparent");
    	btn.setGraphic(imageView);
    }
}
