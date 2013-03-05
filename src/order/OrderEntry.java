package order;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import se.chalmers.ait.dat215.project.Order;

public class OrderEntry extends JPanel {
	private final JLabel orderDateLabel = new JLabel("New label");
	private final OrderEntryInfo orderInfoLabel;
	private final JLabel arrowIcon;
	private Order order;
	private String date;
	
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
	public OrderEntry(Order order) {
		this.order = order;
		orderInfoLabel = new OrderEntryInfo(order);
		date = OrderUtil.convertDateToFormattedString(order.getDate());

		setBackground(NORMAL_BG_COLOR);
		setMaximumSize(new Dimension(32767, 50));
		setMinimumSize(new Dimension(10, 50));
		setPreferredSize(new Dimension(200, 50));
		setBorder(new MatteBorder(0, 0, 1, 0, new Color(225, 225, 225)));
		
		orderInfoLabel.setBounds(151, 11, 93, 28);
		orderInfoLabel.setTextColor(NORMAL_TEXT_COLOR);
		orderInfoLabel.setBackground(NORMAL_BG_COLOR);
		
		orderDateLabel.setBounds(10, 17, 140, 17);
		orderDateLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		orderDateLabel.setForeground(NORMAL_TEXT_COLOR);
		orderDateLabel.setText(date);
		
		arrowIcon = new JLabel(greyArrow);
		arrowIcon.setBounds(247, 17, 15, 17);
		setLayout(null);
		add(orderDateLabel);
		add(orderInfoLabel);
		add(arrowIcon);
	}
	public void addEntryMouseListener(MouseListener listener) {
		addMouseListener(listener);
	}
	
	public Order getOrder() {
		return this.order;
	}

	public String getDate() {
		return this.date;
	}
	
	/**
	 * Toggles the panel to be marked or unmarked.
	 * This method won't affect a panel set to active.
	 */
	public void toggle() {
		if(!isActive) {
			orderDateLabel.setForeground(isToggled? NORMAL_TEXT_COLOR : SELECTED_TEXT_COLOR);
			setBackground(isToggled? NORMAL_BG_COLOR : SELECTED_BG_COLOR);
			
			orderInfoLabel.setTextColor(isToggled? NORMAL_TEXT_COLOR : SELECTED_TEXT_COLOR);
			orderInfoLabel.setBackground(isToggled? NORMAL_BG_COLOR : SELECTED_BG_COLOR);
			
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
