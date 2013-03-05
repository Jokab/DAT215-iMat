package shoppingCart;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

import javax.swing.JButton;
import core.MainController;
import ShoppingList.PopupListEntry;
import ShoppingList.ShoppingList;
import ShoppingList.ShoppingListHandler;
import ShoppingList.ShoppingListPopupSave;

/**
 * @author Jakob
 *
 */
public class AddListToCartPopupController implements PropertyChangeListener {
	
	private ShoppingListPopupSave popup;
	private JButton saveButton;
	private JButton cancelButton;
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private PopupListEntry selected;
	private MainController mainController;
	
	private ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private ShoppingCartAdapter cart = ShoppingCartAdapter.getInstance();
	private Set<ShoppingList> lists;

	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);
	private final Color SAVEBUTTON_GRAYED_BG = new Color(235,235,235);
	private final Color SAVEBUTTON_GRAYED_TEXT = Color.WHITE;
	
	private final String CONFIRM_BUTTON_TEXT = "L�gg till";
	private final String HEADER_TEXT = "V�lj en lista att l�gga till fr�n:";
	
	
	public AddListToCartPopupController(MainController mainController) {
		
		this.popup = new ShoppingListPopupSave(CONFIRM_BUTTON_TEXT, HEADER_TEXT, false);
		this.mainController = mainController;
		this.mainController.showPopup(popup);
		
		this.saveButton = popup.getSaveButton();
		this.saveButton.addActionListener(new AddButtonClicked());
		
		this.cancelButton = popup.getCancelButton();
		this.cancelButton.addActionListener(new CancelButtonClicked());
		
		initListPanels();
	}
	
	
	private void initListPanels() {
		/** Load the shoppinglists. **/
		handler.readLists();
		lists = handler.getShoppingLists();
		popup.getListPanel().removeAll();
		popup.getListPanel().revalidate();

		
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
			addButtonClicked(selected);
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

	private void addButtonClicked(PopupListEntry entry) {		
		
		ShoppingList selectedList = entry.getShoppingList();
		if(selectedList != null && selectedList.getItems() != null && selectedList.getItems().size() > 0) {
			cart.addShoppingList(selectedList);
		}
		exitPopup();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		mainController.showPopup(this.popup);
		initListPanels();
	}
	
	


}
