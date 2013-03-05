package ShoppingList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;

import java.beans.PropertyChangeSupport;

public class ShoppingListPopupSave extends JPanel {


	private final JLabel lblAngeNamn = new JLabel("Ange namn:");
	private final JButton cancelButton = new JButton("Avbryt");
	private final JButton saveButton = new JButton("Spara");
	private String[] listNames;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel listPanel = new JPanel();
	private final JLabel chooseListLabel = new JLabel("");
	
	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);
	private final Color SAVEBUTTON_GRAYED_BG = new Color(235,235,235);
	private final Color SAVEBUTTON_GRAYED_TEXT = Color.WHITE;
	
	private final boolean showNewListButton;


	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final JButton newListButton = new JButton("Skapa ny lista");
	

	public ShoppingListPopupSave(String confirmButtonText, String headerText, boolean showNewListButton) {
		setSize(new Dimension(350, 300));
		setPreferredSize(new Dimension(330, 275));
		setMinimumSize(new Dimension(350, 300));
		setMaximumSize(new Dimension(350, 300));

		setLayout(null);
		setBackground(Color.WHITE);
		listPanel.setBackground(Color.WHITE);
		chooseListLabel.setForeground(NORMAL_TEXT_COLOR);
		
		if(confirmButtonText != null && confirmButtonText.length() > 0) {
			saveButton.setText(confirmButtonText);
		}
		saveButton.setBackground(SAVEBUTTON_GRAYED_BG);
		saveButton.setForeground(SAVEBUTTON_GRAYED_TEXT);
		
		cancelButton.setBackground(NORMAL_TEXT_COLOR);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBounds(212, 233, 107, 25);
		add(cancelButton);
		
		saveButton.setBounds(93, 233, 107, 25);
		saveButton.setEnabled(false);
		add(saveButton);
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 39, 307, 185);
		
		add(scrollPane);
		listPanel.setSize(new Dimension(200, 150));
		listPanel.setMinimumSize(new Dimension(200, 150));
		listPanel.setPreferredSize(new Dimension(200, 150));
		
		scrollPane.setViewportView(listPanel);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		chooseListLabel.setBounds(12, 12, 171, 15);
		chooseListLabel.setText(headerText);
		
		this.showNewListButton = showNewListButton;
		initNewListButton();
		add(chooseListLabel);

	}

	private void initNewListButton() {
		if(showNewListButton) {
			newListButton.setBounds(202, 8, 115, 23);
			newListButton.setBackground(SELECTED_BG_COLOR);
			newListButton.setForeground(SELECTED_TEXT_COLOR);
			add(newListButton);
		}
	}

	public JButton getSaveButton() {
		return saveButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public JPanel getListPanel() {
		return listPanel;
	}
	public JButton getNewListButton() {
		return newListButton;
	}
}
