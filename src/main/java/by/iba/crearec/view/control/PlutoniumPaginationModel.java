package by.iba.crearec.view.control;

import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.List;

public interface PlutoniumPaginationModel extends TemplateModel {

	void setPage(int page);
	void setTotal(int total);
	void setLimit(int limit);
	void setSize(int size);

	int getPage();
	int getTotal();
	int getLimit();
}
