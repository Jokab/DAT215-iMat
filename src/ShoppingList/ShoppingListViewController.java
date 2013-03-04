package ShoppingList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import core.MainController;
import core.ViewController;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingCart.ShoppingCartAdapter;

public class ShoppingListViewController implements ViewController,
		PropertyChangeListener {

	private ShoppingListView view;
	private static final ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private ShoppingListEntry activeEntryPanel = null;

	private final MainController mainController;

	private static final IMatDataHandler dm = IMatDataHandler.getInstance();

	public ShoppingListViewController(MainController mController) {
		this.mainController = mController;
		this.view = new ShoppingListView();
		this.view.addRemoveButtonActionListener(new RemoveButtonListener());
		this.view.addNewListButtonActionListener(new NewListButtonListener());
		this.view.addNewAddToCartButtonListener(new AddToCartButtonListener());
		updateListView();
	}

	private void updateListView() {
		view.getPanel().removeAll();
		view.getPanel().repaint();

		handler.readLists();
		Set<ShoppingList> lists = handler.getShoppingLists();

		for (ShoppingList l : lists) {
			ShoppingListEntry entry = new ShoppingListEntry(l);
			entry.addEntryMouseListener(new EntryClickedListener());
			view.getPanel().add(entry);
		}
		view.getPanel().revalidate();
	}

	private class EntryClickedListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent evt) {
			if (activeEntryPanel != null) {
				activeEntryPanel.setInactive();
			}
			activeEntryPanel = (ShoppingListEntry) evt.getSource();
			activeEntryPanel.setActive();
			updateDetailedPanel(activeEntryPanel.getShoppingList());
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			((ShoppingListEntry) e.getSource()).toggle();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((ShoppingListEntry) e.getSource()).toggle();
		}

	}

	private class RemoveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ShoppingList list = ShoppingListViewController.this.activeEntryPanel
					.getShoppingList();
			handler.removeShoppingList(list.getName());
			handler.writeLists(); // necessary to be able to retrieve lists for
									// repaint

			updateListView();
			updateHeaderText(null);
			view.getRemoveButton().setEnabled(false);
			view.getDetailedPanel().removeAll();
		}

	}

	private class AddToCartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ShoppingList list = ShoppingListViewController.this.activeEntryPanel
					.getShoppingList();
			ShoppingCartAdapter.getInstance().addItems(list.getItems());

		}
	}

	private class NewListButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			PopupControllerNew controller = new PopupControllerNew(
					mainController);
			controller.addObserver(ShoppingListViewController.this);
		}

	}

	private void updateDetailedPanel(ShoppingList shoppingList) {
		view.getDetailedPanel().removeAll();

		updateHeaderText(shoppingList);
		JPanel detailedPanel = view.getDetailedPanel();
		List<ShoppingItem> items = shoppingList.getItems();
		if (items != null && items.size() > 0) {
			for (ShoppingItem item : items) {
				detailedPanel.add(new ShoppingListProductPanel(item));
			}
		} else {
			// TODO: Add a panel that says "list is empty"
		}
		view.showRightPanel();
		view.getDetailedPanel().revalidate();
	}

	// TODO: Is this not used? I don't know.
	private void checkRemoveButtonEnabled() {
		if (!(view.getPanel().getComponentCount() <= 1)) {
			view.getRemoveButton().setEnabled(true);
		} else {
			view.getRemoveButton().setEnabled(false);
		}
	}

	private void updateHeaderText(ShoppingList list) {
		if (list == null) {
			view.getHeaderNameLabel().setText("");
		} else {
			view.getHeaderNameLabel().setText(
					ShoppingListViewController.this.activeEntryPanel
							.getShoppingList().getName());
		}
	}

	@Override
	public JPanel getView() {
		return this.view;
	}

	/*
	 * Listens to PopupControllerNew to see if content has been saved, if so
	 * update the list
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		updateListView();
	}

}
