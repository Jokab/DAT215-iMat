package myProfile;

import javax.swing.JPanel;

import checkout.Session;

import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Dimension;
import java.text.NumberFormat;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyInfoPanel extends JPanel {
	public MyInfoPanel() {
		
		setPreferredSize(new Dimension(600, 500));
		setMinimumSize(new Dimension(600, 500));
		setMaximumSize(new Dimension(600, 500));
		
		NumberFormat CCVFormat = NumberFormat.getInstance();
		CCVFormat.setMaximumIntegerDigits(3);
		CCVFormat.setMinimumIntegerDigits(3);
		CCVFormat.setGroupingUsed(false);
		
		NumberFormat cardNumberFormat = NumberFormat.getInstance();
		cardNumberFormat.setMaximumIntegerDigits(16);
		cardNumberFormat.setMinimumIntegerDigits(20);
		cardNumberFormat.setGroupingUsed(false);
		
		NumberFormat ZIPFormat = NumberFormat.getInstance();
		ZIPFormat.setMaximumIntegerDigits(5);
		ZIPFormat.setMinimumIntegerDigits(5);
		ZIPFormat.setGroupingUsed(false);
		
		NumberFormat yearFormat = NumberFormat.getInstance();
		yearFormat.setMaximumIntegerDigits(4);
		yearFormat.setMinimumIntegerDigits(2);
		yearFormat.setGroupingUsed(false);
		
		NumberFormat monthFormat = NumberFormat.getInstance();
		monthFormat.setMaximumIntegerDigits(2);
		monthFormat.setMinimumIntegerDigits(2);
		monthFormat.setGroupingUsed(false);
		
		JLabel lblMinaUppgifter = new JLabel("Mina uppgifter");
		lblMinaUppgifter.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel lastNameLabel = new JLabel("Efternamn:");
		lastNameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel firstNameLabel = new JLabel("F\u00F6rnamn:");
		firstNameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel addressLabel = new JLabel("Leveransadress:");
		addressLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel zipLabel = new JLabel("Postnummer:");
		zipLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel cityLabel = new JLabel("Stad:");
		cityLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel phoneLabel = new JLabel("Telefonnummer:");
		phoneLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel cardTypeLabel = new JLabel("Korttyp:");
		cardTypeLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel cardNumberLabel = new JLabel("Kortnummer:");
		cardNumberLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel ccvLabel = new JLabel("CCV:");
		ccvLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel validDateLabel = new JLabel("Giltligt till:");
		validDateLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		lastNameField = new JTextField(customer.getLastName());
		lastNameField.setColumns(10);
		
		firstNameField = new JTextField(customer.getFirstName());
		firstNameField.setColumns(10);
		
		addressField = new JTextField(customer.getAddress());
		addressField.setColumns(10);
		
		zipField = new JFormattedTextField(ZIPFormat);
		zipField.setText(customer.getPostCode());
		cityField = new JTextField(customer.getPhoneNumber());
		cityField.setColumns(10);
		
		phoneNumberField = new JFormattedTextField(customer.getPhoneNumber());
		
		emailField = new JTextField(customer.getEmail());
		emailField.setColumns(10);
		
		cardNumberField = new JFormattedTextField(cardNumberFormat);
		cardNumberField.setText(creditCard.getCardNumber());
		
		ccvField = new JFormattedTextField(CCVFormat);
		ccvField.setText("" +creditCard.getVerificationCode());
		
		monthField = new JFormattedTextField(monthFormat);
		monthField.setText("" +creditCard.getValidMonth());
		
		JLabel slashLabel = new JLabel("/");
		slashLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		yearField = new JFormattedTextField(yearFormat);
		yearField.setText("" + creditCard.getValidYear());
		
		cardTypeBox = new JComboBox();
		cardTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Visa", "Mecenat", "MasterCard", "American Express"}));
		int index= 0;
		switch (creditCard.getCardType()){
		case "Visa": index = 0; break;
		case "Mecenat": index=1; break;
		case "American Express": index=2; break;
		case "Mastercard": index = 3; break;
		default: index = 0; break;
		}
		cardTypeBox.setSelectedIndex(index);
		cardTypeBox.setFont(new Font("Georgia", Font.PLAIN, 13));
		
		JButton saveButton = new JButton("Spara");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveData();
			}
		});
		
		JButton btnAvbryt = new JButton("Avbryt");
		btnAvbryt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Go back in history
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMinaUppgifter, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(phoneNumberField, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(68)
									.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(emailField, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(58)
									.addComponent(cardTypeLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cardTypeBox, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(20)
									.addComponent(cardNumberLabel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cardNumberField, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(78)
									.addComponent(ccvLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ccvField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(35)
											.addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(45)
											.addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(7)
											.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addComponent(addressField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(21)
											.addComponent(zipLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(79)
											.addComponent(cityLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cityField, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(zipField, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(41)
									.addComponent(validDateLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(monthField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(slashLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(297))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnAvbryt)
							.addGap(18)
							.addComponent(saveButton))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMinaUppgifter, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(0)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(zipLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(zipField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cityLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(cityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(phoneNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cardTypeLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(cardTypeBox, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cardNumberLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(cardNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(ccvLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(ccvField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(validDateLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(monthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(slashLabel)
								.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(87)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveButton)
						.addComponent(btnAvbryt))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	IMatDataHandler dataHandler = IMatDataHandler.getInstance();
	Customer customer = dataHandler.getCustomer();
	CreditCard creditCard = dataHandler.getCreditCard();
	private JTextField lastNameField;
	private JTextField firstNameField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField emailField;
	JFormattedTextField zipField;
	JFormattedTextField yearField;
	JFormattedTextField ccvField;
	JFormattedTextField cardNumberField;
	JFormattedTextField monthField;
	JFormattedTextField phoneNumberField;
	JComboBox cardTypeBox;
	private void saveData() {
		// TODO Auto-generated method stub
		if(dataIsOk()){
			customer.setAddress(addressField.getText());
			customer.setPostAddress(cityField.getText());
			customer.setEmail(emailField.getText());
			customer.setFirstName(firstNameField.getText());
			customer.setLastName(lastNameField.getText());
			customer.setPhoneNumber(phoneNumberField.getText());
			creditCard.setCardType((String) cardTypeBox.getSelectedItem());
			creditCard.setCardNumber(cardNumberField.getText());
			creditCard.setValidMonth(Integer.parseInt(monthField.getText()));
			creditCard.setValidYear(Integer.parseInt(yearField.getText()));
			creditCard.setVerificationCode(Integer.parseInt(ccvField.getText()));
		} else {
			JOptionPane.showMessageDialog(new JFrame("Ajdå"), "Det verkar ha blivit något fel med de uppgifter du matat in.\n Var god se över dessa och försök igen.");
		}
	}
	private boolean dataIsOk(){
		try{
			Integer.parseInt(zipField.getText());
			Integer.parseInt(monthField.getText());
			Integer.parseInt(yearField.getText());
			Integer.parseInt(cardNumberField.getText());
			Integer.parseInt(ccvField.getText());
		} catch (Exception e){
			return false;
		}
		if(firstNameField.getText().length() == 0 || 
				lastNameField.getText().length() == 0 ||
				addressField.getText().length() == 0 ||
				phoneNumberField.getText().length() == 0 ||
				cityField.getText().length() == 0){
			return false;
		}
		return true;
	}
}
