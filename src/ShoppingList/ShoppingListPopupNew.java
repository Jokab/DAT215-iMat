package ShoppingList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShoppingListPopupNew extends JPanel {

	private ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private Set<ShoppingList> lists;

	private final JTextField textField = new JTextField();
	private final JLabel lblAngeNamn = new JLabel("Ange namn:");
	private final JButton cancelButton = new JButton("Avbryt");
	private final JButton saveButton = new JButton("Spara");
	private final JTextArea warningLabel = new JTextArea("");
	
	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);
	private final Color SAVEBUTTON_GRAYED_BG = new Color(235,235,235);
	private final Color SAVEBUTTON_GRAYED_TEXT = Color.WHITE;


	/**
	 * A popup for allowing the user to create a new <code>ShoppingList</code>.
	 * If the name entered is the name of an existing <code>ShoppingList</code>,
	 * that <code>ShoppingList</code> is overwritten with an empty one.
	 * 
	 * @author Jakob
	 */
	public ShoppingListPopupNew() {
		setOpaque(false);
		
		saveButton.setBackground(SAVEBUTTON_GRAYED_BG);
		saveButton.setForeground(SAVEBUTTON_GRAYED_TEXT);
		
		cancelButton.setBackground(NORMAL_TEXT_COLOR);
		cancelButton.setForeground(Color.WHITE);
		
		lblAngeNamn.setForeground(NORMAL_TEXT_COLOR);

		textField.setBounds(12, 32, 307, 19);
		textField.setColumns(10);
		setLayout(null);

		add(textField);
		lblAngeNamn.setBounds(12, 12, 74, 15);

		add(lblAngeNamn);
		
		cancelButton.setBounds(212, 103, 107, 25);

		add(cancelButton);
		saveButton.setEnabled(false);
		saveButton.setBounds(93, 103, 107, 25);

		add(saveButton);
		
		warningLabel.setEditable(false);
		warningLabel.setLineWrap(true);
		warningLabel.setOpaque(false);
		warningLabel.setForeground(Color.RED);
		warningLabel.setBounds(12, 53, 307, 37);

		add(warningLabel);
		setVisible(true);

	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	public JButton getSaveButton() {
		return saveButton;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextArea getWarningLabel() {
		return warningLabel;
	}
}
