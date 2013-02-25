package productView;

import java.util.List;

import se.chalmers.ait.dat215.project.Product;
import ProductSearch.ProductSearch;

public class ProductListController {

	private ProductListView view;
	
	public ProductListController(ProductListView view) {
		this.view = view;
		updateView();
	}
	
	public void updateView() {
		ProductSearch search = new ProductSearch("", 50);
		List<Product> list = search.getProducts();
		for(Product p : list ) {
			ProductView pView = new ProductView(p);
			view.getViewPanel().add(pView);
			view.getViewPanel().revalidate();
		}
	}
	
	
}
