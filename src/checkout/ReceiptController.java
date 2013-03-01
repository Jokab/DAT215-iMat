package checkout;

import javax.swing.JPanel;

import ShoppingList.PopupControllerNew;
import ShoppingList.PopupControllerSave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.MainController;
import core.ViewController;

public class ReceiptController implements ViewController {

	private ReceiptPanel view;
	private CheckoutEnum nextController = CheckoutEnum.RECEIPT;
	private MainController mainController;

	public ReceiptController(MainController mainController) {
		this.mainController = mainController;
		this.view = new ReceiptPanel();
		this.view.addSaveToListListener(new SaveToListListener());
		this.view.addDoneButtonListener(new DoneButtonListener()); // submit same as text
	}

	private class SaveToListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			mainController.initCheckoutController(new CheckoutController(
//					nextController, mainController));
			PopupControllerNew popup = new PopupControllerNew(mainController);
			
		}
	}

	private class DoneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("clicked cancel");
		}
	}

	@Override
	public JPanel getView() {
		return this.view;
	}

}
