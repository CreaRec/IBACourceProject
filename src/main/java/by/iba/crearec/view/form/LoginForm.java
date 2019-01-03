package by.iba.crearec.view.form;

import by.iba.crearec.controller.LoginController;
import by.iba.crearec.model.Person;
import by.iba.crearec.util.ProjectConstants;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import javax.inject.Inject;
import javax.naming.NamingException;

public class LoginForm extends Dialog implements KeyNotifier {

	private static final long serialVersionUID = -3374781727176353760L;

	@Inject
	private LoginController loginController;

	private TextField username = new TextField("Username");
	private PasswordField password = new PasswordField("Password");

	private Button confirm = new Button("Confirm", VaadinIcon.ENTER.create());
	private HorizontalLayout actions = new HorizontalLayout(confirm);

	public LoginForm() {
		VerticalLayout items = new VerticalLayout(username, password, actions);
		items.setSpacing(true);
		add(items);

		confirm.getElement().getThemeList().add("primary");

		addKeyPressListener(Key.ENTER, e -> confirmAction());

		confirm.addClickListener(e -> confirmAction());
	}

	private void confirmAction() {
		try {
			boolean result = loginController.checkPerson(new Person(username.getValue(), password.getValue()));
			if (result) {
				UI.getCurrent().getSession().setAttribute(ProjectConstants.LOGIN_SESSION_NAME, username.getValue());
				UI.getCurrent().getPage().reload();
			} else {
				Notification.show("Invalid login or password");
			}
		} catch (NamingException e) {
			Notification.show("Internal server error");
		}
	}

}
