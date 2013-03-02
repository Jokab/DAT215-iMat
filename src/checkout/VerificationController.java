package checkout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import core.MainController;
import core.ViewController;

public class VerificationController implements ViewController {

	private VerificationPanel view;
	private CheckoutEnum nextController = CheckoutEnum.RECEIPT;
	private MainController mainController;
	
	public VerificationController(MainController mainController) {
		this.mainController = mainController;
		this.view = new VerificationPanel();
		this.view.addCancelButtonListener(new CancelButtonClicked());
		this.view.addSubmitButtonListener(new NextButtonListener()); // submit same as next
	}
	
	private class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainController.initCheckoutController(new CheckoutController(nextController, mainController));
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
