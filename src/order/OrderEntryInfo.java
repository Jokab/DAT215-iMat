package order;

import javax.swing.JPanel;
import javax.swing.JLabel;

import se.chalmers.ait.dat215.project.Order;

import java.awt.Color;
import java.awt.Dimension;

public class OrderEntryInfo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3512891505977682633L;
	private final JLabel listPrice = new JLabel("New label");
	private final JLabel listNbrItems = new JLabel("New label");

	/**
	 * Create the panel.
	 * @param order 
	 */
	public OrderEntryInfo(Order order) {
		setMaximumSize(new Dimension(90, 40));
		listPrice.setText(Double.toString(OrderUtil.getTotal(order)) + " kr");
		listNbrItems.setText(Integer.toString(order.getItems().size()) + " st");
		
		setMinimumSize(new Dimension(90, 40));
		setPreferredSize(new Dimension(90, 40));
		setSize(new Dimension(90, 40));
		setBackground(new Color(255, 255, 204));
		setLayout(null);
		listPrice.setBounds(12, 0, 61, 15);
		
		add(listPrice);
		listNbrItems.setBounds(12, 22, 61, 15);
		
		add(listNbrItems);
		
		

	}

}
