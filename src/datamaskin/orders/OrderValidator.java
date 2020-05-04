package datamaskin.orders;

import datamaskin.filbehandling.ReadFromAllOrdersFile;
import datamaskin.users.CustomerValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class OrderValidator {
    private static final ReadFromAllOrdersFile readFromAllOrdersFile = new ReadFromAllOrdersFile();

    public static boolean checkDuplicate(ObservableList<FinalOrderOverview> validOrdersList, FinalOrderOverview anOrder){
        for(FinalOrderOverview anotherO : validOrdersList){
            if(anOrder.getOrderID().equals(anotherO.getOrderID())) {
                System.out.println("Duplikat: Det finnes to ordreIDer som er identiske i csv-filen");
                return true;
            }
        }
        return false;
    }

    public static boolean validateOrderID(String orderID){
        if(orderID.matches("ordre-" + "[0-9]{1,5}")){
            return true;
        }
        return false;
    }

    public static boolean validateDate (String date){
        if(date.matches("[0-9]{4,}"+"-"+"[0-1]+"+"[0-9]+"+"-"+"[0-9]{2,}")){
            String[] split = date.split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int day = Integer.parseInt(split[2]);

            if(year<2021 && year>2010 && month>0 && month<13 && day>0 && day<32){
                return true;
            }
        }
        return false;
    }

    public static boolean validateTotalPrice(double totalprice, double expectedTotalprice){
        if(totalprice>expectedTotalprice){
            return true;
        }
        return false;
    }

    // metode som henter og returnerer en liste med kundene
    public static ObservableList<FinalOrderOverview> getOrderList() throws IOException {
        try {
            ObservableList<FinalOrderOverview> allOrdersList = readFromAllOrdersFile.readFromAllOrdersFile("./src/Datamaskin/sentOrdersPath/allOrders.csv");
            ObservableList<FinalOrderOverview> validOrdersList = FXCollections.observableArrayList();

            for(FinalOrderOverview anOrder: allOrdersList){
                if(!CustomerValidator.validateEmail(anOrder.getEmail())) {
                    System.out.println("Eposten er i feil format på følgende ordrenr.: " + anOrder.getOrderID());
                } else if (!validateOrderID(anOrder.getOrderID())){
                    System.out.println("OrdreID er i feil format på følgende ordrenr.: " + anOrder.getOrderID());
                } else if (checkDuplicate(validOrdersList, anOrder)) {
                    System.out.println("Duplikat: Det finnes to ordreID-er som er identiske i csv-filen: " + anOrder.getOrderID());
                } else if (!validateDate(anOrder.getOrderDate())){
                    System.out.println("Dato er i feil format i csv-filen på følgende ordrenr.: " + anOrder.getOrderID());
                } else if(!validateTotalPrice(anOrder.getTotalPrice(), 2392.0)){
                    System.out.println("Totalprisen i filen stemmer ikke overens med totalprisen av produktene i ordrenr: " + anOrder.getOrderID());
                } else {
                    validOrdersList.add(anOrder);
                }
            }
            return validOrdersList;
        } catch (IOException e){
            System.out.println("Filsti ikke funnet: " + e.getMessage() + ". Se igjennom allOrders.csv-filen.");
        }
        return null;
    }

}