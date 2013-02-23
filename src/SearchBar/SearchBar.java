package SearchBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.Color;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Dimension;

import java.awt.GridLayout;



/**
 * A search bar-view for displaying auto-complete results.
 * 
 * @author Jakob
 *
 */
public class SearchBar extends JPanel {
	private final JTextField searchField = new JTextField();
	
	private SearchController controller;
	private final JPanel panel = new JPanel();

	/**
	 * Create the panel.
	 */
	public SearchBar() {
		setBackground(Color.ORANGE);
		panel.setVisible(false);
		panel.setBackground(Color.WHITE);
		
		this.controller = new SearchController(this);
		
		searchField.setText("S\u00F6k produkt...");
		searchField.setColumns(10);
		panel.setLayout(new GridLayout(0,1));
		panel.setPreferredSize(new Dimension(0,1));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
					.addGap(124))
		);
		setLayout(groupLayout);
		
		// Listens to changes in the textfield text
		searchField.getDocument().addDocumentListener(new DocumentListener() {
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
				 getPanel().setVisible(false);
			     controller.updateAutoCompletePanel(getSearchField());
			     if(!(getSearchField().getText().isEmpty())) {
			     	getPanel().setVisible(true);
			     }
			  }
		});

	}

	public void addBarActionPerformedListener(ActionListener listener) {
		searchField.addActionListener(listener);
	}

	public void addFocusGainedListener(FocusListener listener) {
		searchField.addFocusListener(listener);
	}
	
	public JTextField getSearchField() {
		return this.searchField;
	}

	public JPanel getPanel() {
		return panel;
	}
}
