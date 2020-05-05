package datamaskin.filbehandling;

import datamaskin.users.Customer;
import java.io.IOException;
import java.util.List;

public interface iReadFromCustomerFile {

    List<Customer> readFromCustomerFile(String path) throws IOException;

    Customer parseToCustomer(String line) throws IOException;
}
