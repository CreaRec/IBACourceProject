package by.iba.crearec.view;

import by.iba.crearec.util.ProjectConstants;
import by.iba.crearec.view.form.LoginForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
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
	private ListCustomerView listCustomerView;

	@Inject
	private LoginForm loginForm;

	private Button logout = new Button("Logout");

	@PostConstruct
	public void init() {
		if (UI.getCurrent().getSession().getAttribute(ProjectConstants.LOGIN_SESSION_NAME) == null) {
			loginForm.open();
		} else {
			logout.addClickListener(e -> logoutAction());

			add(logout, listCustomerView.open());
		}
	}

	private void logoutAction() {
		UI.getCurrent().getSession().setAttribute(ProjectConstants.LOGIN_SESSION_NAME, null);
		UI.getCurrent().getPage().reload();
	}
}
