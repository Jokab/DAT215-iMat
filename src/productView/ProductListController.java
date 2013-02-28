package productView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Comparator;
import java.util.List;

import ProductCategories.ProductCategories;
import ProductSearch.ProductFilter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import core.MainController;
import core.ViewController;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import ProductSearch.ProductSearch;

/**
 * A class for controlling the ProductListView, which holds and shows Products
 * found in a search. When the search function is used, updates the view automatically
 * with the results that were found, and sorted if a comparator is specified.
 * 
 * @author Jakob, Sebastian Blomberg
 *
 */
public class ProductListController implements ViewController {

	private ProductListView view;
	private MainController mainController;
	private String currentCategory;
	IMatDataHandler dataHandler = IMatDataHandler.getInstance();

	public ProductListController(MainController mainController) {
		this.view = new ProductListView();
		this.mainController = mainController;
	}

	private void updateView(List<Product> list) {
		for (Product p : list) {
			ProductView pView = new ProductView(p);
			pView.addMouseListener(new ViewMouseListener());
			pView.addActionListener(new StarActionListener());
			view.getViewPanel().add(pView);
		}
		view.getViewPanel().revalidate();
	}
	
	/**
	 * Updates the view with products found in search using a name.
	 * 
	 * @param name The name to be searched for.
	 */
	public void search(String name) {
		updateView(new ProductSearch(name).getProducts());
	}
	
	/**
	 * Updates the view with "results" amount of products found in search using a name.
	 * 
	 * @param name The name to be searched for.
	 * @param results The amount of results to return.
	 */
	public void search(String name, int results) {
		updateView(new ProductSearch(name, results).getProducts());
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
		updateView(new ProductSearch(name, results, comp).getProducts());
	}

	public void filter(String category){
		this.currentCategory = category;
		updateView(ProductFilter.getProductByCategory(currentCategory));
		view.setCurrentCategory(currentCategory);
		view.setSubcategories(ProductCategories.getInstance().getSubcategories(currentCategory), new SidePanelMouseListener());
	}
	
	public void filter(String category, ProductCategory subcategory) {
		this.currentCategory = category;
		updateView(ProductFilter.getProductBySubcategory(subcategory));
		view.setCurrentCategory(currentCategory);
		view.setSubcategories(ProductCategories.getInstance().getSubcategories(currentCategory), new SidePanelMouseListener(), subcategory);
	}
	
	private class SidePanelMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			ProductSidePanelButton btn = (ProductSidePanelButton) e.getSource();
			mainController.initProductListController(currentCategory, btn.getSubcategory());
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			((ProductSidePanelButton) e.getSource()).toggle();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((ProductSidePanelButton) e.getSource()).toggle();
		}
		
	}
	
	private class ViewMouseListener implements MouseListener {

		private ProductView panel;
		@Override
		public void mouseClicked(MouseEvent arg0) { }

		@Override
		public void mouseEntered(MouseEvent evt) {
			if (evt.getSource().getClass().equals(ProductView.class)) {
				panel = (ProductView) evt.getSource();
				panel.getStarButton().setVisible(true);
			}
		}

		public void mouseExited(MouseEvent evt) {
			if (panel.contains(evt.getPoint())) {
				return;
			} else {
				panel.getStarButton().setVisible(false);
			}	
		}

		@Override
		public void mousePressed(MouseEvent arg0) { }

		@Override
		public void mouseReleased(MouseEvent arg0) { }
	}

	private class StarActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			ProductView panel = (ProductView) e.getSource();
			
			if(dataHandler.isFavorite(panel.getProduct())){
				String PicURL = "/.dat215/imat/images/SuperStar.png";
				ImageIcon starIcon = new ImageIcon(System.getProperty("user.home") + PicURL);
				panel.getStarButton().setIcon(starIcon);
				dataHandler.addFavorite(panel.getProduct());
			} else {
				String PicURL = "/.dat215/imat/images/SuperStarOfylld.png";
				ImageIcon starIcon = new ImageIcon(System.getProperty("user.home") + PicURL);
				panel.getStarButton().setIcon(starIcon);
				dataHandler.removeFavorite(panel.getProduct());
			}
		}
	}

	@Override
	public JPanel getView() {
		return view;
	}
}
