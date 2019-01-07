package by.iba.crearec.view;

import by.iba.crearec.controller.CustomerController;
import by.iba.crearec.model.Customer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;

import javax.inject.Inject;
import java.util.List;

public class ListCustomerView extends HorizontalLayout {

	private static final long serialVersionUID = -6687632485887114643L;

	@Inject
	private CustomerController customerController;

	private Grid<Customer> grid = new Grid<>(Customer.class);

	private TextField textField = new TextField("Filter by ssn");
	private Button filterButton = new Button("Filter");

	public ListCustomerView open() {
		List<Customer> allCustomers = customerController.getAllCustomers();
		ListDataProvider<Customer> dataProvider = DataProvider.ofCollection(allCustomers);

		dataProvider.setSortOrder(Customer::getName, SortDirection.ASCENDING);

		grid.setDataProvider(dataProvider);

		Button buttonNew = new Button("New");
		// Instantiate and edit new Customer the new button is clicked
		buttonNew.addClickListener(e -> {
			System.out.println("sgfegergerg");
//			UI.getCurrent().add(testModelEditor);
//			loginForm.open();
		});

		filterButton.addClickListener(event -> {
			ListDataProvider<Customer> dataProvider1 = DataProvider.ofCollection(customerController.getAllCustomers());

			dataProvider1.setSortOrder(Customer::getName, SortDirection.ASCENDING);

			grid.setDataProvider(dataProvider1);
		});

		add(textField, filterButton, grid, buttonNew);
		setSizeFull();
		return this;
	}
}
