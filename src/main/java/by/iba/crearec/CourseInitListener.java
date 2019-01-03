package by.iba.crearec;

import by.iba.crearec.util.DataBaseUtils;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class CourseInitListener implements VaadinServiceInitListener {
	private static final long serialVersionUID = -7751231935131340259L;

	@Override
	public void serviceInit(ServiceInitEvent initEvent) {
		if (!DataBaseUtils.isLiquibaseUpdated) {
			DataBaseUtils.liquibaseUpdateWithChangelog();
		}
	}
}
