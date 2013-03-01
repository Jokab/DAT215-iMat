package ShoppingList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
		handler.readLists();
		lists = handler.getShoppingLists();
		storeListNames();
		
		this.popup = new ShoppingListPopupNew();
		this.mainController = mainController;
		this.mainController.showPopup(popup);
		
		this.textField = popup.getTextField();
		
		this.warningLabel = popup.getWarningLabel();
		
		this.saveButton = popup.getSaveButton();
		this.saveButton.addActionListener(new SaveButtonClicked());
		
		this.cancelButton = popup.getCancelButton();
		this.cancelButton.addActionListener(new CancelButtonClicked());
		
		textField.getDocument().addDocumentListener(new TextFieldListener());
			
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
			System.out.println("savebutton clicked");
			saveButtonClicked();
		}
	}
	
	private void storeListNames() {
		listNames = new String[lists.size()];
		int i = 0;
		for (ShoppingList list : lists) {
			listNames[i] = list.getName();
			i++;
		}
	}

	private void setWarningLabelText(String list) {
		warningLabel
				.setText("En lista med namnet: "
						+ list
						+ "    existerar redan. Välj ett annat namn eller tryck spara för att skriva över.");
	}

	private String getMatchingName() {
		for (String s : listNames) {
			if (textField.getText().equals(s)) {
				return s;
			}
		}
		return null;
	}
	
	private void saveButtonClicked() {
		String inputName = textField.getText();
		
		if (getMatchingName() == null) {
			handler.addShoppingList(new ShoppingList(inputName));
		} else {
			handler.removeShoppingList(textField.getText());
			handler.addShoppingList(new ShoppingList(inputName));
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
