package Datamaskin.Order;

public class OrderExample {

    public static void setExampleData(){
        FinalOrder finalOrder1 = new FinalOrder("011", "bruker@bruker.no", 5049);
        FinalOrder finalOrder2 = new FinalOrder("022", "bruker@bruker.no", 2049);
        FinalOrder finalOrder3 = new FinalOrder("033", "bruker@bruker.no", 3009);
        FinalOrder finalOrder4 = new FinalOrder("007", "eksempel@eksempel.no", 1049);

        FinalOrderRegister.OrdreRegister.addAll(finalOrder1, finalOrder2, finalOrder3, finalOrder4);

    }






}
