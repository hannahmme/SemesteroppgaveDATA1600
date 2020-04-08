package Datamaskin;

import Datamaskin.FXML.ProductAdmPageController;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.Arrays;

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
    public static ArrayList<Product> Color = new ArrayList<>();


    public static void setData(Product etProduct, String category){
        if(category.equals("Graphic card")) {
            GraphicCard.add(etProduct);
            for (Product x : GraphicCard) {
                System.out.println("Dette er lagt til i kategorien Graphic card: " + x);
            }
        }
        else if (category.equals("Memorycard")){
            Memorycard.add(etProduct);
            for (Product x : Memorycard) {
                System.out.println("Dette er lagt til i kategorien Memorycard: " + x);
            }
        }
        else if (category.equals("Keyboard")){
            Keyboard.add(etProduct);
        }
        else if (category.equals("Mouse")){
            Mouse.add(etProduct);
        }
        else if (category.equals("Other Components")){
            OtherComponents.add(etProduct);
        }
        else if (category.equals("Color")){
            Color.add(etProduct);
        }

    }



}
