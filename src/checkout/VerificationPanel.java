package checkout;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import se.chalmers.ait.dat215.project.IMatDataHandler;

public class VerificationPanel extends JPanel {
	private JTextField passwordField;
	private JButton cancelButton; 
	private JButton submitButton;
	
	private final Font DEFAULT_FONT = new Font("Calibri", Font.PLAIN, 12);
	private final Color DEFAULT_COLOR = new Color(150,150,150);
	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color CANCEL_TEXT_COLOR = new Color(144,144,144);
	
	public VerificationPanel() {
		setOpaque(false);
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		Date todaysDate = new Date();
		Session session = Session.getInstance();
		Dimension d = new Dimension(600/3, 265/3);
		Dimension d2 = new Dimension(200,200);
		setMinimumSize(new Dimension(800, 500));
		setMaximumSize(new Dimension(800, 500));
		ImageIcon vbVisa = new ImageIcon("img/VerifiedbyVisa.png");
		Image temp = vbVisa.getImage();
		Image finishedImage = temp.getScaledInstance(600/3, 265/3, Image.SCALE_FAST);
		JLabel vbVisaLabel = new JLabel("");
		vbVisaLabel.setPreferredSize(new Dimension(200, 88));
		vbVisa.setImage(finishedImage);
		vbVisaLabel.setIcon(vbVisa);
		vbVisaLabel.setMaximumSize(d);
		vbVisaLabel.setMinimumSize(d);
		
		JLabel pleaseLabel = new JLabel("Please submit your verification password associated with your credit card.");
		pleaseLabel.setFont(DEFAULT_FONT);
		pleaseLabel.setForeground(DEFAULT_COLOR);
		
		JLabel retailLabel = new JLabel("Retailer:");
		retailLabel.setFont(DEFAULT_FONT);
		retailLabel.setForeground(DEFAULT_COLOR);
		
		JLabel amountLabel = new JLabel("Amount: ");
		amountLabel.setFont(DEFAULT_FONT);
		amountLabel.setForeground(DEFAULT_COLOR);
		
		JLabel dateLabel = new JLabel("Date: ");
		dateLabel.setFont(DEFAULT_FONT);
		dateLabel.setForeground(DEFAULT_COLOR);
		
		JLabel cardNumberLabel = new JLabel("Card number:");
		cardNumberLabel.setFont(DEFAULT_FONT);
		cardNumberLabel.setForeground(DEFAULT_COLOR);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(DEFAULT_FONT);
		passwordLabel.setForeground(DEFAULT_COLOR);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		submitButton = new JButton("Submit");
		submitButton.setFont(DEFAULT_FONT);
		submitButton.setBackground(SELECTED_BG_COLOR);
		submitButton.setForeground(SELECTED_TEXT_COLOR);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(DEFAULT_FONT);
		cancelButton.setBackground(CANCEL_TEXT_COLOR);
		cancelButton.setForeground(Color.WHITE);
		
		JLabel matnyttitLabel = new JLabel("MatnyttIT");
		pleaseLabel.setFont(DEFAULT_FONT);
		matnyttitLabel.setForeground(DEFAULT_COLOR);
		
		JLabel dynamicAmountLabel = new JLabel("" + dataHandler.getShoppingCart().getTotal() + " kr", SwingConstants.RIGHT);
		pleaseLabel.setFont(DEFAULT_FONT);
		dynamicAmountLabel.setForeground(DEFAULT_COLOR);
		
		JLabel dynamicCardNumberLabel = new JLabel(session.getValue("cardnumber"), SwingConstants.RIGHT);
		pleaseLabel.setFont(DEFAULT_FONT);
		dynamicCardNumberLabel.setForeground(DEFAULT_COLOR);
		
		JLabel dynamicDateLabel = new JLabel("" + todaysDate.getDate() + "/" + (todaysDate.getMonth()+1));
		pleaseLabel.setFont(DEFAULT_FONT);
		dynamicDateLabel.setForeground(DEFAULT_COLOR);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(116)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(vbVisaLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(cancelButton)
								.addGap(18)
								.addComponent(submitButton))
							.addComponent(pleaseLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(cardNumberLabel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
									.addComponent(dateLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(amountLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(retailLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(matnyttitLabel)
									.addComponent(dynamicCardNumberLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addComponent(dynamicAmountLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addComponent(dynamicDateLabel)))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(passwordLabel)
								.addPreferredGap(ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))))
					.addGap(329))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(vbVisaLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pleaseLabel)
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(retailLabel)
						.addComponent(matnyttitLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountLabel)
						.addComponent(dynamicAmountLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(dateLabel)
						.addComponent(dynamicDateLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cardNumberLabel)
						.addComponent(dynamicCardNumberLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(cancelButton))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	public void addCancelButtonListener(ActionListener l) {
		cancelButton.addActionListener(l);
	}
	
	public void addSubmitButtonListener(ActionListener l) {
		this.submitButton.addActionListener(l);
	}
}
