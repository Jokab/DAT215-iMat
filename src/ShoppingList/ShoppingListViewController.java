package ShoppingList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import core.MainController;
import core.ViewController;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingCart.ShoppingCartAdapter;

public class ShoppingListViewController implements ViewController {
	
	private ShoppingListView view;
	private static final ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private ShoppingListEntry activeEntryPanel = null;
	
	private final MainController mainController;
	
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
		handler.readLists();
		view.getPanel().repaint();
		
		IMatDataHandler dm = IMatDataHandler.getInstance();

		ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
		
		handler.readLists(); 
		Set<ShoppingList> lists = handler.getShoppingLists();

		for(ShoppingList l : lists) {
			ShoppingListEntry entry = new ShoppingListEntry(l);
			entry.addEntryMouseListener(new EntryClickedListener());
			view.getPanel().add(entry);
		}
		view.getPanel().revalidate();
	}
	
	private class EntryClickedListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent evt) {
			if(activeEntryPanel != null) {
				activeEntryPanel.setInactive();
			}
			activeEntryPanel = (ShoppingListEntry)evt.getSource();
			activeEntryPanel.setActive();
			updateDetailedPanel(activeEntryPanel.getShoppingList());
		}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {
			((ShoppingListEntry)e.getSource()).toggle();
		}
		@Override
		public void mouseExited(MouseEvent e) {
			((ShoppingListEntry)e.getSource()).toggle();
		}
		
	}
	
	private class RemoveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
			ShoppingList list = ShoppingListViewController.this.activeEntryPanel.getShoppingList();
			handler.removeShoppingList(list.getName());
			handler.writeLists();		// necessary to be able to retrieve lists for repaint

			updateListView();
			updateHeaderText(null);
			view.getRemoveButton().setEnabled(false);
			view.getDetailedPanel().removeAll();
		}
		
	}
	
	private class AddToCartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ShoppingList list = ShoppingListViewController.this.activeEntryPanel.getShoppingList();
			ShoppingCartAdapter.getInstance().addItems(list.getItems());
			
		}
	}
	
	private class NewListButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			PopupControllerNew controller = new PopupControllerNew(mainController);
		}
		
	}
	
	private void updateDetailedPanel(ShoppingList shoppingList) {
		view.getDetailedPanel().removeAll();
		
		updateHeaderText(shoppingList);
		JPanel detailedPanel = view.getDetailedPanel();
		for(ShoppingItem item : shoppingList.getItems()) {
			detailedPanel.add(new ShoppingListProductPanel(item));
		}
		view.showRightPanel();
		view.getDetailedPanel().revalidate();
	}

	private void updateHeaderText(ShoppingList list) {
		if(list == null) {
			view.getHeaderNameLabel().setText("");
		} else {
			view.getHeaderNameLabel().setText(ShoppingListViewController.this.activeEntryPanel.getShoppingList().getName());
		}
	}

	@Override
	public JPanel getView() {
		return this.view;
	}
	
	
	
}
