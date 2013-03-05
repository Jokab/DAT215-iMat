package order;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.Dimension;

public class OrderProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel amountLabel;
	private JLabel productLabel;
	private JLabel priceLabel;
	private JPanel panel;
	private JButton increaseButton;
	private JButton decreaseButton;
	private JButton deleteButton;
	private ShoppingItem item;
	
	private final Color NORMAL_TEXT_COLOR = new Color(140,140,140);

	public OrderProductPanel(ShoppingItem item) {
		this.item = item;
		
		setOpaque(false);
		setPreferredSize(new Dimension(446, 38));
		setMaximumSize(new Dimension(460, 38));
		setMinimumSize(new Dimension(460, 38));
		setBorder(new MatteBorder(0, 0, 1, 0, new Color(225, 225, 225)));
		
		double amount = this.item.getAmount();
		amountLabel = new JLabel(amount + " " + this.item.getProduct().getUnit().substring(3));
		amountLabel.setBounds(10, 13, 57, 14);
		amountLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		amountLabel.setForeground(NORMAL_TEXT_COLOR);
		
		productLabel = new JLabel(this.item.getProduct().getName());
		productLabel.setBounds(77, 13, 74, 14);
		productLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		productLabel.setForeground(NORMAL_TEXT_COLOR);
		
		priceLabel = new JLabel(amount * this.item.getProduct().getPrice() + "kr");
		priceLabel.setBounds(396, 13, 41, 14);
		priceLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLabel.setForeground(NORMAL_TEXT_COLOR);
		
		setLayout(null);
		add(amountLabel);
		add(productLabel);
		add(priceLabel);
	}
}
