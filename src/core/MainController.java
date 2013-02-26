package core;
import java.util.LinkedList;

import se.chalmers.ait.dat215.project.ProductCategory;
import menu.MenuController;

/**
 * The main controller of the application
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
	private MenuController menuController;
	private final int DEFAULT_HISTORY_STEP = 10;
	
	public MainController() {
		this.mainView = new MainView(); 
		this.menuController = new MenuController(mainView.getHeaderView(), this);
		
	}
	
	public void initProductListController(String category) {
		//new ProductListController()
		switchController(null);
	}
	
	public void initProductListController(String category, ProductCategory productCategory) {
		//new ProductListController()
		switchController(null);
	}
	
	private void switchController(ViewController controller) {
		if(history.size() >= DEFAULT_HISTORY_STEP) {
			history.removeFirst();
		}
		history.add(controller);
	}
}
