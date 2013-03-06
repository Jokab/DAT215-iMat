package frontPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.Product;

import ProductCategories.ProductCategories;

import core.MainController;
import core.ViewController;

public class FrontPageController implements ViewController {
	
	private final FrontPageView view;
	private final MainController mainController;
	
	public FrontPageController(MainController mainController) {
		this.view = new FrontPageView(new FrontPageButtonListener());
		this.mainController = mainController;
	}

	@Override
	public JPanel getView() {
		return this.view;
	}
	
	private class FrontPageButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() != null) {
				Product p = ((FrontPageItemButton) e.getSource()).getProduct();
				mainController.initProductListController(ProductCategories.getInstance().getCategory(p.getCategory()), p.getCategory(), p);
			}
			
		}
		
	}
}
