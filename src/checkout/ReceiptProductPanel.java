package checkout;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class ReceiptProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel amountLabel;
	JLabel productLabel;
	JLabel priceLabel;
	
	public ReceiptProductPanel(ShoppingItem item) {
		setPreferredSize(new Dimension(280, 17));
		setMinimumSize(new Dimension(280, 17));
		setMaximumSize(new Dimension(280, 17));
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
					.addComponent(amountLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(productLabel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(priceLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountLabel)
						.addComponent(productLabel)
						.addComponent(priceLabel))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
