package Datamaskin.images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageClass {
    //metode som oppretter et bilde via path og returnerer et bilde
    public Image createImage(String path) throws FileNotFoundException {
        FileInputStream imageStream = new FileInputStream(path);
        return new Image(imageStream);
    }

    //metode som kobler imageviewet med bildet - hannah
    public void setImageView(ImageView iv, javafx.scene.image.Image image){
        iv.setImage(image);
    }

}
