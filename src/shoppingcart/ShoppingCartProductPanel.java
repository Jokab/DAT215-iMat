package shoppingcart;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class ShoppingCartProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel amountLabel;
	JLabel productLabel;
	JLabel priceLabel;
	
	public ShoppingCartProductPanel(ShoppingItem item) {
		double amount = item.getAmount();
		amountLabel = new JLabel(amount + "st");
		productLabel = new JLabel(item.getProduct().getName());
		priceLabel = new JLabel(amount * item.getProduct().getPrice() + "kr");
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(amountLabel)
					.addGap(18)
					.addComponent(productLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
					.addComponent(priceLabel)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountLabel)
						.addComponent(productLabel)
						.addComponent(priceLabel))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}
}
