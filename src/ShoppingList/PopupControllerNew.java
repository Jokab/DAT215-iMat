package ShoppingList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import core.MainController;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * A controller for a popup which handles when the user wants to create
 * a new list "from scratch". The controller takes care of saving the 
 * list itself, so there is no need to listen to or extract the list from
 * the controller.
 * 
 * @author Jakob
 *
 */
public class PopupControllerNew {

	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);
	private final Color SAVEBUTTON_GRAYED_BG = new Color(235,235,235);
	private final Color SAVEBUTTON_GRAYED_TEXT = Color.WHITE;
	
	private final JTextField textField;
	private final ShoppingListPopupNew popup;
	private final JButton saveButton;
	private final JButton cancelButton;
	private final JTextArea warningLabel;
	
	private String[] listNames;
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private PopupListEntry selected;
	private MainController mainController;
	
	private ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private Set<ShoppingList> lists;
	
	public PopupControllerNew(MainController mainController) {
		
		this.popup = new ShoppingListPopupNew();
		this.mainController = mainController;
		this.mainController.showPopup(popup);
		
		this.textField = popup.getTextField();
		
		this.warningLabel = popup.getWarningLabel();
		
		this.saveButton = popup.getSaveButton();
		this.saveButton.addActionListener(new SaveButtonClicked());
		
		this.cancelButton = popup.getCancelButton();
		this.cancelButton.addActionListener(new CancelButtonClicked());
		
		/** Adds a listener for user input, which activates in real-time. **/
		textField.getDocument().addDocumentListener(new TextFieldListener());
		
		/** Load the lists and "cache" them for faster use. **/
		handler.readLists();
		lists = handler.getShoppingLists();
		storeListNames();
	}
	
	private class TextFieldListener implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			update();
		}

		public void removeUpdate(DocumentEvent e) {
			update();
		}

		public void insertUpdate(DocumentEvent e) {
			update();
		}

		/**
		 * Updates the components in different ways depending on what the user has entered.
		 * If user enters the name of a <code>ShoppingList</code> that already exists, the text
		 * changes to warn the user that it will overwrite if saved.
		 */
		public void update() {
			if (textField.getText().equals("") || textField.getText().isEmpty()) {
				saveButton.setBackground(SAVEBUTTON_GRAYED_BG);
				saveButton.setForeground(SAVEBUTTON_GRAYED_TEXT);
				saveButton.setEnabled(false);
			} else {
				saveButton.setEnabled(true);
				saveButton.setBackground(SELECTED_BG_COLOR);
				saveButton.setForeground(SELECTED_TEXT_COLOR);
			}

			if (getMatchingName() != null) {
				setWarningLabelText(getMatchingName());
				warningLabel.setVisible(true);
			} else {
				warningLabel.setVisible(false);
			}
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
			saveButtonClicked();
		}
	}
	


	/**
	 * Sets the text of the warning label to what the user entered, warning him/her that 
	 * the stored list will be overwritten if saved.
	 * 
	 * @param listName The name of the stored list which matches what the user inputed. 
	 */
	private void setWarningLabelText(String listName) {
		warningLabel
				.setText("En lista med namnet: "
						+ listName
						+ "    existerar redan. Välj ett annat namn eller tryck spara för att skriva över.");
	}
	
	/**
	 * Stores the names of the list in a String-array, which allows for faster loading.
	 */
	private void storeListNames() {
		
		// Bad way of solving this, but I couldn't get Collections.toArray to work for some reason. 
		listNames = new String[lists.size()];
		int i = 0;
		for (ShoppingList list : lists) {
			listNames[i] = list.getName();
			i++;
		}
	}

	/**
	 * Searches through the stored lists to see if what the user inputed matches
	 * the name of a stored list.
	 * 
	 * @return The name of the matching stored list, if any.
	 */
	private String getMatchingName() {
		for (String s : listNames) {
			if (textField.getText().equals(s)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Save button has been clicked, so either store the inputed text as a new
	 * list, or delete the matching one and add a new one with the inputed name.
	 */
	private void saveButtonClicked() {
		String inputName = textField.getText();
		
		if (getMatchingName() == null) {
			handler.addShoppingList(new ShoppingList(new LinkedList<ShoppingItem>(), inputName));
		} else {
			handler.removeShoppingList(textField.getText());
			handler.addShoppingList(new ShoppingList(new LinkedList<ShoppingItem>(), inputName));
		}
		exitPopup();
	}
	
	private void exitPopup() {
		mainController.removePopup();
	}
	
	public void addObserver(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}	
}
