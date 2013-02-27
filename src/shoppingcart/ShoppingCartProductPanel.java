package shoppingcart;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.Product;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ShoppingCartProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel amountLabel;
	JLabel productLabel;

	public ShoppingCartProductPanel(Product product) {
		amountLabel = new JLabel("2st");
		productLabel = new JLabel(product.getName());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(amountLabel)
					.addGap(18)
					.addComponent(productLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountLabel)
						.addComponent(productLabel))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}
}
