package myAccount;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Container;
import javax.swing.JLabel;

import components.SidePanelButton;

import productView.ProductSidePanelButton;

public class MyAccountSidePanel extends JPanel {
	private final JLabel headerLabel;
	private final JPanel subcategoriesPanel;
	
	/**
	 * Create the panel.
	 */
	public MyAccountSidePanel(MyAccountEnum activeController) {
		setOpaque(false);
		headerLabel = new JLabel("MITT KONTO");
		headerLabel.setOpaque(false);
		headerLabel.setBorder(null);
		headerLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		headerLabel.setForeground(new Color(150, 150 ,150));
		
		subcategoriesPanel = new JPanel();
		subcategoriesPanel.setOpaque(false);
		subcategoriesPanel.setLayout(new BoxLayout(subcategoriesPanel, BoxLayout.PAGE_AXIS));
		
		SidePanelButton button = new SidePanelButton("Mina uppgifter");
		if(activeController == MyAccountEnum.SETTINGS) {
			button.setActive();
		}
		subcategoriesPanel.add(button);
		
		button = new SidePanelButton("Orderhistorik");
		if(activeController == MyAccountEnum.ORDERHISTORY) {
			button.setActive();
		}
		subcategoriesPanel.add(button);
		
		button = new SidePanelButton("Inköpslistor");
		if(activeController == MyAccountEnum.SHOPPINGLISTS) {
			button.setActive();
		}
		subcategoriesPanel.add(button);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(headerLabel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(subcategoriesPanel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(278, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(headerLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(subcategoriesPanel, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

}
