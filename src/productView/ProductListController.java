package productView;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import core.ViewController;

import se.chalmers.ait.dat215.project.Product;
import ProductSearch.ProductSearch;
import SearchBar.AutoCompleteProductsPanel;

/**
 * A class for controlling the ProductListView, which holds and shows Products
 * found in a search. When the search function is used, updates the view automatically
 * with the results that were found, and sorted if a comparator is specified.
 * 
 * @author Jakob
 *
 */
public class ProductListController implements ViewController {

	private ProductListView view;

	public ProductListController(ProductListView view) {
		this.view = new ProductListView();
	}

	private void updateView(ProductSearch search) {
		List<Product> list = search.getProducts();
		for (Product p : list) {
			ProductView pView = new ProductView(p);
			pView.addMouseListener(new ViewMouseListener());
			view.getViewPanel().add(pView);
			view.getViewPanel().revalidate();
		}
	}
	
	/**
	 * Updates the view with products found in search using a name.
	 * 
	 * @param name The name to be searched for.
	 */
	public void search(String name) {
		updateView(new ProductSearch(name));
	}
	
	/**
	 * Updates the view with "results" amount of products found in search using a name.
	 * 
	 * @param name The name to be searched for.
	 * @param results The amount of results to return.
	 */
	public void search(String name, int results) {
		updateView(new ProductSearch(name, results));
	}
	
	/**
	 * Updates the view with "results" amount of products found in search using a name,
	 * sorted by a comparator. 
	 * 
	 * @param name The name to be searched for.
	 * @param results The amount of results to return.
	 * @param comp The comparator to be used.
	 */
	public void search(String name, int results, Comparator<Product> comp) {
		updateView(new ProductSearch(name, results, comp));
	}

	
	private class ViewMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) { }

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
		public void mousePressed(MouseEvent arg0) { }

		@Override
		public void mouseReleased(MouseEvent arg0) { }
	}

}
