package Datamaskin.Order;

import java.sql.Date;
import java.time.LocalDate;

public class OrderExample {
    public static void setExampleData(){
        FinalOrder finalOrder1 = new FinalOrder("#011", "bruker@bruker.no", Date.valueOf(LocalDate.now()), 5049);
        FinalOrder finalOrder2 = new FinalOrder("#022", "bruker@bruker.no", Date.valueOf(LocalDate.now()),2049);
        FinalOrder finalOrder3 = new FinalOrder("#033", "bruker@bruker.no", Date.valueOf(LocalDate.now()),3009);
        FinalOrder finalOrder4 = new FinalOrder("#007", "eksempel@eksempel.no", Date.valueOf(LocalDate.now()),1049);
        FinalOrder finalOrder5 = new FinalOrder("#008", "eksempel@eksempel.no", Date.valueOf(LocalDate.now()),6049);
        FinalOrder finalOrder6 = new FinalOrder("#009", "eksempel@eksempel.no", Date.valueOf(LocalDate.now()),14398);

        FinalOrderRegister.OrdreRegister.addAll(finalOrder1, finalOrder2, finalOrder3, finalOrder4, finalOrder5, finalOrder6);
    }
}
