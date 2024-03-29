package SearchBar;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.Color;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;



public class SearchBar extends JPanel {
	private final JTextField searchField = new JTextField();
	private final JButton searchButton = new JButton(new ImageIcon("img/searchButton.png"));
	private SearchController controller;

	/**
	 * Create the panel.
	 */
	public SearchBar() {
		setOpaque(false);
		
		searchButton.setContentAreaFilled(false);
		searchButton.setBounds(309, 0, 40, 26);
		searchButton.setBorder(new MatteBorder(1, 0, 1, 1, new Color(204, 204, 204)));
		add(searchButton);
		
		searchField.setBorder(new MatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
		searchField.setFont(new Font("Calibri", Font.PLAIN, 12));
		searchField.setBounds(0, 0, 310, 26);
		searchField.setText("S\u00F6k produkt...");
		searchField.setColumns(10);
		searchField.setForeground(new Color(125, 125, 125));
		setLayout(null);
		add(searchField);	
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
	
	public void addSearchButtonListener(ActionListener l) {
		searchButton.addActionListener(l);
	}
	public void addOutsideClickListener(MouseListener listener) {
		searchField.addMouseListener(listener);
	}
}
