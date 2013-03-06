package core;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.JPanel;

import checkout.CheckoutController;
import checkout.CheckoutEnum;
import frontPage.FrontPageController;

import order.OrderViewController;

import SearchBar.SearchController;
import ShoppingList.ShoppingListViewController;

import productView.ProductListController;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import search.SearchViewController;
import menu.MenuController;
import myAccount.MyAccountController;
import myAccount.MyAccountEnum;
import myProfile.MyInfoController;

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
		this.mainView.addMainViewWindowListener(new MainFrameWindowListener());
		this.mainView.addBackButtonListener(new BackButtonListener());
		new MenuController(mainView.getHeaderView(), this);
		new SearchController(mainView.getSearchBar(), mainView.getAutoCompleteContainer(), this);
		this.mainView.getHeaderView().addHomeButtonListener(new HomeButtonListener());
		
		switchController(new FrontPageController(this));
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
		switchController(new MyAccountController(new ShoppingListViewController(this), MyAccountEnum.SHOPPINGLISTS, this));
	}
	
	/**
	 * Initializes a new <code>OrderViewController</code> inside the standard 
	 * <code>MyAccountController</code> to get a side panel for my account.
	 */
	public void initOrderHistoryController() {
		switchController(new MyAccountController(new OrderViewController(), MyAccountEnum.ORDERHISTORY, this));
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
		switchController(new FrontPageController(this));
	}
	
	public void initMyInfoController() {
		switchController(new MyAccountController(new MyInfoController(), MyAccountEnum.SETTINGS, this));
	}
	
	public void initPreviousController() {
		if(history.size() > 1) {
			history.removeLast();
			mainView.setContentView(history.getLast().getView());
		} else {
			mainView.setEnableBackButton(false);
		}
	}
	
	private void switchController(ViewController controller) {
		if(history.size() >= DEFAULT_HISTORY_STEP) {
			history.removeFirst();
		}
		history.add(controller);
		mainView.setContentView(controller.getView());
		if(history.size() > 1) {
			mainView.setEnableBackButton(true);
		}
	}

	public void showPopup(JPanel shoppingListPopupNew) {
		mainView.setPopup(shoppingListPopupNew);
		
	}
	
	public void removePopup() {
		mainView.removePopup();
	}
	
	private class MainFrameWindowListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			mainView.shutDown();
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			initPreviousController();
		}
		
	}
	
	private class HomeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			initFrontPageController();
		}
		
	}
}
