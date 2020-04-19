package Datamaskin.orders;

import java.sql.Date;
import java.time.LocalDate;

public class OrderExample {
    public static void setExampleData(){
        FinalOrderOverview finalOrderOverview1 = new FinalOrderOverview("#001", "bruker@bruker.no", "2019-01-03", 5049);
        FinalOrderOverview finalOrderOverview2 = new FinalOrderOverview("#002", "eksempel@eksempel.no", "2019-02-28",2049);
        FinalOrderOverview finalOrderOverview3 = new FinalOrderOverview("#003", "bruker@bruker.no", "2019-05-10",3009);
        FinalOrderOverview finalOrderOverview4 = new FinalOrderOverview("#004", "eksempel@eksempel.no", "2019-12-31",1049);
        FinalOrderOverview finalOrderOverview5 = new FinalOrderOverview("#005", "bruker@bruker.no", "2020-02-28",6049);
        FinalOrderOverview finalOrderOverview6 = new FinalOrderOverview("#006", "eksempel@eksempel.no", "2020-03-30",4398);
        FinalOrderOverview finalOrderOverview7 = new FinalOrderOverview("#007", "eksempel@eksempel.no", "2020-04-10",14398);
        FinalOrderOverview finalOrderOverview8 = new FinalOrderOverview("#008", "bruker@bruker.no", "2020-04-15",3986);
        FinalOrderOverview finalOrderOverview9 = new FinalOrderOverview("#009", "bruker@bruker.no", "2012-05-15",12743);

        FinalOrderOverviewRegister.OrderRegister.addAll(finalOrderOverview1, finalOrderOverview2, finalOrderOverview3,
                finalOrderOverview4, finalOrderOverview5, finalOrderOverview6, finalOrderOverview7, finalOrderOverview8, finalOrderOverview9);
    }
}
