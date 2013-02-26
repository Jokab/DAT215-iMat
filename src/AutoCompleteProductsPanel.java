import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import se.chalmers.ait.dat215.project.Product;
import java.awt.Color;


public class AutoCompleteProductsPanel extends JPanel {
	private final JLabel image = new JLabel("No Image");
	private final JLabel name = new JLabel("Product Name");
	private final JLabel price = new JLabel("Price");

	/**
	 * Create the panel.
	 */
	public AutoCompleteProductsPanel(Product product) {
		setBackground(Color.CYAN);
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(image)
					.addGap(40)
					.addComponent(name)
					.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
					.addComponent(price, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(image)
						.addComponent(name)
						.addComponent(price))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		name.setText(product.getName());
		price.setText(Double.toString(product.getPrice()));

	}

	public JLabel getImageLabel() {
		return image;
	}

	public JLabel getNameLabel() {
		return this.name;
	}

	public JLabel getPriceLabel() {
		return price;
	}
}
