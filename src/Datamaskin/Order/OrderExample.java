package Datamaskin.Order;

public class OrderExample {

    public static void setExampleData(){
        Order order1 = new Order("011", "bruker@bruker.no", 5049);
        Order order2 = new Order("022", "bruker@bruker.no", 2049);
        Order order3 = new Order("033", "bruker@bruker.no", 3009);
        Order order4 = new Order("007", "eksempel@eksempel.no", 1049);

        OrderRegister.OrdreRegister.addAll(order1, order2, order3, order4);

    }






}
