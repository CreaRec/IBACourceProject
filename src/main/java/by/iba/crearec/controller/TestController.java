package by.iba.crearec.controller;

import by.iba.crearec.model.TestModel;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class TestController {

	private List<TestModel> testModels;

	public List<TestModel> getTestModels() {
		testModels = new ArrayList<>();
		testModels.add(new TestModel("Ivan", "Ivanov", "34"));
		testModels.add(new TestModel("Sasha", "Petrovich", "23"));
		testModels.add(new TestModel("Ararat", "Blablablat", "56"));
		return testModels;
	}

	public TestModel findById(String id) {
		return testModels.stream().filter(tm -> tm.getId().equals(id)).findFirst().orElse(null);
	}

	public void saveTestModel(TestModel testModel) {
		Optional<TestModel> first = testModels.stream().filter(tm -> tm.getId().equals(testModel.getId())).findFirst();
		if (first.isPresent()) {
			TestModel testModelFound = first.get();
			testModelFound.setName(testModel.getName());
			testModelFound.setSurname(testModel.getSurname());
			testModelFound.setAge(testModel.getAge());
		} else {
			testModels.add(testModel);
		}
	}

	public void deleteTestModel(String id) {
		testModels = testModels.stream().filter(testModel -> !testModel.getId().equals(id)).collect(Collectors.toList());
	}

}
