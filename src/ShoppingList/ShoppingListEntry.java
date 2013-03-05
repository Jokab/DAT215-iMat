package ShoppingList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class ShoppingListEntry extends JPanel {
	private final JLabel shoppingListName = new JLabel("New label");
	private final ShoppingListEntryInfo shoppingInfoLabel;
	private final JLabel arrowIcon;
	private ShoppingList list;
	
	private final static ImageIcon greyArrow = new ImageIcon("img/arrowGrey.png");
	private final static ImageIcon whiteArrow = new ImageIcon("img/arrowWhite.png");
	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);

	
	private boolean isActive = false;
	private boolean isToggled = false;
	/**
	 * Create the panel.
	 */
	public ShoppingListEntry(ShoppingList list) {
		setBackground(NORMAL_BG_COLOR);
		setMaximumSize(new Dimension(32767, 50));
		setBorder(new MatteBorder(0, 0, 1, 0, new Color(225, 225, 225)));
		this.list = list;
		
		shoppingInfoLabel = new ShoppingListEntryInfo(list);
		shoppingInfoLabel.setBounds(151, 11, 93, 28);
		shoppingInfoLabel.setTextColor(NORMAL_TEXT_COLOR);
		shoppingInfoLabel.setBackground(NORMAL_BG_COLOR);
		
		shoppingListName.setBounds(10, 17, 61, 17);
		shoppingListName.setFont(new Font("Calibri", Font.PLAIN, 14));
		shoppingListName.setForeground(NORMAL_TEXT_COLOR);
		shoppingListName.setText(list.getName());
		
		arrowIcon = new JLabel(greyArrow);
		arrowIcon.setBounds(247, 17, 15, 17);
		setLayout(null);
		add(shoppingListName);
		add(shoppingInfoLabel);
		add(arrowIcon);
	}
	public void addEntryMouseListener(MouseListener listener) {
		addMouseListener(listener);
	}
	
	public ShoppingList getShoppingList() {
		return this.list;
	}
	public ShoppingListEntryInfo getInfoPanel() {
		return this.shoppingInfoLabel;
	}
	
	/**
	 * Toggles the panel to be marked or unmarked.
	 * This method won't affect a panel set to active.
	 */
	public void toggle() {
		if(!isActive) {
			shoppingListName.setForeground(isToggled? NORMAL_TEXT_COLOR : SELECTED_TEXT_COLOR);
			setBackground(isToggled? NORMAL_BG_COLOR : SELECTED_BG_COLOR);
			
			shoppingInfoLabel.setTextColor(isToggled? NORMAL_TEXT_COLOR : SELECTED_TEXT_COLOR);
			shoppingInfoLabel.setBackground(isToggled? NORMAL_BG_COLOR : SELECTED_BG_COLOR);
			
			arrowIcon.setIcon(isToggled? greyArrow : whiteArrow);
			
			isToggled ^= true;
		}
	}
	/**
	 * Sets the panels background color to green and text to white
	 */
	public void setActive() {
		isToggled = false;
		toggle();
		isActive = true;
	}
	
	/**
	 * Sets the panels background color to white and text to grey
	 */
	public void setInactive() {
		isToggled = true;
		isActive = false;
		toggle();
	}


}
