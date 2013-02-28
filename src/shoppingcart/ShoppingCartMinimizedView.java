package shoppingCart;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;

public class ShoppingCartMinimizedView extends JPanel {

	private JButton listButton; 
	private JButton shoppingcartIconButton;
	private JButton maximizeLabelButton;
	/**
	 * Create the panel.
	 */
	public ShoppingCartMinimizedView() {
		setOpaque(false);
		setLayout(null);
		
		listButton = new JButton();
		listButton.setBorder(null);
		listButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listButton.setFocusable(false);
		listButton.setBackground(new Color(236, 236, 236));
		listButton.setBounds(102, 0, 26, 720);
		
		shoppingcartIconButton = new JButton(new ImageIcon("img/shoppingcartIcon.png"));
		shoppingcartIconButton.setBorder(null);
		shoppingcartIconButton.setContentAreaFilled(false);
		shoppingcartIconButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		shoppingcartIconButton.setBounds(64, 39, 61, 61);
		
		maximizeLabelButton = new JButton(new ImageIcon("img/shoppingcartMinimizedLabel.png"));
		maximizeLabelButton.setBorder(null);
		maximizeLabelButton.setContentAreaFilled(false);
		maximizeLabelButton.setOpaque(false);
		maximizeLabelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		maximizeLabelButton.setBounds(106, 283, 16, 154);
		
		add(shoppingcartIconButton);
		add(maximizeLabelButton);
		add(listButton);		
	}
	
	public void addListListener(ActionListener l) {
		shoppingcartIconButton.addActionListener(l);
		listButton.addActionListener(l);
		maximizeLabelButton.addActionListener(l);
	}

}
