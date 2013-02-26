package shoppingCart;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;

public class ShoppingcartView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ShoppingcartView() {
		setOpaque(false);
		setLayout(null);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(103, 0, 220, 720);
		add(contentPanel);
		
		JPanel listPanel = new JPanel();
		listPanel.setFocusable(false);
		listPanel.setBackground(new Color(236, 236, 236));
		listPanel.setBounds(78, 0, 25, 720);
		add(listPanel);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("img/shoppingcartIcon.png"));
		lblNewLabel.setBounds(42, 39, 61, 61);
		add(lblNewLabel);

	}
}
