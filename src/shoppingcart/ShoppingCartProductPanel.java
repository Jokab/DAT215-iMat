package shoppingcart;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class ShoppingCartProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel amountLabel;
	JLabel productLabel;
	JLabel priceLabel;
	private JPanel panel;
	private JButton increaseButton;
	private JButton decreaseButton;
	private JButton deleteButton;
	
	public ShoppingCartProductPanel(ShoppingItem item) {
		setSize(new Dimension(220, 30));
		setPreferredSize(new Dimension(220, 30));
		setMinimumSize(new Dimension(220, 30));
		setMaximumSize(new Dimension(220, 30));
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		double amount = item.getAmount();
		amountLabel = new JLabel(amount + " " + item.getProduct().getUnit());
		amountLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		productLabel = new JLabel(item.getProduct().getName());
		productLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		priceLabel = new JLabel(amount * item.getProduct().getPrice() + "kr");
		priceLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		panel = new JPanel();
		
		increaseButton = new JButton("+");
		increaseButton.setBorder(new EmptyBorder(3, 5, 3, 5));
		increaseButton.setForeground(Color.WHITE);
		increaseButton.setBackground(new Color(117, 211, 144));
		panel.add(increaseButton);
		
		decreaseButton = new JButton("-");
		decreaseButton.setActionCommand("-");
		decreaseButton.setForeground(Color.WHITE);
		decreaseButton.setBorder(new EmptyBorder(3, 5, 3, 5));
		decreaseButton.setBackground(new Color(211, 130, 114));
		panel.add(decreaseButton);
		
		deleteButton = new JButton("x");
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBorder(new EmptyBorder(3, 5, 3, 5));
		deleteButton.setBackground(new Color(211, 130, 114));
		deleteButton.setActionCommand("-");
		panel.add(deleteButton);
		
		add(amountLabel);
		add(productLabel);
		add(panel);
		add(priceLabel);
		
	}
}
