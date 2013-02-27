package ShoppingList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class ShoppingListEntryInfo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3512891505977682633L;
	private final JLabel listPrice = new JLabel("New label");
	private final JLabel listNbrItems = new JLabel("New label");

	/**
	 * Create the panel.
	 * @param list 
	 */
	public ShoppingListEntryInfo(ShoppingList list) {
		setMaximumSize(new Dimension(90, 40));
		listPrice.setText(Double.toString(list.getTotal()) + " kr");
		listNbrItems.setText(Integer.toString(list.getNbrItems()) + " st");
		
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
