package datamaskin.fxml;

import datamaskin.filbehandling.ReadFromAllOrdersFile;
import datamaskin.filbehandling.ReadFromOrderFile;
import datamaskin.orders.FinalOrderOverview;
import datamaskin.Page;
import datamaskin.product.Product;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static datamaskin.orders.FinalOrderOverviewRegister.OrderRegister;

public class AllOrdersController implements Initializable {

    @FXML private TableView<FinalOrderOverview> allOrders;
    @FXML private TableColumn<String, FinalOrderOverview> emailColumn;
    @FXML private TableColumn<String, FinalOrderOverview> orderIDColumn;
    @FXML private TableColumn<String, FinalOrderOverview> orderDateColumn;
    @FXML private TableColumn<Double, FinalOrderOverview> totalPriceColumn;
    @FXML private Button toSuperuserpage;
    @FXML private TextField txtFiltering;

    private ReadFromAllOrdersFile readFromAllOrdersFile = new ReadFromAllOrdersFile();
    private ReadFromOrderFile readFromOrderFile = new ReadFromOrderFile();


    @FXML private TableView<Product> tblOrderContent;
    @FXML private TableColumn<String, Product> productName;
    @FXML private TableColumn<String, Product> productInfo;
    @FXML private TableColumn<Integer, Product> productLifetime;
    @FXML private TableColumn<Double, Product> productPrice;

    // kode for filtrering
    @FXML void filteredByInput(KeyEvent event) {
        FilteredList<FinalOrderOverview> filteredData = new FilteredList<>(OrderRegister, p -> true);

        //hver gang verdien endres skjer følgende
        txtFiltering.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(finalOrderOverview -> {

                String smallLetters = newValue.toLowerCase();


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
                    }else if (finalOrderOverview.getTotalPrice() == Double.parseDouble(smallLetters)) {
                        return true;
                    } return false;
                } return  false;
            });
        });

        // oppretter en sortert liste binder den sammen med tabellen
        SortedList<FinalOrderOverview> sortertData = new SortedList<>(filteredData);
        sortertData.comparatorProperty().bind(allOrders.comparatorProperty());

        // legger til sotrert og filtert data til tabellen
        allOrders.setItems(sortertData);
    }


    //knappen "tilbake" tar brukeren med tilbake til menysiden for superbruker
    @FXML void toSuperuserpage() throws IOException {
        Stage primaryStage = (Stage) toSuperuserpage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
        Page.toSuperuserpage(primaryStage, root);
    }

    @FXML void btnSuperUserPageEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toSuperuserpage();
        }
    }

    // metoder for å legge inn ordreregisteret på denne siden
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<FinalOrderOverview> allOrdersList = readFromAllOrdersFile.readFromAllOrdersFile("./src/Datamaskin/sentOrdersPath/allOrders.csv");
            allOrders.getItems().addAll(allOrdersList);
            allOrders.setItems(allOrdersList);
        } catch (IOException e) {
            System.out.println("Filsti ikke funnet: " + e.getMessage());
        }

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
    }


    //metode som gjør det mulig for admin å trykke på en ordre og se hva den inneholder
    @FXML
    void selectedItemEvent(MouseEvent event) throws IOException {
        FinalOrderOverview finalOrder = allOrders.getSelectionModel().getSelectedItem();
        String orderID = finalOrder.getOrderID();
        String orderIdPath = "./src/Datamaskin/sentOrdersPath/" + orderID + ".csv";

        try{
            ObservableList<Product> listOfProducts = readFromOrderFile.readFromOrderFile(orderIdPath);
            tblOrderContent.getItems().addAll(listOfProducts);
            tblOrderContent.setItems(listOfProducts);

        } catch (IOException e){
            System.out.println("Noe gikk galt ved innlasting av filstien: " + e.getMessage());
        }


        productName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productInfo.setCellValueFactory(new PropertyValueFactory<>("Description"));
        productLifetime.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

    }

}
