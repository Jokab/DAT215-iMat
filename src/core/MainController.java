package core;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.JPanel;

import checkout.CheckoutController;
import checkout.CheckoutEnum;
import frontPage.FrontPageController;

import order.OrderViewController;

import SearchBar.SearchController;
import ShoppingList.ShoppingList;
import ShoppingList.ShoppingListPopupNew;
import ShoppingList.ShoppingListViewController;

import productView.ProductListController;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import search.SearchViewController;
import menu.MenuController;
import myAccount.MyAccountController;
import myAccount.MyAccountEnum;

/**
 * The main controller of the application. Initializes the standard controllers
 * for the application and initializes ViewControllers. Also keeps a history of 
 * previously added ViewControllers which can be rolled back.
 * @author Sebastian Blomberg
 *
 */
public class MainController {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new MainController();
	}
	
	private LinkedList<ViewController> history = new LinkedList<ViewController>();
	private MainView mainView;
	private final int DEFAULT_HISTORY_STEP = 10;
	
	public MainController() {
		this.mainView = new MainView(this); 
		new MenuController(mainView.getHeaderView(), this);
		new SearchController(mainView.getSearchBar(), mainView.getAutoCompleteContainer(), this);
	}
	
	/**
	 * Initializes a <code>ProductListController</code> with the specified settings.
	 * @param category
	 */
	public void initProductListController(String category) {
		ProductListController productListController = new ProductListController(this);
		productListController.filter(category);
		switchController(productListController);
	}
	
	/**
	 * Initializes a <code>ProductListController</code> with the specified settings.
	 * @param category
	 */
	public void initProductListController(String category, Comparator<Product> filter) {
		ProductListController productListController = new ProductListController(this);
		productListController.filter(category, filter);
		switchController(productListController);
	}
	
	/**
	 * Initializes a <code>ProductListController</code> with the specified settings.
	 * @param category
	 * @param subcategory
	 */
	public void initProductListController(String category, ProductCategory subcategory) {
		ProductListController productListController = new ProductListController(this);
		productListController.filter(category, subcategory);
		switchController(productListController);
	}
	
	/**
	 * Initializes a <code>ProductListController</code> with the specified settings.
	 * @param category
	 * @param subcategory
	 */
	public void initProductListController(String category, ProductCategory subcategory, Comparator<Product> filter) {
		ProductListController productListController = new ProductListController(this);
		productListController.filter(category, subcategory, filter);
		switchController(productListController);
	}
	
	/**
	 * Initializes a new <code>ShoppingListViewController</code> inside the standard 
	 * <code>MyAccountController</code> to get a side panel for my account.
	 */
	public void initShoppingListController() {
		switchController(new MyAccountController(new ShoppingListViewController(this), MyAccountEnum.SHOPPINGLISTS));
	}
	
	/**
	 * Initializes a new <code>OrderViewController</code> inside the standard 
	 * <code>MyAccountController</code> to get a side panel for my account.
	 */
	public void initOrderHistoryController() {
		switchController(new MyAccountController(new OrderViewController(), MyAccountEnum.ORDERHISTORY));
	}	
	
	public void initSearchViewController(String searchString) {
		switchController(new SearchViewController(searchString, this));
	}
	
	public void initSearchViewController(String searchString, Comparator<Product> filter) {
		switchController(new SearchViewController(searchString, filter, this));
	}
	
	public void initCheckoutController(CheckoutController controller) {
		switchController(controller);
	}
	
	public void initCheckoutController() {
		switchController(new CheckoutController(CheckoutEnum.INFORMATION, this));
	}
	
	public void initFrontPageController() {
		switchController(new FrontPageController());
	}
	
	public void initPreviousController() {
		if(history.size() > 1) {
			history.removeLast();
			mainView.setContentView(history.getLast().getView());
		}
	}
	
	private void switchController(ViewController controller) {
		if(history.size() >= DEFAULT_HISTORY_STEP) {
			history.removeFirst();
		}
		history.add(controller);
		mainView.setContentView(controller.getView());
	}

	public void showPopup(JPanel shoppingListPopupNew) {
		mainView.setPopup(shoppingListPopupNew);
		
	}
	
	public void removePopup() {
		mainView.removePopup();
	}
}
