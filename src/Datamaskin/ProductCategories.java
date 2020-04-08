package Datamaskin;

import Datamaskin.FXML.ProductAdmPageController;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;

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
    public static ArrayList<Product> GraphicCard = new ArrayList<>();
    public static ArrayList<Product> Memorycard = new ArrayList<>();
    public static ArrayList<Product> Keyboard = new ArrayList<>();
    public static ArrayList<Product> Mouse = new ArrayList<>();
    public static ArrayList<Product> OtherComponents = new ArrayList<>();


    public static void setData(Product etProduct, String category){
        if(category == "GraphicCard") {
            GraphicCard.add(etProduct);
        } else if (category == "Memorycard"){
            Memorycard.add(etProduct);
        } else if (category == "Keyboard"){
            Keyboard.add(etProduct);
        } else if (category == "Mouse"){
            Mouse.add(etProduct);
        } else if (category == "Other Components"){
            OtherComponents.add(etProduct);
        }

        System.out.println(GraphicCard.toString());
        System.out.println(Memorycard.toString());

    }



}
