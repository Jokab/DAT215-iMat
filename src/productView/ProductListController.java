package productView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.List;

import ProductCategories.ProductCategories;
import ProductSearch.OrderByNameAscending;
import ProductSearch.OrderByNameDescending;
import ProductSearch.OrderByPriceAscending;
import ProductSearch.OrderByPriceDescending;
import ProductSearch.ProductFilter;
import ProductSearch.SearchFilterOption;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import core.MainController;
import core.ViewController;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingItem;
import ProductSearch.ProductSearch;
import ShoppingList.PopupControllerSave;
import ShoppingList.ShoppingList;
import ShoppingList.ShoppingListHandler;
import ShoppingList.ShoppingListPopupSave;

/**
 * A class for controlling the ProductListView, which holds and shows Products
 * found in a search. When the search function is used, updates the view
 * automatically with the results that were found, and sorted if a comparator is
 * specified.
 * 
 * @author Jakob, Sebastian Blomberg
 * 
 */
public class ProductListController implements ViewController,
		PropertyChangeListener {

	private ProductListView view;
	private MainController mainController;
	private String currentCategory;
	private ProductCategory currentSubcategory = null;
	private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
	private ShoppingListHandler handler = ShoppingListHandler.INSTANCE;

	private SearchFilterOption[] comboBoxValues ={new SearchFilterOption(new OrderByNameAscending(), "Namn stigande"), 
			new SearchFilterOption(new OrderByNameDescending(), "Namn fallande"),
			new SearchFilterOption(new OrderByPriceAscending(), "Pris stigande"),
			new SearchFilterOption(new OrderByPriceDescending(), "Pris fallande"),};
	
	/**
	 * The active product view which is stored when the popup is opened for
	 * saving product to list.
	 **/
	private ProductView activeProductView;

	public ProductListController(MainController mainController) {
		this.view = new ProductListView(comboBoxValues);
		this.mainController = mainController;
	}

	private void updateView(List<Product> list) {
		for (Product p : list) {
			ProductView pView = new ProductView(p);
			pView.addMouseListener(new ViewMouseListener());
			pView.getAddToListButton().addActionListener(
					new AddToListActionListener(p, pView));
			pView.getStarButton().addActionListener(new StarActionListener(p, pView));
			view.getViewPanel().add(pView);
		}
		view.getViewPanel().revalidate();
	}
	
	public void filter(String category) {
		filter(category, new OrderByNameAscending());
	}

	public void filter(String category, Comparator<Product> filter) {
		this.currentCategory = category;
		initComboBox(filter);
		updateView(ProductFilter.getProductByCategory(currentCategory, filter));
		view.setCurrentCategory(currentCategory);
		view.setSubcategories(
				ProductCategories.getInstance().getSubcategories(
						currentCategory), new SidePanelMouseListener());
	}
	
	public void filter(String category, ProductCategory subcategory) {
		filter(category, subcategory, new OrderByNameAscending());
	}
	
	public void filter(String category, ProductCategory subcategory, Comparator<Product> filter) {
		this.currentCategory = category;
		this.currentSubcategory = subcategory;
		initComboBox(filter);
		updateView(ProductFilter.getProductBySubcategory(subcategory, filter));
		view.setCurrentCategory(currentCategory);
		view.setSubcategories(
				ProductCategories.getInstance().getSubcategories(
						currentCategory), new SidePanelMouseListener(),
				subcategory);
	}

	private class SidePanelMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			ProductSidePanelButton btn = (ProductSidePanelButton) e.getSource();
			mainController.initProductListController(currentCategory,
					btn.getSubcategory());
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

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

		private ProductView pView;

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent evt) {
			if (evt.getSource().getClass().equals(ProductView.class)) {
				pView = (ProductView) evt.getSource();
				pView.getStarButton().setVisible(true);
				pView.getAddToListButton().setVisible(true);
			}
		}

		public void mouseExited(MouseEvent evt) {
			if (pView.contains(evt.getPoint())) {
				evt.consume();
			} else {
				if(!(dataHandler.isFavorite(pView.getProduct()))) {
					System.out.println(pView.getProduct().getName() + " " + dataHandler.isFavorite(pView.getProduct()));
					pView.getStarButton().setVisible(false);
				} else {
					pView.getStarButton().setVisible(true);
				}
				pView.getAddToListButton().setVisible(false);
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

	private class StarActionListener implements ActionListener {

		private Product product;
		private ProductView pView;
		
		public StarActionListener(Product p, ProductView pView) {
			this.product = p;
			this.pView = pView;
		}

		public void actionPerformed(ActionEvent e) {
			if (dataHandler.isFavorite(product)) {
				ImageIcon starIcon = new ImageIcon("img/starUnfilled.png");
				this.pView.getStarButton().setIcon(starIcon);
				dataHandler.removeFavorite(product);
			} else {
				ImageIcon starIcon = new ImageIcon("img/starFilled.png");
				this.pView.getStarButton().setIcon(starIcon);
				dataHandler.addFavorite(product);
			}
			((JButton)e.getSource()).repaint();
		}
	}

	private class AddToListActionListener implements ActionListener {

		private Product product;
		private ProductView pView;
		
		public AddToListActionListener(Product p, ProductView pView) {
			this.product = p;
			this.pView = pView;
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			PopupControllerSave controller = new PopupControllerSave(
					this.product, pView.getAmount(),  mainController);
			controller.addObserver(ProductListController.this);
		}
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		// Ugliest code ever
		ShoppingList list = (ShoppingList) evt.getNewValue();
		Object[] productInfo = (Object[])evt.getOldValue();
		Product p = (Product)productInfo[0]; 
		double amount = (double)productInfo[1];
		ShoppingItem sentItem = new ShoppingItem(p);
		sentItem.setAmount(amount);
		list.addItem(sentItem);
		mainController.removePopup();
		handler.writeLists();
	}
	
	private class ComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SearchFilterOption filter = (SearchFilterOption) ((JComboBox)e.getSource()).getSelectedItem();			
			if(currentSubcategory != null) {
				mainController.initProductListController(currentCategory, currentSubcategory, filter.getFilter());
			} else {
				mainController.initProductListController(currentCategory, filter.getFilter());
			}
		}
		
	}
	
	private void initComboBox(Comparator<Product> filter) {
		for(int i = 0; i < comboBoxValues.length; i++) {
			if(filter.getClass() == comboBoxValues[i].getFilter().getClass()) {
				view.setSelectedIndexComboBox(i);
				break;
			}
		}
		
		view.addComboBoxListener(new ComboBoxListener());
	}
}
