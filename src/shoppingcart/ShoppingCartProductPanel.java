package shoppingCart;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

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
		double amount = item.getAmount();
		amountLabel = new JLabel(amount + "st");
		amountLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		productLabel = new JLabel(item.getProduct().getName());
		productLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		priceLabel = new JLabel(amount * item.getProduct().getPrice() + "kr");
		priceLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(amountLabel)
					.addGap(18)
					.addComponent(productLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(priceLabel)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(264, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(amountLabel)
							.addComponent(productLabel)
							.addComponent(priceLabel)))
					.addContainerGap())
		);
		
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
		setLayout(groupLayout);
		
	}
}
