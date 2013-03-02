package shoppingCart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

import javax.swing.JButton;

import se.chalmers.ait.dat215.project.Product;
import core.MainController;

import ShoppingList.PopupListEntry;
import ShoppingList.ShoppingList;
import ShoppingList.ShoppingListHandler;
import ShoppingList.ShoppingListPopupSave;
import ShoppingList.PopupControllerSave.EntryMouseListener;

public class AddShoppingListPopUp {
	
	private ShoppingListPopupSave popup;
	private JButton addButton;
	private JButton cancelButton;
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private PopupListEntry selected;
	private MainController mainController;
	
	private ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private Set<ShoppingList> lists;
	
	
	public AddShoppingListPopUp(){
		
	}
	
	
	private void initListPanels() {
		for(ShoppingList list : lists) {
			PopupListEntry entry = new PopupListEntry(list);
			entry.addOwnMouseListener(new EntryMouseListener());
			popup.getListPanel().add(entry);		
		}
	}
	
	private class CancelButtonClicked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) {
			exitPopup();
		}	
	}
	
	private class AddButtonClicked implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent evt) {
			System.out.println("addbutton clicked");
			//TODO: Code below but with the selected list as param
			//ShoppingCartAdapter.getInstance().addShoppingList(selected.getShoppingList())));
		}
	}
	
	private class EntryMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent evt) {
			if(selected != null) {
				selected.setBackground(NORMAL_BG_COLOR);
				selected.setForeground(NORMAL_TEXT_COLOR);
			}
			popup.getSaveButton().setEnabled(true);
			saveButton.setBackground(SELECTED_BG_COLOR);
			saveButton.setForeground(SELECTED_TEXT_COLOR);
			
			PopupListEntry entry = (PopupListEntry)evt.getSource();
			selected = entry;
			entry.setBackground(SELECTED_BG_COLOR);
			entry.setForeground(SELECTED_TEXT_COLOR);
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	private void exitPopup() {
		mainController.removePopup();
	}
	
	private void saveButtonClicked(PopupListEntry entry) {		
		System.out.println("firing");
		pcs.firePropertyChange("Savebutton clicked", this.attachedProduct, entry.getShoppingList());
	}
	
	public void addObserver(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
}
