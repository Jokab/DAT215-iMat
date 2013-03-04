package shoppingCart;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class ShoppingCartSummaryView extends JPanel implements PropertyChangeListener {

	private JLabel amountLabel;
	private JLabel priceLabel;
	private ShoppingCartAdapter model;
	private JButton toCounterButton;
	private JButton showItemsButton;
	private final Color DEFAULT_TEXT_COLOR = new Color(155,155,155);
	/**
	 * Create the panel.
	 */
	public ShoppingCartSummaryView() {
		setOpaque(false);
		setLayout(null);
		model = ShoppingCartAdapter.getInstance();
		model.addListener(this);
		
		amountLabel = new JLabel("Inga produkter");
		amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		amountLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		amountLabel.setBounds(7, 86, 152, 16);
		amountLabel.setForeground(DEFAULT_TEXT_COLOR);
		add(amountLabel);
		
		JLabel headerLabel = new JLabel("KUNDVAGN");
		headerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		headerLabel.setForeground(DEFAULT_TEXT_COLOR);
		headerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		headerLabel.setBounds(25, 35, 134, 25);
		add(headerLabel);
		
		priceLabel = new JLabel("0");
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLabel.setForeground(new Color(169, 207, 109));
		priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		priceLabel.setBounds(18, 63, 126, 22);
		add(priceLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(41, 113, 169, 2);
		separator.setForeground(new Color(225, 225 ,225));
		separator.setBackground(new Color(236, 236 ,236));
		add(separator);
		
		showItemsButton = new JButton("visa varor");
		showItemsButton.setContentAreaFilled(false);
		showItemsButton.setBorder(null);
		showItemsButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		showItemsButton.setBounds(51, 117, 66, 23);
		showItemsButton.setForeground(new Color(184, 184, 184));
		add(showItemsButton);
		
		toCounterButton = new JButton("till kassa");
		toCounterButton.setContentAreaFilled(false);
		toCounterButton.setBorder(null);
		toCounterButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		toCounterButton.setBounds(127, 117, 66, 23);
		toCounterButton.setForeground(new Color(184, 184, 184));
		add(toCounterButton);
		
		JLabel lblKr = new JLabel("kr");
		lblKr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKr.setForeground(DEFAULT_TEXT_COLOR);
		lblKr.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblKr.setBounds(145, 69, 14, 16);
		add(lblKr);

	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		priceLabel.setText(model.getTotal() + "");
		amountLabel.setText(model.getNbrItems() + "st produkter");
		
	}
	
	public void addPopoutListener(ActionListener l) {
		showItemsButton.addActionListener(l);
	}
}
