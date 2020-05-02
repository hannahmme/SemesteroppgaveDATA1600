package datamaskin.product;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1;

    private final String MISSING_IMG_PATH = "./src/Datamaskin/images/missingImage.png";
    private transient SimpleStringProperty name;
    private transient SimpleStringProperty description;
    private transient SimpleIntegerProperty lifetime;
    private transient SimpleDoubleProperty price;
    private transient SimpleStringProperty category;
    private transient SimpleStringProperty imageUri;

    public Product(String name, String description, int lifetime, double price, String category) {
        this.name        = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.lifetime    = new SimpleIntegerProperty(lifetime);
        this.price       = new SimpleDoubleProperty(price);
        this.category    = new SimpleStringProperty(category);
    }

    //konstruktør som også tar inn bildeSti (slik at det er valgfritt å legge til bilde)
    public Product(String name, String description, int lifetime, double price, String category, String imageUri) {
        this.name        = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.lifetime    = new SimpleIntegerProperty(lifetime);
        this.price       = new SimpleDoubleProperty(price);
        this.category    = new SimpleStringProperty(category);
        //this.imageUri    = new SimpleStringProperty(imageUri);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String componentName) {
        this.name = new SimpleStringProperty(componentName);
    }

    public String getDescription() {
        return description.get();
    }
    public void setDescription(String componentDescription) {
        this.description = new SimpleStringProperty(componentDescription);
    }

    public int getLifetime() {
        return lifetime.get();
    }
    public void setLifetime(int componentLifetime) {
        this.lifetime = new SimpleIntegerProperty(componentLifetime);
    }

    public double getPrice() {
        return price.get();
    }
    public void setPrice(double componentPrice) {
        this.price = new SimpleDoubleProperty(componentPrice);
    }

    public String getCategory() {
        return category.get();
    }
    public void setCategory(String componentCategory) {
        this.category = new SimpleStringProperty(componentCategory);
    }

    //hvis et produkt ikke har et bilde (det er valgtfritt å legge til for admin når admin oppretter et nytt produkt), vil "missingImage" settes i ImagesViewet.
    public String getImageUri() {
       String imgUri = MISSING_IMG_PATH;

        if(imageUri != null){
            imgUri = imageUri.get();
        }
        if(imgUri.isEmpty()){
            imgUri = MISSING_IMG_PATH;
        }
        return imgUri;
    }

    // todo: Hannah
    public void setImageUri(String imageUri) {
        this.imageUri = new SimpleStringProperty(imageUri);
    }

    @Override public String toString(){
        return String.format("%s;%s;%s;%s;%s",
                name.getValue(), description.getValue(), lifetime.toString(),
                price.getValue(), category.getValue());
    }


    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getName());
        s.writeUTF(getDescription());
        s.writeInt(getLifetime());
        s.writeDouble(getPrice());
        s.writeUTF(getCategory());
        //todo: Hannah - fortsett her
       /* s.writeUTF(getImageUri());*/
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String name = s.readUTF();
        String description = s.readUTF();
        int lifetime = s.readInt();
        double price = s.readDouble();
        String category = s.readUTF();

        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.lifetime = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
        this.category = new SimpleStringProperty();

        setName(name);
        setDescription(description);
        setLifetime(lifetime);
        setPrice(price);
        setCategory(category);
    }

}
