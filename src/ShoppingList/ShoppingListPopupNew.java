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
	private String[] listNames;

	public ShoppingListPopupNew() {
		handler.readLists();
		lists = handler.getShoppingLists();
		storeListNames();

		textField.setBounds(12, 32, 307, 19);
		textField.setColumns(10);
		setLayout(null);

		add(textField);
		lblAngeNamn.setBounds(12, 12, 74, 15);

		add(lblAngeNamn);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShoppingListPopupNew.this.setVisible(false);
			}
		});
		cancelButton.setBounds(212, 103, 107, 25);

		add(cancelButton);
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveButtonClicked();
			}
		});
		saveButton.setBounds(93, 103, 107, 25);

		add(saveButton);
		warningLabel.setEditable(false);
		warningLabel.setLineWrap(true);
		warningLabel.setOpaque(false);
		warningLabel.setForeground(Color.RED);
		warningLabel.setBounds(12, 53, 307, 37);

		add(warningLabel);

		textField.getDocument().addDocumentListener(new DocumentListener() {
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
					saveButton.setEnabled(false);
				} else {
					saveButton.setEnabled(true);
				}

				if (getMatchingName() != null) {
					setWarningLabelText(getMatchingName());
					warningLabel.setVisible(true);
				} else {
					warningLabel.setVisible(false);
				}
			}
		});
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
	}

	private static class Main extends JFrame {
		public Main() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setPreferredSize(new Dimension(1190, 700));

			add(new ShoppingListPopupNew());
			pack();
		}

		public static void main(String[] args) {
			new Main();
		}
	}
}
