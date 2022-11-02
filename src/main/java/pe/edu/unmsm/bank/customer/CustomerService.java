package pe.edu.unmsm.bank.customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomerPassword(Long id, CustomerPassword customerPassword);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomerById(Long id);

}
