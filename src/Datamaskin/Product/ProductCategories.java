package Datamaskin.Product;

import Datamaskin.FXML.ProductAdmPageController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductCategories {
    String componentName;
    String componentDescription;
    int componentLifetime;
    double componentPrice;

    public void ProductCategories (String componentName, String componentDescription, int componentLifetime, double componentPrice){
        this.componentName = componentName;
        this.componentDescription = componentDescription;
        this.componentLifetime = componentLifetime;
        this.componentPrice = componentPrice;
    }

    // alle de forskjellige kategoriene et produkt kan være innenfor
    public static HashMap<String, Product> GraphicCard = new HashMap<>(); // Skjermkort
    public static HashMap<String, Product> Memorycard = new HashMap<>(); // Minnekort
    public static HashMap<String, Product> Harddrive = new HashMap<>(); // Harddisk
    public static HashMap<String, Product> Processor = new HashMap<>(); // Prosessor
    public static HashMap<String, Product> Power = new HashMap<>();     // Strømforsyning
    public static HashMap<String, Product> Soundcard = new HashMap<>(); // Lydkort
    public static HashMap<String, Product> OpticalDisk = new HashMap<>(); // Optisk disk
    public static HashMap<String, Product> Color = new HashMap<>();     // Farge

    // har ikke denne i hashmap, fordi hashmap har en kapasitet på 16 plasser i listen.
    public static ArrayList<Product> otherProducts = new ArrayList<>(); // Andre produkter som mus, tastatur, skjerm osv


    // vurder å bruke switch case for å vise at vi kan det også
    public static void setData(Product etProduct, String category, String navn){
        if(category.equals("Skjermkort")) {
            GraphicCard.put(navn, etProduct);
        }
        else if (category.equals("Minnekort")){
            Memorycard.put(navn, etProduct);
        }
        else if (category.equals("Harddisk")){
            Harddrive.put(navn, etProduct);
        }
        else if (category.equals("Prosessor")){
            Processor.put(navn, etProduct);
        }
        else if (category.equals("Strømforsyning")){
            Power.put(navn, etProduct);
        }
        else if (category.equals("Lydkort")){
            Soundcard.put(navn, etProduct);
        }
        else if (category.equals("Optisk disk")){
            OpticalDisk.put(navn, etProduct);
        }
        else if (category.equals("Farge")){
            Color.put(navn, etProduct);
        }
        else if (category.equals("Andre producter")){
            otherProducts.add(etProduct);
        }
    }

    // eksempeldata, så slipper vi å lage nye produkter hver gang vi kjører progr. Bør ha minst x2 produkter innenfor hver kategori
    // denne må bindes opp med brukersiden på "EnduserPage".
    public static void setExampleData(){
        Product graphiccard1 = new Product("Skjermkort 1", "Dette er vårt billigste skjermkort", 10, 299, "Skjermkort");
        Product graphiccard2 = new Product("Skjermkort 2", "Dette er vårt nest billigste skjermkort", 12, 399, "Skjermkort");
        ProductAdmPageController.aRegister.addElement(graphiccard1);
        ProductAdmPageController.aRegister.addElement(graphiccard2);
        GraphicCard.put("Skjermkort 1", graphiccard1);
        GraphicCard.put("Skjermkort 2", graphiccard2);

        Product memorycard1 = new Product("Minnekort 1", "Dette er vårt billigste minnekort", 5, 299, "Minnekort");
        Product memorycard2 = new Product("Minnekort 2", "Dette er vårt nest billigste skjermkort", 10, 699, "Minnekort");
        ProductAdmPageController.aRegister.addElement(memorycard1);
        ProductAdmPageController.aRegister.addElement(memorycard2);
        Memorycard.put("Minnekort 1", memorycard1);
        Memorycard.put("Minnekort 2", memorycard2);

        Product harddrive1 = new Product("Harddisk 1", "blabla", 5, 299, "Harddisk");
        Product harddrive2 = new Product("Harddisk 2", "Dette er ikke vårt billigste minnekort", 5, 999, "Harddisk");
        ProductAdmPageController.aRegister.addElement(harddrive1);
        ProductAdmPageController.aRegister.addElement(harddrive2);
        Harddrive.put("Harddisk 1", harddrive1);
        Harddrive.put("Harddisk 2", harddrive2);

        Product processor1 = new Product("Proessor 1", "hei", 5, 299, "Prosessor");
        Product processor2 = new Product("Proessor 2", "hei", 5, 99, "Prosessor");
        ProductAdmPageController.aRegister.addElement(processor1);
        ProductAdmPageController.aRegister.addElement(processor2);
        Processor.put("Proessor 1", processor1);
        Processor.put("Proessor 2", processor2);

        Product Power1 = new Product("Strømforsyning 1", "noe", 5, 299, "Strømforsyning");
        Product Power2 = new Product("Strømforsyning 2", "noe", 5, 399, "Strømforsyning");
        ProductAdmPageController.aRegister.addElement(Power1);
        ProductAdmPageController.aRegister.addElement(Power2);
        Power.put("Strømforsyning 1", Power1);
        Power.put("Strømforsyning 2", Power2);

        Product Soundcard1 = new Product("Lydkort 1", "Dette er verre", 5, 299, "Lydkort");
        Product Soundcard2 = new Product("Lydkort 2", "Dette er bedre", 7, 799, "Lydkort");
        ProductAdmPageController.aRegister.addElement(Soundcard1);
        ProductAdmPageController.aRegister.addElement(Soundcard2);
        Soundcard.put("Lydkort 1", Soundcard1);
        Soundcard.put("Lydkort 2", Soundcard2);

        Product opticaldisk1 = new Product("Optisk disk 1", "ikke et minnekort", 5, 299, "Optisk disk");
        Product opticaldisk2 = new Product("Optisk disk 2", "ikke et minnekort 2", 5, 429, "Optisk disk");
        ProductAdmPageController.aRegister.addElement(opticaldisk1);
        ProductAdmPageController.aRegister.addElement(opticaldisk2);
        OpticalDisk.put("Optisk disk 1", opticaldisk1);
        OpticalDisk.put("Optisk disk 2", opticaldisk2);

        Product color1 = new Product("Vaniljerød", "En fin blanding av rød rose og hvit vanilje", 10, 299, "Farge");
        Product color2 = new Product("Svart som olje", "En tøff oljesvart farge med glans.", 10, 499, "Farge");
        ProductAdmPageController.aRegister.addElement(color1);
        ProductAdmPageController.aRegister.addElement(color2);
        Color.put("Vaniljerød", color1);
        Color.put("Svart som olje", color2);


        // disse andre produktene skal knyttes mot tableview på "ExtraOrderEnduserPage"
        Product otherProducts1 = new Product("Tastatur 1", "Dette er vårt billigste tastatur", 3, 179, "Andre produkter");
        Product otherProducts2 = new Product("Tastatur 2", "Dette er vårt dyreste tastatur", 3, 779, "Andre produkter");
        Product otherProducts3 = new Product("Ekstern skjerm 1", "bra skjerm så man ikke får vondt i øynene", 3, 1179, "Andre produkter");
        Product otherProducts4 = new Product("Mus 1", "Fin for hånda. Bra passform", 3, 579, "Andre produkter");
        ProductAdmPageController.aRegister.addElement(otherProducts1);
        ProductAdmPageController.aRegister.addElement(otherProducts2);
        ProductAdmPageController.aRegister.addElement(otherProducts3);
        ProductAdmPageController.aRegister.addElement(otherProducts4);
        otherProducts.add(otherProducts1);
        otherProducts.add(otherProducts2);
        otherProducts.add(otherProducts3);
        otherProducts.add(otherProducts4);
    }


    // bør lage en to string metode så man får skrevet ut år etter levetid og kroner etter prisMap.Entry<String, Product> entry



    //metode som gjør at navnet fra hashmappen kommer opp som et option i choicebox, og ikke hele objektet
    public static String CategorynameToString(HashMap<String, Product> aProduct, int index){
        return aProduct.keySet().toArray()[index].toString();

    }

}
