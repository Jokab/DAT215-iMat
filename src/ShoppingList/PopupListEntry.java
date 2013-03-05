package ShoppingList;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

public class PopupListEntry extends JButton {

	private ShoppingList list;
	private String name;
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);
	
	/**
	 * Create the panel.
	 */
	public PopupListEntry(ShoppingList list) {
		setBorder(new EmptyBorder(0, 10, 0, 0));
		setPreferredSize(new Dimension(250, 25));
		setMinimumSize(new Dimension(25, 25));
		setSize(new Dimension(250, 25));
		setMaximumSize(new Dimension(250, 25));
		setHorizontalAlignment(SwingConstants.LEFT);
		this.list = list;
		this.name = list.getName();
		setBackground(NORMAL_BG_COLOR);
		setForeground(NORMAL_TEXT_COLOR);
		setText(name);
	}
	
	public ShoppingList getShoppingList() {
		return this.list;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public void addOwnMouseListener(MouseListener listener) {
		addMouseListener(listener);
//		nameButton.addMouseListener(listener);
	}

}
