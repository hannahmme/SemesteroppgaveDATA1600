package datamaskin.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import static datamaskin.product.ProductRegister.*;

public class ProductCategories implements Serializable {
    private static final long serialVersionUID = 1;

    // alle de forskjellige kategoriene et produkt kan være innenfor
    public static ObservableList<Product> GraphicCard = FXCollections.observableArrayList();    // Skjermkort
    public static ObservableList<Product> Memorycard = FXCollections.observableArrayList();     // Minnekort
    public static ObservableList<Product> Harddrive = FXCollections.observableArrayList();      // Harddisk
    public static ObservableList<Product> Processor = FXCollections.observableArrayList();      // Prosessor
    public static ObservableList<Product> Power = FXCollections.observableArrayList();          // Strømforsyning
    public static ObservableList<Product> Soundcard = FXCollections.observableArrayList();      // Lydkort
    public static ObservableList<Product> OpticalDisk = FXCollections.observableArrayList();    // Optisk disk
    public static ObservableList<Product> Color = FXCollections.observableArrayList();          // Farge
    public static ObservableList<Product> OtherProducts = FXCollections.observableArrayList();  // Andre produkter som mus, tastatur, skjerm osv

    public static void deleteDataFromCategories(){
        GraphicCard.clear();
        Memorycard.clear();
        Harddrive.clear();
        Processor.clear();
        Power.clear();
        Soundcard.clear();
        OpticalDisk.clear();
        Color.clear();
        OtherProducts.clear();
    }

    public static void addDataToCategories(){
            for(Product aProduct : ProductRegister) {
                switch (aProduct.getCategory()) {
                    case "Skjermkort":
                        GraphicCard.add(aProduct);
                        break;
                    case "Minnekort":
                        Memorycard.add(aProduct);
                        break;
                    case "Harddisk":
                        Harddrive.add(aProduct);
                        break;
                    case "Prosessor":
                        Processor.add(aProduct);
                        break;
                    case "Strømforsyning":
                        Power.add(aProduct);
                        break;
                    case "Lydkort":
                        Soundcard.add(aProduct);
                        break;
                    case "Optisk disk":
                        OpticalDisk.add(aProduct);
                        break;
                    case "Farge":
                        Color.add(aProduct);
                        break;
                    case "Andre produkter":
                        OtherProducts.add(aProduct);
                        break;
                }
            }
    }

    // eksempeldata, så slipper vi å lage nye produkter hver gang vi kjører progr.
    public static void setExampleData(){
        Product graphiccard1 = new Product("Skjermkort 1", "Dette er vårt billigste skjermkort.", 7, 299, "Skjermkort", "./src/Datamaskin/images/missingImage.png");
        Product graphiccard2 = new Product("Skjermkort 2", "Dette er vårt nest billigste skjermkort.", 10, 399, "Skjermkort", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(graphiccard1, graphiccard2);

        Product memorycard1 = new Product("4GB RAM", "Dette er vårt billigste minnekort.", 5, 299, "Minnekort", "./src/Datamaskin/images/missingImage.png");
        Product memorycard2 = new Product("8GB RAM", "Dette er vårt beste minnekort.", 10, 699, "Minnekort", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(memorycard1, memorycard2);

        Product harddrive1 = new Product("128 GB SSD", "Egnet til pcer for vanlig bruk.", 5, 299, "Harddisk", "./src/Datamaskin/images/missingImage.png");
        Product harddrive2 = new Product("256 GB SSD", "Dette er en harddisk med rask lagring.", 7, 999, "Harddisk", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(harddrive1, harddrive2);

        Product processor1 = new Product("Intel Core I3", "Eldre versjon av prosessor.", 5, 299, "Prosessor", "./src/Datamaskin/images/missingImage.png");
        Product processor2 = new Product("Intel Core I5", "Nyere versjon av prosessor.", 5, 499, "Prosessor", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(processor1, processor2);

        Product Power1 = new Product("Strømforsyning 1", "0.5 meter ledning.", 5, 299, "Strømforsyning", "./src/Datamaskin/images/missingImage.png");
        Product Power2 = new Product("Strømforsyning 2", "1.5 meter ledning.", 5, 399, "Strømforsyning", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(Power1, Power2);

        Product Soundcard1 = new Product("Lydkort 1", "God lyd.", 5, 299, "Lydkort", "./src/Datamaskin/images/missingImage.png");
        Product Soundcard2 = new Product("Lydkort 2", "Den beste lyden på markedet.", 7, 799, "Lydkort", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(Soundcard1, Soundcard2);

        Product Opticaldisk1 = new Product("Optisk disk 1", "God opplevelse til daglige gjøremål.", 5, 299, "Optisk disk", "./src/Datamaskin/images/missingImage.png");
        Product Opticaldisk2 = new Product("Optisk disk 2", "Godt egnet for gaming.", 7, 1429, "Optisk disk", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(Opticaldisk1, Opticaldisk2);

        Product color1 = new Product("Rød", "En fin farge inspirert av rød rose.", 10, 299, "Farge", "./src/Datamaskin/images/missingImage.png");
        Product color2 = new Product("Svart som olje", "En tøff oljesvart farge med glans.", 10, 499, "Farge", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(color1, color2);

        // disse andre produktene skal knyttes mot tableview på "ExtraOrderEnduserPage"
        Product otherProducts1 = new Product("Tastatur 1", "Dette er vårt billigste tastatur.", 3, 179, "Andre produkter", "./src/Datamaskin/images/keyboard1.jpg");
        Product otherProducts2 = new Product("Tastatur 2", "Dette er vårt dyreste tastatur.", 3, 779, "Andre produkter", "./src/Datamaskin/images/keyboard2.jpg");
        Product otherProducts3 = new Product("Ekstern skjerm 1", "Stor skjerm så man ikke får vondt i øynene.", 3, 1179, "Andre produkter", "./src/Datamaskin/images/missingImage.png");
        Product otherProducts4 = new Product("Mus 1", "Fin for hånda. Bra passform.", 3, 579, "Andre produkter","./src/Datamaskin/images/mus1.jpg");
        Product otherProducts5 = new Product("Headset 1", "Nyeste på markedet, god kvalitet.", 10,  100, "Andre produkter", "./src/Datamaskin/images/headset1.jpg");
        Product otherProducts6 = new Product("Headset 2", "Svært god lydkvalitet.", 5,  300, "Andre produkter", "./src/Datamaskin/images/headset2.jpg");
        Product otherProducts7 = new Product("Webkamera 1", "Nå med nattmodus!", 2,299, "Andre produkter", "./src/Datamaskin/images/webkamera1.jpg");
        Product otherProducts8 = new Product("Webkamera 2", "Større kamera med vidvinkel.", 2,299, "Andre produkter", "./src/Datamaskin/images/webkamera2.jpg");
        Product otherProducts9  = new Product("Mus 2", "Passer til alle pcer.",5 ,499, "Andre produkter", "./src/Datamaskin/images/mus2.jpg");
        Product otherProducts10 = new Product("Mus 3", "Godt egnet for gaming.",5 ,499, "Andre produkter", "./src/Datamaskin/images/missingImage.png");
        ProductRegister.addAll(otherProducts1, otherProducts2, otherProducts3, otherProducts4, otherProducts5, otherProducts6,
                otherProducts7, otherProducts8, otherProducts9, otherProducts10);
    }
}