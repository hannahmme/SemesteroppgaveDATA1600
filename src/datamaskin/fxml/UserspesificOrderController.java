package datamaskin.fxml;

import datamaskin.filbehandling.ReadFromOrderFile;
import datamaskin.orders.FinalOrderCustomerOverviewRegister;
import datamaskin.orders.Order;
import datamaskin.product.Product;
import datamaskin.orders.FinalOrderOverview;
import datamaskin.Page;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import static datamaskin.fxml.MainpageController.sortingKey;

public class UserspesificOrderController implements Initializable {
    @FXML private Button toMainpage;
    @FXML private TableView<FinalOrderOverview> tblAllOrders;
    @FXML private TableColumn<FinalOrderOverview, String> emailColumn;
    @FXML private TableColumn<FinalOrderOverview, String> orderIDColumn;
    @FXML private TableColumn<FinalOrderOverview, String> orderDateColumn;
    @FXML private TableColumn<FinalOrderOverview, Double> totalPriceColumn;

    @FXML private TableView<Product> tblOrderInfo;
    @FXML private TableColumn<Product, String> productName;
    @FXML private TableColumn<Product, String> productInfo;
    @FXML private TableColumn<Product, Integer> productLifetime;
    @FXML private TableColumn<Product, Double> productPrice;
    @FXML private TextField txtFilter;

    private ReadFromOrderFile reader = new ReadFromOrderFile();

    //Metode som viser innholdet i orderen når bruker trykker på en ordre
    @FXML void selectedOrderItemEvent(MouseEvent event) throws IOException {
        FinalOrderOverview order = tblAllOrders.getSelectionModel().getSelectedItem();

        if (order == null) return;
        try {
            String path = "./src/Datamaskin/sentOrdersPath/" + order.getOrderID() + ".csv";
            ObservableList<Product> listOfProducts = reader.readFromOrderFile(path);
            tblOrderInfo.getItems().addAll(listOfProducts);
            tblOrderInfo.setItems(listOfProducts);
            productName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            productInfo.setCellValueFactory(new PropertyValueFactory<>("Description"));
            productLifetime.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
            productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        }catch(FileNotFoundException e){
            System.err.println("Noe gikk galt ved innlasting av filstien" + e.getMessage());
        }
    }

    // todo: filtrering etter beløp fungerer ikke
    @FXML void filterData(KeyEvent event) {
        FilteredList<FinalOrderOverview> filteredData = new FilteredList<>(FinalOrderCustomerOverviewRegister.OrderRegister, p -> true);

        //hver gang verdien endres skjer følgende
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(finalOrderOverview -> {

                String smallLetters = newValue.toLowerCase();
                /*if(finalOrderOverview.getEmail().equals(MainpageController.sortingKey)){

                }*/
                if (newValue.matches("[a-zA-Z. -_0-9()@]*")) {    //
                    // Hvis feltet er tomt skal alle personer vises
                    if (newValue.isEmpty()) {
                        return true;
                    }

                    // Sammenligner alle kolonner med filtertekst
                    if (finalOrderOverview.getEmail().toLowerCase().contains(smallLetters)) {
                        return true;
                    } else if (finalOrderOverview.getOrderID().toLowerCase().contains(smallLetters)) {
                        return true;
                    } else if (String.valueOf(finalOrderOverview.getOrderDate()).matches(smallLetters)) {
                        return true;
                        // todo: sortere etter totalpris funker ikke
                    } else if (String.valueOf(finalOrderOverview.getTotalPrice()).matches(smallLetters)) {
                        return true;
                    } return false;
                } return  false;

            });
        });

        // oppretter en sortert liste binder den sammen med tabellen
        SortedList<FinalOrderOverview> sortertData = new SortedList<>(filteredData);
        sortertData.comparatorProperty().bind(tblAllOrders.comparatorProperty());

        // legger til sotrert og filtert data til tabellen
        tblAllOrders.setItems(sortertData);
    }

    // metoder for å legge inn ordreregisteret på denne siden
    @Override public void initialize(URL url, ResourceBundle rb) {
        Order.deleteCustomerInfo();                 // sletter elementer i kunderegisteret for ny bruk
        Order.addCustomerOverviewInfo(sortingKey);  // legger til elementer basert på hva som er sortingKey

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));

        Order.aCustomersOrderRegister.addOrder(tblAllOrders);
    }


    // Knapper som tar brukeren med tilbake til hovedsiden
    @FXML void btnToMainpageEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toMainpage();
        }

    }
    @FXML void toMainpage() throws IOException {
        Stage primaryStage = (Stage) toMainpage.getScene().getWindow();
        Page.toMainpage(primaryStage, FXMLLoader.load(getClass().getResource("Mainpage.fxml")));
    }
}

