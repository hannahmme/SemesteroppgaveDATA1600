package Datamaskin.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ProductRegister {
    public transient static ObservableList<Product> Register = FXCollections.observableArrayList();

    public void setComponentToTV(TableView tv) {
        tv.setItems(Register);
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
}
