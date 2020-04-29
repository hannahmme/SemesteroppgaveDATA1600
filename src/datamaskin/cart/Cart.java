package datamaskin.cart;
import datamaskin.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

public class Cart {
    public transient static ObservableList<Product> Register = FXCollections.observableArrayList();

    public void attachTableview(TableView tv) {
        tv.setItems(Register);
    }

    public void addElement(Product aProduct){
            Register.add(aProduct);
    }

    public void deleteOneProductFromCart(Product aProduct){
        Register.remove(aProduct);
    }

    //Metode som fjerner gammelt element i handlelisten (hører til første brukerside) og legger til nytt
    public void replaceElements(int i, Product newProduct){
            Register.set(i, newProduct);
    }

    // meotde for å hente ut verdier fra pris-kolonnen og legge de sammen, for så å sette verdien til lbl
    //Metode som returnerer totalsum av varer lagt til i handlekurv - Hannah
    public double getTotalPrice(Label infoLabel) {
        double totalSum = 0;
        for (Product product : Register) {
            totalSum += product.getPrice();
        }
        infoLabel.setText(String.valueOf(totalSum));
        return totalSum;
    }


    //Metode som sletter alle elementer i handlelisten (brukes når bruker skal tilbake til hovedsiden) - Hannah
    public void deleteShoppingcart(){
        if(Register.size() != 0){
            Register.remove(0, Register.size());
        }
    }

    // finner indeksen til produktet i produktkategorien som er input
    public static int findIndex(String category){
        int index = 0;
        for(Product aProduct: Cart.Register){
            if(aProduct.getCategory().equals(category)){
                index = Cart.Register.indexOf(aProduct);
            }
        }
        return index;
    }

    // metode for å hente frem riktig produkt fra listen og legge til produktet i handlekurven
    public static Product addProduct(String productname, ObservableList<Product> aCategorylist) {
        Product aProduct = null;
        for (int i = 0; i < aCategorylist.size(); i++) {
            if (productname.equals(aCategorylist.get(i).getName())) {
                aProduct = aCategorylist.get(i);
            }
        }
        return aProduct;
    }

    // metoder for å sette choicebox til riktig verdi utifra det brukeren allerede har valgt
    public static Product setAllChosenComboboxes(String category) {
        for(Product aProduct : Register){
            if(aProduct.getCategory().equals(category)){
                return aProduct;
            }
        }
        return null;
    }

    // metode som gjør at combobox kun viser navnet på produktet
    public static void formatComboBoxDisplay(ComboBox<Product> aCBox){
        aCBox.setConverter(new StringConverter<Product>() {

            @Override
            public String toString(Product object) {
                return object.getName();
            }

            @Override
            public Product fromString(String string) {
                return aCBox.getItems().stream().filter(aProduct ->
                        aProduct.getName().equals(string)).findFirst().orElse(null);
            }
        });

    }

    // metode som setter infotekst til label
    public static void formatComboBoxDexcription (ComboBox<Product> aCBox, Label aLbl){
        aCBox.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null)
                aLbl.setText(newval.getDescription());
        });
    }

    public static double findExpectedLifetime(){
        double lifetime = 0;
        double expectedLifetime;
        double count = 0;
        for(Product aProduct : Register) {
            count ++;
            lifetime += aProduct.getLifetime();

        }
        expectedLifetime = lifetime / count;
        return expectedLifetime;
    }

}