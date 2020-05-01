package datamaskin.product;

import datamaskin.fxml.ProductAdmPageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;

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

    public static void setData(Product etProduct, String category){
        switch(category){
            case "Skjermkort" : GraphicCard.add(etProduct); break;
            case "Minnekort" : Memorycard.add(etProduct); break;
            case "Harddisk" : Harddrive.add(etProduct); break;
            case "Prosessor" : Processor.add(etProduct); break;
            case "Strømforsyning" : Power.add(etProduct); break;
            case "Lydkort" : Soundcard.add(etProduct); break;
            case "Optisk disk" : OpticalDisk.add(etProduct); break;
            case "Farge" : Color.add(etProduct); break;
            case "Andre produkter" : OtherProducts.add(etProduct);
        }
    }

    // eksempeldata, så slipper vi å lage nye produkter hver gang vi kjører progr.
    public static void setExampleData(){
        Product graphiccard1 = new Product("Skjermkort 1", "Dette er vårt billigste skjermkort.", 7, 299, "Skjermkort");
        Product graphiccard2 = new Product("Skjermkort 2", "Dette er vårt nest billigste skjermkort.", 10, 399, "Skjermkort");
        ProductAdmPageController.aRegister.addElement(graphiccard1);
        ProductAdmPageController.aRegister.addElement(graphiccard2);
        GraphicCard.addAll(graphiccard1, graphiccard2);

        Product memorycard1 = new Product("4GB RAM", "Dette er vårt billigste minnekort.", 5, 299, "Minnekort");
        Product memorycard2 = new Product("8GB RAM", "Dette er vårt beste skjermkort.", 10, 699, "Minnekort");
        ProductAdmPageController.aRegister.addElement(memorycard1);
        ProductAdmPageController.aRegister.addElement(memorycard2);
        Memorycard.addAll(memorycard1, memorycard2);

        Product harddrive1 = new Product("128 GB SSD", "Egnet til pcer for vanlig bruk.", 5, 299, "Harddisk");
        Product harddrive2 = new Product("256 GB SSD", "Dette er et minnekort med rask lagring.", 7, 999, "Harddisk");
        ProductAdmPageController.aRegister.addElement(harddrive1);
        ProductAdmPageController.aRegister.addElement(harddrive2);
        Harddrive.addAll(harddrive1, harddrive2);

        Product processor1 = new Product("Intel Core I3", "Eldre versjon av prosessor.", 5, 299, "Prosessor");
        Product processor2 = new Product("Intel Core I5", "Nyere versjon av prosessor.", 5, 499, "Prosessor");
        ProductAdmPageController.aRegister.addElement(processor1);
        ProductAdmPageController.aRegister.addElement(processor2);
        Processor.addAll(processor1, processor2);

        Product Power1 = new Product("Strømforsyning 1", "0.5 meter ledning.", 5, 299, "Strømforsyning");
        Product Power2 = new Product("Strømforsyning 2", "1.5 meter ledning.", 5, 399, "Strømforsyning");
        ProductAdmPageController.aRegister.addElement(Power1);
        ProductAdmPageController.aRegister.addElement(Power2);
        Power.addAll(Power1, Power2);

        Product Soundcard1 = new Product("Lydkort 1", "God lyd.", 5, 299, "Lydkort");
        Product Soundcard2 = new Product("Lydkort 2", "Den beste lyden på markedet.", 7, 799, "Lydkort");
        ProductAdmPageController.aRegister.addElement(Soundcard1);
        ProductAdmPageController.aRegister.addElement(Soundcard2);
        Soundcard.addAll(Soundcard1, Soundcard2);

        Product Opticaldisk1 = new Product("Optisk disk 1", "God opplevelse til daglige gjøremål.", 5, 299, "Optisk disk");
        Product Opticaldisk2 = new Product("Optisk disk 2", "Godt egnet for gaming.", 7, 1429, "Optisk disk");
        ProductAdmPageController.aRegister.addElement(Opticaldisk1);
        ProductAdmPageController.aRegister.addElement(Opticaldisk2);
        OpticalDisk.addAll(Opticaldisk1, Opticaldisk2);

        Product color1 = new Product("Rød", "En fin farge inspirert av rød rose.", 10, 299, "Farge");
        Product color2 = new Product("Svart som olje", "En tøff oljesvart farge med glans.", 10, 499, "Farge");
        ProductAdmPageController.aRegister.addElement(color1);
        ProductAdmPageController.aRegister.addElement(color2);
        Color.addAll(color1, color2);


        // disse andre produktene skal knyttes mot tableview på "ExtraOrderEnduserPage"
        Product otherProducts1 = new Product("Tastatur 1", "Dette er vårt billigste tastatur.", 3, 179, "Andre produkter", "./src/Datamaskin/images/keyboard1.jpg");
        Product otherProducts2 = new Product("Tastatur 2", "Dette er vårt dyreste tastatur.", 3, 779, "Andre produkter", "./src/Datamaskin/images/keyboard2.jpg");
        Product otherProducts3 = new Product("Ekstern skjerm 1", "Stor skjerm så man ikke får vondt i øynene.", 3, 1179, "Andre produkter");
        Product otherProducts4 = new Product("Mus 1", "Fin for hånda. Bra passform.", 3, 579, "Andre produkter","./src/Datamaskin/images/mus1.jpg");
        Product otherProducts5 = new Product("Headset 1", "Nyeste på markedet, god kvalitet.", 10,  100, "Andre produkter", "./src/Datamaskin/images/headset1.jpg");
        Product otherProducts6 = new Product("Headset 2", "Svært god lydkvalitet.", 5,  300, "Andre produkter", "./src/Datamaskin/images/headset2.jpg");
        Product otherProducts7 = new Product("Webkamera 1", "Nå med nattmodus!", 2,299, "Andre produkter", "./src/Datamaskin/images/webkamera1.jpg");
        Product otherProducts8 = new Product("Webkamera 2", "Større kamera med vidvinkel.", 2,299, "Andre produkter", "./src/Datamaskin/images/webkamera2.jpg");
        Product otherProducts9  = new Product("Mus 2", "Passer til alle pcer.",5 ,499, "Andre produkter", "./src/Datamaskin/images/mus2.jpg");
        Product otherProducts10 = new Product("Mus 3", "Godt egnet for gaming.",5 ,499, "Andre produkter");
        ProductAdmPageController.aRegister.addElement(otherProducts1);
        ProductAdmPageController.aRegister.addElement(otherProducts2);
        ProductAdmPageController.aRegister.addElement(otherProducts3);
        ProductAdmPageController.aRegister.addElement(otherProducts4);
        ProductAdmPageController.aRegister.addElement(otherProducts5);
        ProductAdmPageController.aRegister.addElement(otherProducts6);
        ProductAdmPageController.aRegister.addElement(otherProducts7);
        ProductAdmPageController.aRegister.addElement(otherProducts8);
        ProductAdmPageController.aRegister.addElement(otherProducts9);
        ProductAdmPageController.aRegister.addElement(otherProducts10);
        OtherProducts.addAll(otherProducts1, otherProducts2, otherProducts3, otherProducts4, otherProducts5,
                otherProducts7,otherProducts8, otherProducts9, otherProducts10);
    }
}