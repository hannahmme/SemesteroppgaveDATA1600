package datamaskin.orders;

public class Order {

    // et register for all overordnet info
    public static FinalOrderOverviewRegister OrderRegister = new FinalOrderOverviewRegister();

    // et register for kun overordnet info som gjelder hver enkelt kunde
    public static FinalOrderCustomerOverviewRegister aCustomersOrderRegister = new FinalOrderCustomerOverviewRegister();

}
