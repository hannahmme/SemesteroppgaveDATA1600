package datamaskin.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProductRegister implements Serializable {
    private static final long serialVersionUID = 1;

    static transient ObservableList<Product> Register = FXCollections.observableArrayList();

    public List<Product> getRegister(){ return Register;}

    public void addElement(Product aProduct) {
        Register.add(aProduct);
    }

    public void removeAll() {
        Register.clear();
    }

    public static void setComponentToTV(TableView<Product> tv) {
        tv.setItems(Register);
    }

    // todo: kan denne slettes?
    public Product searchRegisterByName(String name) {
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);

        for(Product p : Register) {
            Matcher matcher = pattern.matcher(p.getName());

            if(matcher.find()) {
                return p;
            }
        }

        // Hvis vi kommer hit betyr det at vi ikke fant noen personer
        return null;
    }

    //Metoder for filtrering innenfor de ulike kolonnene
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Product p : Register) {
            sb.append(p.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(Register));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Product> list = (List<Product>) s.readObject();
        Register = FXCollections.observableArrayList();
        Register.addAll(list);
    }


}
