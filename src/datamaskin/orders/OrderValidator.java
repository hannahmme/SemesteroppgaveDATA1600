package datamaskin.orders;

import datamaskin.filbehandling.ReadFromAllOrdersFile;
import datamaskin.filbehandling.ReadFromAnOrderFile;
import datamaskin.filbehandling.ReadFromCustomerFile;
import datamaskin.product.Product;
import datamaskin.users.Customer;
import datamaskin.users.CustomerValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.Calendar;

public class OrderValidator {
    private static final ReadFromAllOrdersFile readFromAllOrdersFile = new ReadFromAllOrdersFile();

    private static boolean checkDuplicate(ObservableList<FinalOrderOverview> validOrdersList, FinalOrderOverview anOrder) {
        for (FinalOrderOverview anotherO : validOrdersList) {
            if (anOrder.getOrderID().equals(anotherO.getOrderID())) {
                System.err.println("Duplikat: Det finnes to ordreIDer som er identiske i csv-filen");
                return true;
            }
        }
        return false;
    }

    public static boolean validateOrderID(String orderID) {
        if (orderID.matches("ordre-" + "[0-9]{1,5}")) {
            return true;
        }
        return false;
    }

    public static boolean validateDate(String date) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        // den tror at vi er i feil måned - må legge til en måned for å få riktig
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        if (date.matches("[0-9]{4,}" + "-" + "[0-1]+" + "[0-9]+" + "-" + "[0-9]{2,}")) {
            String[] split = date.split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int day = Integer.parseInt(split[2]);

            if (year <= currentYear && year > 2009 && day > 0) {
                if (year == currentYear && month > currentMonth) {
                    return false;
                } else if (year == currentYear && month == currentMonth && day > currentDay) {
                    return false;
                } else if (checkDate(year, month, day)) return true;

            }
        }
        return false;
    }

    private static boolean checkDate(int year, int month, int day) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 && day < 32) {
            return true;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11 && day < 31) {
            return true;
        }
        if (month == 2 && day < 30 && year % 4 == 0) {
            return true;
        }
        if (month == 2 && day < 29) {
            return true;
        }
        return false;
    }

    public static boolean validateTotalPrice(double totalprice, double expectedTotalprice) {
        if (totalprice == expectedTotalprice && totalprice > 0) {
            return true;
        }
        return false;
    }

    private static final ReadFromAnOrderFile readFromAnOrderFile = new ReadFromAnOrderFile();
    private static final ReadFromCustomerFile readFromCustomerFile = new ReadFromCustomerFile();

    private static double getExpectedPrice(String orderID) throws IOException {
        ObservableList<Product> specificOrder = readFromAnOrderFile.readFromAnOrderFile("./src/Datamaskin/sentOrdersPath/" + orderID + ".csv");
        double totalprice = 0;

        for (Product aProductLine : specificOrder) {
            totalprice += aProductLine.getPrice();
        }
        return totalprice;
    }

    private static boolean getExpectedEmail(String email) throws IOException {
        ObservableList<Customer> customerReg = readFromCustomerFile.readFromCustomerFile("./src/Datamaskin/users/allCustomers.csv");

        for (Customer aCustomer : customerReg) {
            if (aCustomer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // metode som henter og returnerer en liste med kundene
    public static ObservableList<FinalOrderOverview> getOrderList() throws IOException {
        ObservableList<FinalOrderOverview> validOrdersList = FXCollections.observableArrayList();
        ObservableList<FinalOrderOverview> allOrdersList = FXCollections.observableArrayList();

        try {
            allOrdersList = readFromAllOrdersFile.readFromAllOrdersFile("./src/datamaskin/sentOrdersPath/allOrders.csv");
        } catch (IOException e) {
            System.err.println("Filsti ikke funnet: " + e.getMessage() + ".");
        }


        if (allOrdersList == null) {
            System.err.println("Filen som skal inneholde ordreregisteret er tom.");
        } else {
            for (FinalOrderOverview anOrder : allOrdersList) {
                if (!CustomerValidator.validateEmail(anOrder.getEmail())) {
                    System.err.println("Eposten er i feil format på følgende ordrenr.: " + anOrder.getOrderID());
                } else if (!getExpectedEmail(anOrder.getEmail())) {
                    System.err.println("Eposten fra følgende ordrenr. finnes ikke i kunderegisteret: " + anOrder.getOrderID());
                } else if (!validateOrderID(anOrder.getOrderID())) {
                    System.err.println("OrdreID er i feil format på følgende ordrenr.: " + anOrder.getOrderID());
                } else if (checkDuplicate(validOrdersList, anOrder)) {
                    System.err.println("Duplikat: Det finnes to ordreID-er som er identiske i csv-filen: " + anOrder.getOrderID());
                } else if (!validateDate(anOrder.getOrderDate())) {
                    System.err.println("Dato er i feil format i csv-filen på følgende ordrenr.: " + anOrder.getOrderID());
                } else if (!validateTotalPrice(anOrder.getTotalPrice(), getExpectedPrice(anOrder.getOrderID()))) {
                    System.err.println("Totalprisen i filen stemmer ikke overens med totalprisen av produktene i ordrenr: " + anOrder.getOrderID());
                } else {
                    validOrdersList.add(anOrder);
                }
            }
        }
        return validOrdersList;
    }
}