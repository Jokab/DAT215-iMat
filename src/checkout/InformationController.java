package checkout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.ShoppingCart;
import shoppingCart.ShoppingCartAdapter;

import core.MainController;
import core.ViewController;

public class InformationController implements ViewController {

	private InformationPanel view;
	private CheckoutEnum nextController = CheckoutEnum.VERIFICATION;
	private MainController mainController;
	
	public InformationController(MainController mainController) {
		this.mainController = mainController;
		this.view = new InformationPanel();
		this.view.addCancelButtonListener(new CancelButtonClicked());
		this.view.addNextButtonListener(new NextButtonListener());
	}
	
	private class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Session.getInstance().infoIsOk()) {
				mainController.initCheckoutController(new CheckoutController(nextController, mainController));
			} else if(!Session.getInstance().infoIsOk()){
				view.displayErrorMessage();
			}
		}
	}
	
	private class CancelButtonClicked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainController.initPreviousController();
		}
	}

	@Override
	public JPanel getView() {
		return this.view;
	}
}
