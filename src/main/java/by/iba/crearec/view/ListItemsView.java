package by.iba.crearec.view;

import by.iba.crearec.controller.TestController;
import by.iba.crearec.model.TestModel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import javax.inject.Inject;

public class ListItemsView extends HorizontalLayout {

	private static final long serialVersionUID = -6687632485887114643L;

	@Inject
	private TestController testController;

	private Grid<TestModel> grid = new Grid<>();

	public ListItemsView open() {
		grid.setItems(testController.getTestModels());
		grid.addColumn(TestModel::getName).setHeader("Name");
		grid.addColumn(TestModel::getSurname).setHeader("Surname");
		grid.addColumn(TestModel::getAge).setHeader("Age");

		Button buttonNew = new Button("New");
		// Instantiate and edit new Customer the new button is clicked
		buttonNew.addClickListener(e -> {
			System.out.println("sgfegergerg");
//			UI.getCurrent().add(testModelEditor);
//			loginForm.open();
		});

		add(grid, buttonNew);
		return this;
	}
}
