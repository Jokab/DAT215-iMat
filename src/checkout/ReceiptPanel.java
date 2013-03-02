package checkout;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;


import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ReceiptPanel extends JPanel {
	private JPanel contentPanel;
	private JButton doneButton;
	private JButton saveToListButton;
	
	public ReceiptPanel() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		Session session = Session.getInstance();
		ShoppingCart shoppingCart = dataHandler.getShoppingCart();
		List<ShoppingItem> shoppingList = shoppingCart.getItems();
		for (int i=0 ; i<shoppingList.size() ; i++){
			contentPanel.add(new ReceiptProductPanel(shoppingList.get(i)));
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel thanksLabel = new JLabel("Tack f\u00F6r din best\u00E4llning!");
		thanksLabel.setFont(new Font("Verdana", Font.PLAIN, 26));
		
		JLabel totalCostLabel = new JLabel("Total kostnad: ");
		totalCostLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel cardNumberLabel = new JLabel("Kortnummer: " + session.getValue("cardnumber"));
		cardNumberLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel nameLabel = new JLabel("Kund: " + session.getValue("firstname" + " " + session.getValue("lastname")));
		nameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel addressLabel = new JLabel("Leveransadress: " + session.getValue("address"));
		addressLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		doneButton = new JButton("Klar");
		
		saveToListButton = new JButton("Spara handlingslista");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(saveToListButton)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE)
								.addComponent(totalCostLabel, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addComponent(cardNumberLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane)
								.addComponent(thanksLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(doneButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(174))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(thanksLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(totalCostLabel)
							.addGap(19)
							.addComponent(cardNumberLabel)
							.addGap(18)
							.addComponent(nameLabel)
							.addGap(18)
							.addComponent(addressLabel)
							.addGap(270))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveToListButton)
						.addComponent(doneButton))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		
		scrollPane.setViewportView(contentPanel);
		
		setLayout(groupLayout);
		
	}
	
	public void addDoneButtonListener(ActionListener l) {
		this.doneButton.addActionListener(l);
	}
	
	public void addSaveToListListener(ActionListener l) {
		this.saveToListButton.addActionListener(l);
	}
}
