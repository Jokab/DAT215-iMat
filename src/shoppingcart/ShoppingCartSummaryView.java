package shoppingCart;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JSeparator;
import javax.swing.JButton;

public class ShoppingCartSummaryView extends JPanel implements PropertyChangeListener {

	private JLabel amountLabel;
	private JLabel priceLabel;
	private ShoppingCartAdapter model;
	/**
	 * Create the panel.
	 */
	public ShoppingCartSummaryView() {
		setOpaque(false);
		setLayout(null);
		model = ShoppingCartAdapter.getInstance();
		model.addListener(this);
		
		amountLabel = new JLabel();
		amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		amountLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		amountLabel.setBounds(7, 86, 203, 16);
		add(amountLabel);
		
		JLabel headerLabel = new JLabel("KUNDVAGN");
		headerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		headerLabel.setForeground(new Color(155, 155, 155));
		headerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		headerLabel.setBounds(83, 33, 127, 25);
		add(headerLabel);
		
		priceLabel = new JLabel();
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLabel.setForeground(new Color(169, 207, 109));
		priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		priceLabel.setBounds(7, 59, 203, 22);
		add(priceLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 113, 190, 2);
		add(separator);
		
		JButton showItemsButton = new JButton("visa varor");
		showItemsButton.setContentAreaFilled(false);
		showItemsButton.setBorder(null);
		showItemsButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		showItemsButton.setBounds(10, 126, 89, 23);
		showItemsButton.setForeground(new Color(184, 184, 184));
		add(showItemsButton);
		
		JButton toCounterButton = new JButton("till kassa");
		toCounterButton.setContentAreaFilled(false);
		toCounterButton.setBorder(null);
		toCounterButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		toCounterButton.setBounds(109, 126, 89, 23);
		toCounterButton.setForeground(new Color(184, 184, 184));
		add(toCounterButton);

	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		priceLabel.setText(model.getTotal() + "kr ");
		amountLabel.setText(model.getTotal() + "st produkter");
		
	}
}
