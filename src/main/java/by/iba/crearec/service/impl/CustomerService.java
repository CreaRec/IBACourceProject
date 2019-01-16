package by.iba.crearec.service.impl;

import by.iba.crearec.command.AddCustomerCommand;
import by.iba.crearec.model.Customer;
import by.iba.crearec.rmi.CommandManager;
import by.iba.crearec.service.ICustomerService;
import by.iba.crearec.util.DataBaseUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseUtils.class);

	@Inject
	private CommandManager commandManager;

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DataBaseUtils.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
			while (resultSet.next()) {
				customers.add(new Customer(resultSet.getString("ssn"), resultSet.getString("cust_name"), resultSet.getString("address")));
			}
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.error("", e);
		} finally {
			DataBaseUtils.closeConnection(connection);
		}
		return customers;
	}

	@Override
	public List<Customer> getPagerCustomers(int offset, int limit, String ssnLike) {
		List<Customer> customers = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DataBaseUtils.getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM customer";
			if (StringUtils.isNotEmpty(ssnLike)) {
				query += "WHERE customer.ssn LIKE '%" + ssnLike + "'%";
			}
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customer LIMIT " + limit + " OFFSET " + offset);
			while (resultSet.next()) {
				customers.add(new Customer(resultSet.getString("ssn"), resultSet.getString("cust_name"), resultSet.getString("address")));
			}
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.error("", e);
		} finally {
			DataBaseUtils.closeConnection(connection);
		}
		return customers;
	}

	@Override
	public int getCountCustomers() {
		List<Customer> customers = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DataBaseUtils.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM customer");
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.error("", e);
		} finally {
			DataBaseUtils.closeConnection(connection);
		}
		return 0;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		Customer addedCustomer = null;
		try {
			addedCustomer = commandManager.startCommand(AddCustomerCommand.class, customer);
			if (addedCustomer.getErrorMessage() != null && !addedCustomer.getErrorMessage().isEmpty()) {
				throw new Exception(addedCustomer.getErrorMessage());
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return addedCustomer;
	}
}
