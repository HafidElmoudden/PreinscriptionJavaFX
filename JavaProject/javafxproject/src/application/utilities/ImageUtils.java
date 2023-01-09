package application.utilities;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtils {
    public static void setButtonImage(Class _class,Button btn, String imageName) {
    	Image deleteImage = new Image(_class.getResourceAsStream(imageName));
    	ImageView imageView = new ImageView(deleteImage);
    	imageView.setFitHeight(32);
    	imageView.setFitWidth(32);
    	btn.setMinHeight(32);
    	btn.setMinWidth(32);
    	btn.setPadding(new Insets(0,6,0,0));
    	btn.setStyle("-fx-background-color: transparent");
    	btn.setGraphic(imageView);
    }
}
