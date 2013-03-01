package checkout;

import javax.swing.JPanel;

import core.MainController;
import core.ViewController;

public class CheckoutController implements ViewController {

	private MainController mainController;
	private CheckoutEnum nextController;
	private ViewController activeController;

	public CheckoutController(CheckoutEnum nextController,
			MainController mainController) {
		this.mainController = mainController;
		this.nextController = nextController;
		initController();
	}

	private void initController() {
		switch (nextController) {
		case INFORMATION:
			activeController = new InformationController(mainController);
			break;
		case VERIFICATION:
			activeController = new VerificationController(mainController);
			break;
		case RECEIPT:
			activeController = new ReceiptController(mainController);
			break;
			
		default:
			activeController = new InformationController(mainController);
			break;
		}

	}

	@Override
	public JPanel getView() {
		return this.activeController.getView();
	}

	public ViewController getActiveController() {
		return this.activeController;
	}
}
