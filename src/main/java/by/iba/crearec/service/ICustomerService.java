package by.iba.crearec.service;

import by.iba.crearec.model.Customer;

import java.util.List;

public interface ICustomerService {

	List<Customer> getAllCustomers();

	List<Customer> getPagerCustomers(int offset, int limit, String ssnLike);

	int getCountCustomers();

	Customer addCustomer(Customer customer);

}
