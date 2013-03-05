package SearchBar;


import javax.swing.JPanel;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.border.MatteBorder;
import java.awt.Font;


/**
 * A panel for displaying an individual result for autocomplete-search.
 * 
 * @author Jakob
 *
 */
public class AutoCompleteProductsPanel extends JPanel {
	private final static IMatDataHandler dh = IMatDataHandler.getInstance();
	private final JLabel image = new JLabel("");
	private final JLabel name = new JLabel("Product Name");
	private final JLabel price = new JLabel("Price");
	
	private final Product product;
	/**
	 * Create the panel.
	 */
	public AutoCompleteProductsPanel(Product product) {
		setMinimumSize(new Dimension(10, 60));
		setMaximumSize(new Dimension(32767, 60));
		setBorder(new MatteBorder(0, 1, 1, 1, new Color(204, 204, 204)));
		setBackground(Color.WHITE);
		
		this.product = product;
		price.setFont(new Font("Calibri", Font.PLAIN, 12));
		price.setForeground(new Color(150, 150, 150));
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		
		name.setForeground(new Color(150, 150, 150));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(name, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(27)
					.addComponent(price, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(price))
					.addGap(13))
				.addComponent(image, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
		);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Calibri", Font.PLAIN, 12));
		setLayout(groupLayout);
		
		image.setIcon(dh.getImageIcon(product,new Dimension(48,48)));
		name.setText(product.getName());
		price.setText(product.getPrice() + "kr");

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

	public void addAutoCompMouseListener(MouseListener listener) {
		addMouseListener(listener);
	}
	
	public Product getProduct() {
		return product;
	}
}
