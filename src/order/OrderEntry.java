package order;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import se.chalmers.ait.dat215.project.Order;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;

public class OrderEntry extends JPanel {
	private final JButton btnNewButton = new JButton("+\n");
	private final JLabel shoppingListName = new JLabel("New label");
	private final Component horizontalStrut = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(20);
	private final JPanel shoppingInfoLabel;
	private Order order;
	/**
	 * Create the panel.
	 */
	public OrderEntry(Order order) {
		this.order = order;
		shoppingInfoLabel = new OrderEntryInfo(order);
		shoppingListName.setText(order.getDate().toString());
		
		
		setBackground(new Color(255, 255, 204));
		setMinimumSize(new Dimension(280, 45));
		setMaximumSize(new Dimension(280, 45));
		setSize(new Dimension(280, 45));
		setPreferredSize(new Dimension(280, 45));
		
		btnNewButton.setPreferredSize(new Dimension(50, 25));
		
		horizontalStrut_1.setPreferredSize(new Dimension(8, 0));
		horizontalStrut_1.setMinimumSize(new Dimension(8, 0));
		
		horizontalStrut.setMinimumSize(new Dimension(0, 0));
		horizontalStrut.setPreferredSize(new Dimension(50, 0));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(horizontalStrut_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(shoppingListName)
					.addGap(5)
					.addComponent(horizontalStrut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(shoppingInfoLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(horizontalStrut_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(horizontalStrut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(5)
									.addComponent(shoppingListName))))
						.addComponent(shoppingInfoLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	public void addEntryMouseListener(MouseListener listener) {
//		shoppingListName.addMouseListener(listener);
//		shoppingInfoLabel.addMouseListener(listener);
//		horizontalStrut.addMouseListener(listener);
//		shoppingListName.addMouseListener(listener);
		addMouseListener(listener);
	}
	
//	public Order getOrders() {
//		return this.order;
//	}
	
	public String processDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.
	}


}
