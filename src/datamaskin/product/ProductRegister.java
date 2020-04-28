package datamaskin.product;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ProductRegister implements Serializable {
    private static final long serialVersionUID = 1;

    public transient static ObservableList<Product> Register = FXCollections.observableArrayList();

    public static void setComponentToTV(TableView tv) {
        tv.setItems(Register);
    }

    public ObservableList<Product> filterByName(String name) {
        return Register.stream().
                filter(p -> p.getName().toLowerCase().
                        matches(String.format("%s%s%s",".*", name.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Product> filterByDescription(String description) {
        return Register.stream().
                filter(p -> p.getDescription().toLowerCase().
                        matches(String.format("%s%s%s",".*", description.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Product> filterByLifetime(int lifetime) {
        return Register.stream().
                filter(p -> p.getLifetime() == lifetime).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Product> filterByPrice(double price) {
        return Register.stream().
                filter(p -> p.getPrice() == price).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Product> filterByCategory(String category) {
        return Register.stream().
                filter(p -> p.getCategory().toLowerCase().
                        matches(String.format("%s%s%s",".*", category.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    //metode som gjør det mulig å slette et produkt i listen - Hannah
    public static void deleteElement(Product itemToDelete){
        if(itemToDelete == null){
            return;
        }
        for(int i = 0; i < Register.size(); i++){
            if (itemToDelete.equals(Register.get(i))) {
                Register.remove(Register.get(i));
            }
        }
    }

    public void addElement(Product aProduct) {
        Register.add(aProduct);
    }

    public static boolean checkIfCategoryIsPresent(String category){
        for(Product aProduct : Register){
            if(aProduct.getCategory().equals(category)){
                return true;
            }
        }
        return false;
    }

    public static boolean allCategoriesArePresent(){
        if(checkIfCategoryIsPresent("Skjermkort") && checkIfCategoryIsPresent("Minnekort")
                && checkIfCategoryIsPresent("Harddisk") && checkIfCategoryIsPresent("Prosessor")
                && checkIfCategoryIsPresent("Strømforsyning") && checkIfCategoryIsPresent("Lydkort")
                && checkIfCategoryIsPresent("Optisk disk") && checkIfCategoryIsPresent("Farge")){
            return true;
        }
        return false;
    }



}
