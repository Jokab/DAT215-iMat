package SearchBar;


import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;


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

	/**
	 * Create the panel.
	 */
	public AutoCompleteProductsPanel(Product product) {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackground(Color.WHITE);
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(name, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
					.addGap(31)
					.addComponent(price, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(price)
								.addComponent(name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(image, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addContainerGap())))
		);
		setLayout(groupLayout);
		
		image.setIcon(dh.getImageIcon(product,new Dimension(28,28)));
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

	public void addAutoCompMouseListener(MouseListener listener) {
		addMouseListener(listener);
	}
}
