package Datamaskin.orders;

public class Order {
    public static void setExampleData(){
        FinalOrderOverview finalOrderOverview1 = new FinalOrderOverview("ordre-1", "bruker@bruker.no", "2019-01-03", 5049);
        FinalOrderOverview finalOrderOverview2 = new FinalOrderOverview("ordre-2", "eksempel@eksempel.no", "2019-02-28",2049);
        FinalOrderOverview finalOrderOverview3 = new FinalOrderOverview("ordre-3", "bruker@bruker.no", "2019-05-10",3009);
        FinalOrderOverview finalOrderOverview4 = new FinalOrderOverview("ordre-4", "eksempel@eksempel.no", "2019-12-31",1049);
        FinalOrderOverview finalOrderOverview5 = new FinalOrderOverview("ordre-5", "bruker@bruker.no", "2020-02-28",6049);
        FinalOrderOverview finalOrderOverview6 = new FinalOrderOverview("ordre-6", "eksempel@eksempel.no", "2020-03-30",4398);
        FinalOrderOverview finalOrderOverview7 = new FinalOrderOverview("ordre-7", "eksempel@eksempel.no", "2020-04-10",14398);
        FinalOrderOverview finalOrderOverview8 = new FinalOrderOverview("ordre-8", "bruker@bruker.no", "2020-04-15",3986);
        FinalOrderOverview finalOrderOverview9 = new FinalOrderOverview("ordre-9", "bruker@bruker.no", "2012-05-15",12743);

        FinalOrderOverviewRegister.OrderRegister.addAll(finalOrderOverview1, finalOrderOverview2, finalOrderOverview3,
                finalOrderOverview4, finalOrderOverview5, finalOrderOverview6, finalOrderOverview7, finalOrderOverview8, finalOrderOverview9);
    }

    // et register for all overordnet info
    public static FinalOrderOverviewRegister OrderRegister = new FinalOrderOverviewRegister();

    // et register for kun overordnet info som gjelder hver enkelt kunde
    public static FinalOrderCustomerOverviewRegister aCustomersOrderRegister = new FinalOrderCustomerOverviewRegister();

    public static void deleteCustomerInfo(){
        for(FinalOrderOverview anOrder : FinalOrderOverviewRegister.OrderRegister) {
            aCustomersOrderRegister.deleteElement(anOrder);
        }
    }

    public static FinalOrderOverview addCustomerOverviewInfo(String epost){
        for(FinalOrderOverview anOrder : FinalOrderOverviewRegister.OrderRegister) {
            if(epost.equals(anOrder.getEmail())){
                aCustomersOrderRegister.addElement(anOrder);
            }
        }
        return null;
    }

}
