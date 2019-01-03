package by.iba.crearec.view.form;

import by.iba.crearec.controller.TestController;
import by.iba.crearec.model.TestModel;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import javax.inject.Inject;

public class TestModelEditor extends Dialog implements KeyNotifier {

	private static final long serialVersionUID = -9003792035183688180L;

	@Inject
	private TestController testController;

	private TestModel testModel;

	TextField name = new TextField("First name");
	TextField surname = new TextField("Last name");
	TextField age = new TextField("Age");

	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", VaadinIcon.TRASH.create());
	HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

	Binder<TestModel> binder = new Binder<>(TestModel.class);
	private ChangeHandler changeHandler;

	public TestModelEditor() {
		add(name, surname, age, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
//		setSpacing(true);

		save.getElement().getThemeList().add("primary");
		delete.getElement().getThemeList().add("error");

		addKeyPressListener(Key.ENTER, e -> saveAction());

		// wire action buttons to saveAction, deleteAction and reset
		save.addClickListener(e -> saveAction());
		delete.addClickListener(e -> deleteAction());
		cancel.addClickListener(e -> editTestModelAction(testModel));
		setVisible(false);
	}

	private void deleteAction() {
		testController.deleteTestModel(testModel.getId());
		changeHandler.onChange();
	}

	private void saveAction() {
		testController.saveTestModel(testModel);
		changeHandler.onChange();
	}

	public interface ChangeHandler {
		void onChange();
	}

	private void editTestModelAction(TestModel testModel) {
		if (testModel == null) {
			setVisible(false);
			return;
		}
		open();
		final boolean persisted = testModel.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			this.testModel = testController.findById(testModel.getId());
		} else {
			this.testModel = testModel;
		}
		cancel.setVisible(persisted);

		// Bind testModel properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(this.testModel);

		setVisible(true);

		// Focus first name initially
//		name.focus();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either saveAction or deleteAction
		// is clicked
		changeHandler = h;
	}

}
