package ShoppingList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ShoppingListEntryInfo extends JPanel {
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
	public ShoppingListEntryInfo(ShoppingList list) {
		setMaximumSize(new Dimension(90, 40));
		listPrice.setFont(DEFAULT_TEXT_FONT);
		listPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		listPrice.setText(Double.toString(list.getTotal()) + " kr");
		
		listNbrItems.setFont(DEFAULT_TEXT_FONT);
		listNbrItems.setHorizontalAlignment(SwingConstants.RIGHT);
		listNbrItems.setText(Integer.toString(list.getNbrItems()) + "st varor");
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
