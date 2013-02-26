package productView;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import se.chalmers.ait.dat215.project.Product;
import ProductSearch.ProductSearch;
import SearchBar.AutoCompleteProductsPanel;

public class ProductListController {

	private ProductListView view;

	public ProductListController(ProductListView view) {
		this.view = view;
		updateView();
	}

	public void updateView() {
		ProductSearch search = new ProductSearch("Kyckling", 50);
		List<Product> list = search.getProducts();
		for (Product p : list) {
			ProductView pView = new ProductView(p);
			pView.addMouseListener(new ViewMouseListener());
			view.getViewPanel().add(pView);
			view.getViewPanel().revalidate();
		}
	}

	private class ViewMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent evt) {
			if (evt.getSource().getClass().equals(ProductView.class)) {
				ProductView panel = (ProductView) evt.getSource();
				panel.getStarLabel().setVisible(true);
				panel.getShoppingListBox().setVisible(true);
			}
		}

		public void mouseExited(MouseEvent evt) {
			ProductView panel;
			if (evt.getSource().getClass().equals(ProductView.class)) {
				panel = (ProductView) evt.getSource();
			} else {
				panel = (ProductView) SwingUtilities
						.getAncestorNamed("ProductView", evt.getComponent());
			}

			Component c = SwingUtilities.getDeepestComponentAt(evt.getComponent(), evt.getX(), evt.getY());
			if (c != null && SwingUtilities.isDescendingFrom(c, panel)) {
				return;
			} else {
				panel.getStarLabel().setVisible(false);
				panel.getShoppingListBox().setVisible(false);
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}
