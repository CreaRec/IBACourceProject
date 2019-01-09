package by.iba.crearec.controller;

import by.iba.crearec.model.Customer;
import by.iba.crearec.service.ICustomerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CustomerController {

	@Inject
	private ICustomerService customerService;

	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	public List<Customer> getPagerCustomers(int offset, int limit, String ssnLike) {
		return customerService.getPagerCustomers(offset, limit, ssnLike);
	}

	public int getCountCustomers() {
		return customerService.getCountCustomers();
	}

	public Customer addCustomer(Customer customer) {
		return customerService.addCustomer(customer);
	}

//	public TestModel findById(String id) {
//		return testModels.stream().filter(tm -> tm.getId().equals(id)).findFirst().orElse(null);
//	}
//
//	public void saveTestModel(TestModel testModel) {
//		Optional<TestModel> first = testModels.stream().filter(tm -> tm.getId().equals(testModel.getId())).findFirst();
//		if (first.isPresent()) {
//			TestModel testModelFound = first.get();
//			testModelFound.setName(testModel.getName());
//			testModelFound.setSurname(testModel.getSurname());
//			testModelFound.setAge(testModel.getAge());
//		} else {
//			testModels.add(testModel);
//		}
//	}
//
//	public void deleteTestModel(String id) {
//		testModels = testModels.stream().filter(testModel -> !testModel.getId().equals(id)).collect(Collectors.toList());
//	}

}
