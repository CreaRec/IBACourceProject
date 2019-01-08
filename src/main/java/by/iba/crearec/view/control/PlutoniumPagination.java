package by.iba.crearec.view.control;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.ModelItem;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;

@Tag("plutonium-pagination")
@HtmlImport("bower_components/plutonium-pagination/plutonium-pagination.html")
public class PlutoniumPagination extends PolymerTemplate<PlutoniumPaginationModel> {

	private static final long serialVersionUID = 7540818999156813585L;

	public PlutoniumPagination(int limit, int total) {
		setId("plutonium-pagination");
		PlutoniumPaginationModel model = getModel();
		model.setLimit(limit);
		model.setPage(1);
		model.setSize(2);
		model.setTotal(total);
	}

	public void setPage(int number) {
		getModel().setPage(number);
	}

    @EventHandler
    private void onChange() {
	    System.out.println("34234234234");
    }

	@EventHandler
	private void onPageChange(int oldPage) {
		System.out.println("111111111111");
	}
}