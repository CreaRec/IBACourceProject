package by.iba.crearec.view;

import by.iba.crearec.controller.TestController;
import by.iba.crearec.model.TestModel;
import by.iba.crearec.util.ProjectConstants;
import by.iba.crearec.view.form.LoginForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Route("")
@NoArgsConstructor
public class GeneralView extends HorizontalLayout {

	private static final long serialVersionUID = -5127216852126202666L;

	@Inject
	private TestController testController;

	@Inject
	private LoginForm loginForm;

	private Button logout = new Button("Logout");

	private Grid<TestModel> grid = new Grid<>();

	@PostConstruct
	public void init() {
		if (UI.getCurrent().getSession().getAttribute(ProjectConstants.LOGIN_SESSION_NAME) == null) {
			loginForm.open();
		} else {
			logout.addClickListener(e -> logoutAction());

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

			add(logout, grid, buttonNew);
		}
	}

	private void logoutAction() {
		UI.getCurrent().getSession().setAttribute(ProjectConstants.LOGIN_SESSION_NAME, null);
		UI.getCurrent().getPage().reload();
	}
}
