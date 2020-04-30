package datamaskin.fxml;

import datamaskin.filbehandling.ReadFromAllOrdersFile;
import datamaskin.orders.FinalOrderOverview;
import datamaskin.Page;
import datamaskin.orders.Order;
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
import javafx.scene.input.MouseEvent;
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
    @FXML ChoiceBox<String> filterCBox;
    @FXML private ThreadReader readerTask;

    private ReadFromAllOrdersFile readFromAllOrdersFile = new ReadFromAllOrdersFile();
    private ObservableList<Product> emptyList = FXCollections.observableArrayList();

    @FXML private TableView<Product> tblOrderContent;
    @FXML private TableColumn<String, Product> productName;
    @FXML private TableColumn<String, Product> productInfo;
    @FXML private TableColumn<Integer, Product> productLifetime;
    @FXML private TableColumn<Double, Product> productPrice;

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

        ObservableList<String> filterChoices = FXCollections.observableArrayList();
        filterChoices.addAll("Email", "OrdreID", "Dato", "Totalpris");

        filterCBox.setItems(filterChoices);
        filterCBox.setValue("Email");

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
    }

    //metode som gjør det mulig for admin å trykke på en ordre og se hva den inneholder
    //Lesing fra fil gjennomføres i en egen tråd, og setter tableViewet disablet imens det leser
    @FXML
    void selectedItemEvent(MouseEvent event) throws IOException {
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
        tblOrderContent.setItems(emptyList);
        allOrders.setDisable(false);
    }


    @FXML
    private void filterChoiceChanged() throws IOException {
        filter();
    }

    @FXML
    private void searchTxtEntered() throws IOException {
        filter();
    }


    private void filter() throws IOException {
        // henter listen over alle ordrene fra fil
        ObservableList<FinalOrderOverview> allOrdersList = readFromAllOrdersFile.readFromAllOrdersFile("./src/Datamaskin/sentOrdersPath/allOrders.csv");

        // oppretter en ny liste for filtrert data med alle ordrene fra fil
        FilteredList<FinalOrderOverview> filtrertData = new FilteredList<>((allOrdersList), p -> true);

        // hver gang verdien endres skjer følgende
        txtFiltering.textProperty().addListener((observable, oldVerdi, newVerdi) -> {

            // listen med filtrert data sjekker gjennom allOrdersList om den finner verdiene av order
            filtrertData.setPredicate(anOrder -> {

                // henter den nye verdien og gjør den om til små bokstaver
                String smallLetters = newVerdi.toLowerCase();

                if (newVerdi.matches("[a-zA-Z. -_0-9()@]*")) {    //

                    // Hvis feltet er tomt skal alle personer vises
                    if (newVerdi.isEmpty()) {
                        return true;
                    }

                    // Sammenligner alle kolonner med filtertekst, etter valgt cbox
                    if(filterCBox.getValue().toLowerCase().equals("email")) {
                        if (anOrder.getEmail().toLowerCase().contains(smallLetters)) {
                            return true;
                        }
                    }
                    if(filterCBox.getValue().toLowerCase().equals("ordreid")) {
                        if (anOrder.getOrderID().toLowerCase().contains(smallLetters)) {
                            return true;
                        }
                    }
                    if(filterCBox.getValue().toLowerCase().equals("dato")) {
                        if (anOrder.getOrderDate().contains(smallLetters)) {
                            return true;
                        }
                    }
                    if(filterCBox.getValue().toLowerCase().equals("totalpris")){
                            if (String.valueOf(anOrder.getTotalPrice()).matches(smallLetters)) {
                                return true;
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
