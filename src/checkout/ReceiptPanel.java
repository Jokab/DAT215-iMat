package checkout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.util.List;



import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.BoxLayout;

import components.StandardButton;
import components.StandardButtonGreen;
import javax.swing.Icon;

public class ReceiptPanel extends JPanel {
	private JPanel contentPanel;
	private JButton doneButton;
	private JButton saveToListButton;
	private final Font DEFAULT_FONT = new Font("Calibri", Font.PLAIN, 12);
	private final Color DEFAULT_COLOR = new Color(150,150,150);
	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	
	public ReceiptPanel() {
		setOpaque(false);
		contentPanel = new JPanel();
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		Session session = Session.getInstance();
		ShoppingCart shoppingCart = dataHandler.getShoppingCart();
		List<ShoppingItem> shoppingList = shoppingCart.getItems();
		for (int i=0 ; i<shoppingList.size() ; i++){
			contentPanel.add(new ReceiptProductPanel(shoppingList.get(i)));
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel thanksLabel = new JLabel("Tack för din beställning!");
		thanksLabel.setFont(DEFAULT_FONT);
		thanksLabel.setForeground(DEFAULT_COLOR);
		
		JLabel totalCostLabel = new JLabel("Total kostnad: " + shoppingCart.getTotal() + " kr");
		totalCostLabel.setFont(DEFAULT_FONT);
		totalCostLabel.setForeground(DEFAULT_COLOR);
		
		JLabel cardNumberLabel = new JLabel("Kortnummer: " + "XXXXXXXXXXXX" + session.getValue("cardnumber").substring(12));
		cardNumberLabel.setFont(DEFAULT_FONT);
		cardNumberLabel.setForeground(DEFAULT_COLOR);
		
		JLabel nameLabel = new JLabel("Kund: " + session.getValue("firstname") + " " + session.getValue("lastname"));
		nameLabel.setFont(DEFAULT_FONT);
		nameLabel.setForeground(DEFAULT_COLOR);
		
		JLabel addressLabel = new JLabel("Leveransadress: " + session.getValue("address"));
		addressLabel.setFont(DEFAULT_FONT);
		addressLabel.setForeground(DEFAULT_COLOR);
		
		doneButton = new StandardButtonGreen("Tillbaka till startsidan");
		
		saveToListButton = new StandardButton("Spara som inköpslista");
		
		JLabel deliveryDateLabel = new JLabel("Leveransdatum: " + session.getValue("deliveryday") +"/"+ (Integer.parseInt(session.getValue("deliverymonth"))+1) + " - " + session.getValue("deliveryyear"));
		deliveryDateLabel.setFont(DEFAULT_FONT);
		deliveryDateLabel.setForeground(DEFAULT_COLOR);
		
		JLabel label = new JLabel(new ImageIcon("img/counter3.png"));
		
		JLabel lblKassaKvitto = new JLabel("Kassa - kvitto");
		lblKassaKvitto.setForeground(new Color(150, 150, 150));
		lblKassaKvitto.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE)
										.addComponent(totalCostLabel, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
										.addComponent(cardNumberLabel))
									.addComponent(deliveryDateLabel, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(thanksLabel))
								.addPreferredGap(ComponentPlacement.RELATED, 290, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(saveToListButton, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(doneButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKassaKvitto, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
					.addGap(280))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(54)
									.addComponent(thanksLabel))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(29)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(totalCostLabel)
									.addGap(19)
									.addComponent(cardNumberLabel)
									.addGap(18)
									.addComponent(nameLabel)
									.addGap(18)
									.addComponent(addressLabel)
									.addGap(18)
									.addComponent(deliveryDateLabel)
									.addGap(208))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(saveToListButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(doneButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblKassaKvitto, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {doneButton, saveToListButton});
		
		
		scrollPane.setViewportView(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		
		setLayout(groupLayout);
		
	}
	
	public void addDoneButtonListener(ActionListener l) {
		this.doneButton.addActionListener(l);
	}
	
	public void addSaveToListListener(ActionListener l) {
		this.saveToListButton.addActionListener(l);
	}
}
