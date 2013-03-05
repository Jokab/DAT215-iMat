package ShoppingList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

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
		List<ShoppingList> sortedList = sortSetAsList(lists);

		for (ShoppingList l : sortedList) {
			ShoppingListEntry entry = new ShoppingListEntry(l);
			entry.addEntryMouseListener(new EntryClickedListener());
			view.getPanel().add(entry);
		}
		view.getPanel().revalidate();
	}
	
	private List<ShoppingList> sortSetAsList(Set<ShoppingList> set) {
		List<ShoppingList> listsList = new LinkedList<ShoppingList>();
		listsList.addAll(set);
		Collections.sort(listsList, new OrderShoppingListsAlphabetically());
		return listsList;
	}

	private class EntryClickedListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent evt) {
			if (activeEntryPanel != null) {
				activeEntryPanel.setInactive();
			}
			activeEntryPanel = (ShoppingListEntry) evt.getSource();
			activeEntryPanel.setActive();
			checkRemoveButtonEnabled();
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
			view.getDetailedPanel().repaint();
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
		view.getDetailedPanel().repaint();

		updateHeaderText(shoppingList);
		JPanel detailedPanel = view.getDetailedPanel();
		List<ShoppingItem> items = shoppingList.getItems();
		if (items != null && items.size() > 0) {
			for (ShoppingItem item : items) {
				ShoppingListProductPanel productPanel = new ShoppingListProductPanel(
						item);
				productPanel.addObserver(ShoppingListViewController.this);
				detailedPanel.add(productPanel);
			}
		} else {
			// TODO: Add a panel that says "list is empty"
		}
		view.showRightPanel();
		view.getDetailedPanel().revalidate();
	}

	private void checkRemoveButtonEnabled() {
		if (!(view.getPanel().getComponentCount() <= 0)) {
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
	 * update the list.
	 * 
	 * Also listens to the associated ShoppingListProductPanelfor increasing and
	 * removing products from a list, taking appropriate action depending on
	 * which was clicked.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (activeEntryPanel != null) {
			ShoppingList activeShoppingList = activeEntryPanel
					.getShoppingList();
			if (evt.getPropertyName().equals("increased")) {
				activeShoppingList.increaseItemAmount((ShoppingItem) evt
						.getNewValue());
			}
			if (evt.getPropertyName().equals("decreased")) {
				activeShoppingList.decreaseItemAmount((ShoppingItem) evt
						.getNewValue());
			}
			if (evt.getPropertyName().equals("removed")) {
				activeShoppingList.removeItem((ShoppingItem) evt.getNewValue());
			}
			updateDetailedPanel(activeShoppingList);
		}

		handler.writeLists();

		updateListView();

	}

}
