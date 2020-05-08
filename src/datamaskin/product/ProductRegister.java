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
import static datamaskin.product.ProductCategories.*;

public class ProductRegister implements Serializable {
    private static final long serialVersionUID = 1;

    public static transient ObservableList<Product> ProductRegister = FXCollections.observableArrayList();
    private static ObservableList<Product> emptyList = FXCollections.observableArrayList();

    public void addElement(Product aProduct) {
        ProductRegister.add(aProduct);
    }

    public static void removeAll() {
        ProductRegister.clear();
    }

    public static void setComponentToTV(TableView<Product> tv) {
        tv.setItems(ProductRegister);
    }

    public static void clearTableView(TableView<Product> tableview){
        tableview.setItems(emptyList);
    }

    //metode som gjør det mulig å slette et produkt i registeret
    public static void deleteElement(Product itemToDelete){
        if(itemToDelete == null){
            return;
        }
        for(int i = 0; i < ProductRegister.size(); i++){
            if (itemToDelete.equals(ProductRegister.get(i))) {
                ProductRegister.remove(ProductRegister.get(i));
            }
        }
    }

    private static boolean checkIfCategoryIsPresent(String category){
        for(Product aProduct : ProductRegister){
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
        for(Product p : ProductRegister) {
            sb.append(p.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(ProductRegister));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Product> list = (List<Product>) s.readObject();
        ProductRegister = FXCollections.observableArrayList();
        ProductRegister.addAll(list);
    }
}
