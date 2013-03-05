package myAccount;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JLabel;

import components.SidePanelButton;

import java.awt.Dimension;
import javax.swing.border.MatteBorder;

public class MyAccountSidePanel extends JPanel {
	private final JLabel headerLabel;
	private final JPanel subcategoriesPanel;
	
	private final SidePanelButton settings =  new SidePanelButton("Mina uppgifter"); 
	private final SidePanelButton orderHistory =  new SidePanelButton("Orderhistorik"); 
	private final SidePanelButton shoppingLists =  new SidePanelButton("Inköpslistor"); 
	/**
	 * Create the panel.
	 */
	public MyAccountSidePanel(MyAccountEnum activeController) {
		setBorder(new MatteBorder(0, 0, 0, 1, new Color(225, 225, 225)));
		setOpaque(false);
		setSize(new Dimension(170, 400));
		setPreferredSize(new Dimension(162, 363));
		setMinimumSize(new Dimension(170, 400));
		setMaximumSize(new Dimension(170, 400));
		headerLabel = new JLabel("MITT KONTO");
		headerLabel.setOpaque(false);
		headerLabel.setBorder(null);
		headerLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		headerLabel.setForeground(new Color(150, 150 ,150));
		
		subcategoriesPanel = new JPanel();
		subcategoriesPanel.setOpaque(false);
		subcategoriesPanel.setLayout(new BoxLayout(subcategoriesPanel, BoxLayout.PAGE_AXIS));
		
		if(activeController == MyAccountEnum.SETTINGS) {
			settings.setActive();
		}
		subcategoriesPanel.add(settings);
	
		if(activeController == MyAccountEnum.ORDERHISTORY) {
			orderHistory.setActive();
		}
		subcategoriesPanel.add(orderHistory);
		
		if(activeController == MyAccountEnum.SHOPPINGLISTS) {
			shoppingLists.setActive();
		}
		subcategoriesPanel.add(shoppingLists);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(subcategoriesPanel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addComponent(headerLabel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(subcategoriesPanel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	public SidePanelButton getSettings() {
		return settings;
	}
	public SidePanelButton getOrderHistory() {
		return orderHistory;
	}
	public SidePanelButton getShoppingLists() {
		return shoppingLists;
	}
}
