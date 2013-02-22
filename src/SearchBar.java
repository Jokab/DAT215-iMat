import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.Color;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;



public class SearchBar extends JPanel {
	private final JTextField searchField = new JTextField();
	
	private SearchController controller;
	private final JPanel panel = new JPanel();

	/**
	 * Create the panel.
	 */
	public SearchBar() {
		panel.setBackground(Color.WHITE);
		this.controller = new SearchController(this);
		searchField.setText("S\u00F6k produkt...");
		searchField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
						.addComponent(searchField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
					.addGap(123))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(381))
		);
		panel.setVisible(false);
		panel.setLayout(new GridLayout(0,1));
		panel.setPreferredSize(new Dimension(0,1));
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
