package Datamaskin.orders;

import java.sql.Date;
import java.time.LocalDate;

public class OrderExample {
    public static void setExampleData(){
        FinalOrderOverview finalOrderOverview1 = new FinalOrderOverview("#011", "bruker@bruker.no", Date.valueOf(LocalDate.now()), 5049);
        FinalOrderOverview finalOrderOverview2 = new FinalOrderOverview("#022", "bruker@bruker.no", Date.valueOf(LocalDate.now()),2049);
        FinalOrderOverview finalOrderOverview3 = new FinalOrderOverview("#033", "bruker@bruker.no", Date.valueOf(LocalDate.now()),3009);
        FinalOrderOverview finalOrderOverview4 = new FinalOrderOverview("#007", "eksempel@eksempel.no", Date.valueOf(LocalDate.now()),1049);
        FinalOrderOverview finalOrderOverview5 = new FinalOrderOverview("#008", "eksempel@eksempel.no", Date.valueOf(LocalDate.now()),6049);
        FinalOrderOverview finalOrderOverview6 = new FinalOrderOverview("#009", "eksempel@eksempel.no", Date.valueOf(LocalDate.now()),14398);

        FinalOrderOverviewRegister.OrderRegister.addAll(finalOrderOverview1, finalOrderOverview2, finalOrderOverview3, finalOrderOverview4, finalOrderOverview5, finalOrderOverview6);
    }
}
