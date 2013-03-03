package order;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import se.chalmers.ait.dat215.project.Order;

import java.awt.Font;

public class OrderEntryInfo extends JPanel {
	/**
	 * 
	 */
	private final JLabel listPrice = new JLabel();
	private final JLabel listNbrItems = new JLabel();

	private static final Font DEFAULT_TEXT_FONT = new Font("Calibri", Font.PLAIN, 10);
	/**
	 * Create the panel.
	 * @param list 
	 */
	public OrderEntryInfo(Order order) {
		setMaximumSize(new Dimension(90, 40));
		listPrice.setFont(DEFAULT_TEXT_FONT);
		listPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		listPrice.setText(Double.toString(OrderUtil.getTotal(order)) + " kr");
		
		listNbrItems.setFont(DEFAULT_TEXT_FONT);
		listNbrItems.setHorizontalAlignment(SwingConstants.RIGHT);
		listNbrItems.setText(Integer.toString(order.getItems().size()) + " st");
		setLayout(null);
		listPrice.setBounds(10, 0, 61, 15);
		
		add(listPrice);
		listNbrItems.setBounds(10, 11, 61, 15);
		
		add(listNbrItems);		
	}
	
	public void setTextColor(Color c) {
		listPrice.setForeground(c);
		listNbrItems.setForeground(c);
	}
}
