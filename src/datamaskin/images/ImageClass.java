package datamaskin.images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageClass {
    //metode som oppretter et bilde via path og returnerer et bilde
    public Image createImage(String path) throws FileNotFoundException {
        try {
            FileInputStream imageStream = new FileInputStream(path);
            return new Image(imageStream);
        } catch(FileNotFoundException e){
            System.err.println("Bildefilen gikk ikke å lese inn. Sjekk i .src/datamaskin/images.");
        }
        FileInputStream imageStream = new FileInputStream("./src/datamaskin/images/missingImage.png");
        return new Image(imageStream);
    }

    //metode som kobler imageviewet med bildet - hannah
    public void setImageView(ImageView iv, javafx.scene.image.Image image){
        iv.setImage(image);
    }

}
