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
import javax.swing.BoxLayout;

public class ReceiptPanel extends JPanel {
	public ReceiptPanel() {
		//TODO: Getting the shoppinglist and adding the items to the scrollpane (uneditable versions)
		Session session = Session.getInstance();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel thanksLabel = new JLabel("Tack f\u00F6r din best\u00E4llning!");
		thanksLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		
		JLabel totalCostLabel = new JLabel("Total kostnad: ");
		totalCostLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel cardNumberLabel = new JLabel("Kortnummer: " + session.getValue("cardnumber"));
		cardNumberLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel nameLabel = new JLabel("Kund: " + session.getValue("firstname" + " " + session.getValue("lastname")));
		nameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel addressLabel = new JLabel("Leveransadress: " + session.getValue("address"));
		addressLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JButton finishedButton = new JButton("Klar");
		
		JButton saveCartButton = new JButton("Spara handlingslista");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(257)
					.addComponent(thanksLabel)
					.addContainerGap(257, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE)
								.addComponent(totalCostLabel, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addComponent(cardNumberLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
						.addComponent(saveCartButton, Alignment.TRAILING))
					.addGap(18)
					.addComponent(finishedButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(174))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(thanksLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(totalCostLabel)
							.addGap(19)
							.addComponent(cardNumberLabel)
							.addGap(18)
							.addComponent(nameLabel)
							.addGap(18)
							.addComponent(addressLabel))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveCartButton)
						.addComponent(finishedButton))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
