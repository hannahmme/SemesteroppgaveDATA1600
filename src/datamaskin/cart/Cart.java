package datamaskin.cart;

import datamaskin.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class Cart {
    public transient static ObservableList<Product> Cart = FXCollections.observableArrayList();

    public void attachTableview(TableView tv) {
        tv.setItems(Cart);
    }

    public void addElement(Product aProduct){
        Cart.add(aProduct);
    }

    public void deleteOneProductFromCart(Product aProduct){
        Cart.remove(aProduct);
    }

    // Fjerner gammelt element i handlelisten (hører til første brukerside) og legger til nytt
    public void replaceElements(int i, Product newProduct){
        Cart.set(i, newProduct);
    }

    // Returnerer totalsum av varer lagt til i handlekurv + setter prisen til label
    public double getTotalPrice(Label infoLabel) {
        double totalSum = 0;
        for (Product product : Cart) {
            totalSum += product.getPrice();
        }
        infoLabel.setText(String.valueOf(totalSum) + "0 kr");
        return totalSum;
    }

    // Sletter alle elementer i handlelisten (brukes når bruker skal tilbake til hovedsiden)
    public void deleteShoppingcart(){
        if(Cart.size() != 0){
            Cart.remove(0, Cart.size());
        }
    }

    // Finner indeksen til produktet i produktkategorien som er input
    public static int findIndex(String category){
        int index = 0;
        for(Product aProduct: Cart){
            if(aProduct.getCategory().equals(category)){
                index = Cart.indexOf(aProduct);
            }
        }
        return index;
    }

    // Henter frem riktig produkt fra listen og legger til produktet i handlekurven
    public static Product addProduct(String productname, ObservableList<Product> aCategorylist) {
        Product aProduct = null;
        for (int i = 0; i < aCategorylist.size(); i++) {
            if (productname.equals(aCategorylist.get(i).getName())) {
                aProduct = aCategorylist.get(i);
            }
        }
        return aProduct;
    }

    // Sette combobox til riktig verdi utifra det brukeren allerede har valgt
    public static Product setAllChosenComboboxes(String category) {
        for(Product aProduct : Cart){
            if(aProduct.getCategory().equals(category)){
                return aProduct;
            }
        }
        return null;
    }

    // Setter verdier til hver cBox
    public static void setChosenCombobox(ComboBox<Product> cBoxGraphicCard,ComboBox<Product> cBoxMemorycard,ComboBox<Product> cBoxHarddrive,
                                   ComboBox<Product> cBoxProcessor, ComboBox<Product> cBoxPower, ComboBox<Product> cBoxSoundcard,
                                   ComboBox<Product> cBoxOpticaldisk, ComboBox<Product> cBoxColor) {
        cBoxGraphicCard.setValue(setAllChosenComboboxes("Skjermkort"));
        cBoxMemorycard.setValue(setAllChosenComboboxes("Minnekort"));
        cBoxHarddrive.setValue(setAllChosenComboboxes("Harddisk"));
        cBoxProcessor.setValue(setAllChosenComboboxes("Prosessor"));
        cBoxPower.setValue(setAllChosenComboboxes("Strømforsyning"));
        cBoxSoundcard.setValue(setAllChosenComboboxes("Lydkort"));
        cBoxOpticaldisk.setValue(setAllChosenComboboxes("Optisk disk"));
        cBoxColor.setValue(setAllChosenComboboxes("Farge"));
    }

    // Setter infotekst til label
    public static void formatComboBoxDexcription (ComboBox<Product> aCBox, Label aLbl){
        aCBox.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null)
                aLbl.setText(newval.getDescription()+" Levetid: " + newval.getLifetime() + " år.");
        });
    }

    // Finner levetiden til produktene og returnerer et gjennomsnitt
    public static double findExpectedLifetime(){
        double lifetime = 0;
        double expectedLifetime;
        double count = 0;
        for(Product aProduct : Cart) {
            count ++;
            lifetime += aProduct.getLifetime();
        }
        expectedLifetime = lifetime / count;
        return expectedLifetime;
    }
}