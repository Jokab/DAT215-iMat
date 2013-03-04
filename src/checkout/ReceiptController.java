package checkout;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import shoppingCart.SaveAsShoppingListPopUpController;
import shoppingCart.ShoppingCartAdapter;

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
	private static final ShoppingCartAdapter cart = ShoppingCartAdapter.getInstance();
	private static final IMatDataHandler dm = IMatDataHandler.getInstance();

	public ReceiptController(MainController mainController) {
		this.mainController = mainController;
		this.view = new ReceiptPanel();
		this.view.addSaveToListListener(new SaveToListListener());
		this.view.addDoneButtonListener(new DoneButtonListener()); // submit same as text
	}

	private class SaveToListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SaveAsShoppingListPopUpController popup = new SaveAsShoppingListPopUpController(mainController);
		}
	}
		
	private class DoneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dm.placeOrder();
			cart.clear();
			mainController.initFrontPageController();
		}
	}

	@Override
	public JPanel getView() {
		return this.view;
	}

}
