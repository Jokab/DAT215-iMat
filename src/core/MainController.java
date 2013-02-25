package core;
import java.util.LinkedList;
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
	
	public MainController() {
		this.mainView = new MainView(); 
		this.menuController = new MenuController(mainView.getHeaderView());
		
	}
	
	public void switchController(ViewController controller) {
		
	}
}
