package SearchBar;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;



public class SearchBar extends JPanel {
	private final JTextField searchField = new JTextField();
	
	private SearchController controller;

	/**
	 * Create the panel.
	 */
	public SearchBar() {
		setOpaque(false);
		
		JButton button = new JButton(new ImageIcon("img/searchButton.png"));
		button.setContentAreaFilled(false);
		button.setBounds(309, 0, 40, 26);
		button.setBorder(new MatteBorder(1, 0, 1, 1, (Color) new Color(204, 204, 204)));
		add(button);
		
		searchField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(204, 204, 204)));
		searchField.setFont(new Font("Calibri", Font.PLAIN, 12));
		searchField.setBounds(0, 0, 310, 26);
		searchField.setText("  S\u00F6k produkt...");
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
}
