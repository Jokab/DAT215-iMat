package ShoppingList;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;

import core.MainController;

import se.chalmers.ait.dat215.project.Product;

public class PopupControllerSave {

	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);
	private final Color SAVEBUTTON_GRAYED_BG = new Color(235,235,235);
	private final Color SAVEBUTTON_GRAYED_TEXT = Color.WHITE;
	
	private Product attachedProduct;
	private ShoppingListPopupSave popup;
	private JButton saveButton;
	private JButton cancelButton;
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private PopupListEntry selected;
	private MainController mainController;
	
	private ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private Set<ShoppingList> lists;
	
	public PopupControllerSave(Product p, MainController mainController) {
		this.attachedProduct = p;
		this.popup = new ShoppingListPopupSave();
		this.mainController = mainController;
		this.mainController.showPopup(popup);
		
		this.saveButton = popup.getSaveButton();
		this.saveButton.addActionListener(new SaveButtonClicked());
		
		this.cancelButton = popup.getCancelButton();
		this.cancelButton.addActionListener(new CancelButtonClicked());
		
		handler.readLists();
		lists = handler.getShoppingLists();
		initListPanels();
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
	
	private class SaveButtonClicked implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent evt) {
			System.out.println("savebutton clicked");
			saveButtonClicked(selected);
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