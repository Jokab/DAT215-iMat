package productView;

import components.SidePanelButton;
import se.chalmers.ait.dat215.project.ProductCategory;


public class ProductSidePanelButton extends SidePanelButton {

	private ProductCategory subcatcat;
	private boolean isActive = false;
	private boolean isHover = false;
	public ProductSidePanelButton(String title, ProductCategory subcategory) {
		super(title);
		this.subcatcat = subcategory;
	}
	
	public ProductCategory getSubcategory() {
		return subcatcat;
	}
}
