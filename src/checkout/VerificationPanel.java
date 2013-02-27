package checkout;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
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
	public VerificationPanel() {
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		Date todaysDate = new Date();
		Session session = Session.getInstance();
		Dimension d = new Dimension(600/3, 265/3);
		Dimension d2 = new Dimension(200,200);
		setMinimumSize(new Dimension(800, 500));
		setMaximumSize(new Dimension(800, 500));
		ImageIcon vbVisa = new ImageIcon("C:\\Users\\christoffer\\workspace\\dat215-matnyttigt\\lib\\images\\VerifiedbyVisa.png");
		Image temp = vbVisa.getImage();
		Image finishedImage = temp.getScaledInstance(600/3, 265/3, Image.SCALE_FAST);
		JLabel vbVisaLabel = new JLabel("");
		vbVisaLabel.setPreferredSize(new Dimension(200, 88));
		vbVisa.setImage(finishedImage);
		vbVisaLabel.setIcon(vbVisa);
		vbVisaLabel.setMaximumSize(d);
		vbVisaLabel.setMinimumSize(d);
		
		JLabel pleaseLabel = new JLabel("Please submit your verification password associated with your credit card.");
		
		JLabel retailLabel = new JLabel("Retailer:");
		
		JLabel amountLabel = new JLabel("Amount: ");
		
		JLabel dateLabel = new JLabel("Date: ");
		
		JLabel cardNumberLabel = new JLabel("Card number:");
		
		JLabel passwordLabel = new JLabel("Password:");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		
		JButton cancelButton = new JButton("Cancel");
		
		JLabel matnyttitLabel = new JLabel("MatnyttIT");
		
		JLabel dynamicAmountLabel = new JLabel("" + dataHandler.getShoppingCart().getTotal() + " kr", SwingConstants.RIGHT);
		
		JLabel dynamicCardNumberLabel = new JLabel(session.getValue("cardnumber"), SwingConstants.RIGHT);
		
		JLabel dynamicDateLabel = new JLabel("" + todaysDate.getDate() + "/" + (todaysDate.getMonth()+1));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(116)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(vbVisaLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(cancelButton)
								.addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
								.addComponent(submitButton))
							.addComponent(pleaseLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(cardNumberLabel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
									.addComponent(dateLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(amountLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(retailLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(matnyttitLabel)
									.addComponent(dynamicCardNumberLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addComponent(dynamicAmountLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addComponent(dynamicDateLabel)))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(passwordLabel)
								.addGap(72)
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
						.addComponent(passwordLabel)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(cancelButton))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
