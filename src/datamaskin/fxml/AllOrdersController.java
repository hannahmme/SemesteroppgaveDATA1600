package datamaskin.fxml;

import datamaskin.filbehandling.ReadFromAllOrdersFile;
import datamaskin.orders.FinalOrderOverview;
import datamaskin.Page;
import datamaskin.orders.OrderValidator;
import datamaskin.product.Product;
import datamaskin.threadprogramming.ThreadReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllOrdersController implements Initializable {

    @FXML private TableView<FinalOrderOverview> allOrders;
    @FXML private TableColumn<String, FinalOrderOverview> emailColumn;
    @FXML private TableColumn<String, FinalOrderOverview> orderIDColumn;
    @FXML private TableColumn<String, FinalOrderOverview> orderDateColumn;
    @FXML private TableColumn<Double, FinalOrderOverview> totalPriceColumn;
    @FXML private Button toSuperuserpage;
    @FXML private TextField txtFiltering;
    @FXML private Text txtTblHeader;
    @FXML private ComboBox<String> filterCBox;
    @FXML private ThreadReader readerTask;

    @FXML private TableView<Product> tblOrderContent;
    @FXML private TableColumn<String, Product> productName;
    @FXML private TableColumn<String, Product> productInfo;
    @FXML private TableColumn<Integer, Product> productLifetime;
    @FXML private TableColumn<Double, Product> productPrice;

    private final ReadFromAllOrdersFile readFromAllOrdersFile = new ReadFromAllOrdersFile();
    private final ObservableList<Product> emptyList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setTV();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // setter verdier til filterboks
        ObservableList<String> filterChoices = FXCollections.observableArrayList();
        filterChoices.addAll("Email", "OrdreID", "Dato", "Totalpris");
        filterCBox.setItems(filterChoices);
        filterCBox.setValue("Email");
    }

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

    private void setTV() throws IOException {
        ObservableList<FinalOrderOverview> validOrdersList = OrderValidator.getOrderList();
        allOrders.getItems().addAll(validOrdersList);
        allOrders.setItems(validOrdersList);

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
            orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
            orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
            totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
    }

    //metode som gjør det mulig for admin å trykke på en ordre og se hva den inneholder
    //Lesing fra fil gjennomføres i en egen tråd, og setter tableViewet disablet imens det leser
    @FXML void selectedItemEvent() {
        FinalOrderOverview finalOrder = allOrders.getSelectionModel().getSelectedItem();
        if (finalOrder == null) { return; }

        String orderID = finalOrder.getOrderID();
        String orderIdPath = "./src/Datamaskin/sentOrdersPath/" + orderID + ".csv";
        txtTblHeader.setText("Laster inn valgt ordre.....");
        readerTask = new ThreadReader(orderIdPath);
        readerTask.setOnSucceeded(this::threadDoneReading);
        readerTask.setOnFailed(this::threadFailedReading);
        Thread thread = new Thread(readerTask);
        thread.start();
        allOrders.setDisable(true);
        productName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productInfo.setCellValueFactory(new PropertyValueFactory<>("Description"));
        productLifetime.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    //når tråden er ferdig lest, så skjer:
    private void threadDoneReading(WorkerStateEvent event){
        txtTblHeader.setText("Spesifikasjon av ordre");
        ObservableList<Product> listOfProducts = readerTask.getValue();
        allOrders.setDisable(false);
        tblOrderContent.getItems().addAll(listOfProducts);
        tblOrderContent.setItems(listOfProducts);
    }

    //hvis tråden feiler:
    private void threadFailedReading(WorkerStateEvent event){
        txtTblHeader.setText("Det oppsto en feil. Kunne ikke hente ordreinfo.....");
        System.err.println("Feil i filen, se gjennom ordrespesifikasjon");
        tblOrderContent.setItems(emptyList);
        allOrders.setDisable(false);
    }

    @FXML private void searchTxtEntered() throws IOException {
        filter();
    }

    private void filter() throws IOException {
        ObservableList<FinalOrderOverview> allOrdersList = readFromAllOrdersFile.readFromAllOrdersFile("./src/Datamaskin/sentOrdersPath/allOrders.csv");
        FilteredList<FinalOrderOverview> filtrertData = new FilteredList<>((allOrdersList), p -> true);

        // hver gang verdien endres skjer følgende
        txtFiltering.textProperty().addListener((observable, oldValue, newValue) -> {

            // listen med filtrert data sjekker gjennom allOrdersList om den finner verdiene av en ordre
            filtrertData.setPredicate(anOrder -> {

                String smallLetters = newValue.toLowerCase();           // henter den nye verdien og gjør den om til små bokstaver
                if (newValue.matches("[a-zA-Z. -_0-9()@]*")) {    // Sjekker at det matcher regex
                    if (newValue.isEmpty()) {                           // Hvis feltet er tomt skal alle personer vises
                        return true;
                    }

                    // Sammenligner alle kolonner med filtertekst, etter valgt cbox
                    if (filterCBox.getValue().toLowerCase().equals("email")) {
                        if (anOrder.getEmail().endsWith(smallLetters)){             // kan filtrere etter eposter med samme domene
                            if (anOrder.getEmail().toLowerCase().contains(smallLetters)) {
                                return true;}
                        }
                        if (anOrder.getEmail().toLowerCase().contains(smallLetters)) { // filtrer etter eksakt epostadresse
                            return true;}
                    }
                    if (filterCBox.getValue().toLowerCase().equals("ordreid")) {
                        if(!smallLetters.startsWith("ordre-")){
                            if (anOrder.getOrderID().toLowerCase().matches("ordre-" + smallLetters)) {
                                return true;}
                        }
                        if (anOrder.getOrderID().toLowerCase().matches(smallLetters)) {
                            return true;}
                    }
                    if (filterCBox.getValue().toLowerCase().equals("dato")) {
                        if (anOrder.getOrderDate().startsWith(smallLetters)) {
                            return true;}
                    }
                    if (filterCBox.getValue().toLowerCase().equals("totalpris")){
                        if(String.valueOf(anOrder.getTotalPrice()).startsWith(smallLetters)) {
                            if (smallLetters.endsWith(".0")) {
                                if (String.valueOf(anOrder.getTotalPrice()).matches(smallLetters)) {
                                    return true;}
                            } else {
                                if (String.valueOf(anOrder.getTotalPrice()).matches(smallLetters + ".0")) {
                                    return true;}
                            }
                        }
                    }
                }
                return false;
            });
        });

        // oppretter en sortert liste fra filtrert data og binder den sammen med tabellen
        SortedList<FinalOrderOverview> sortertData = new SortedList<>(filtrertData);
        sortertData.comparatorProperty().bind(allOrders.comparatorProperty());

        // legger til sotrert og filtert data til tabellen
        allOrders.setItems(sortertData);
    }
}