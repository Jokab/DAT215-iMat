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

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	private Map<String,Boolean> errors;

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
	private JLabel errorMessageLabel;
	private JLabel lastNameLabel;
	private JLabel firstNameLabel;
	private JLabel addressLabel;
	private JLabel zipLabel;
	private JLabel cityLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel cardTypeLabel;
	private JLabel cardNumberLabel;
	private JLabel ccvLabel;
	private JLabel validDateLabel;
	
	public MyInfoPanel() {
		errors = new HashMap<String,Boolean>();
		
		setPreferredSize(new Dimension(600, 500));
		setMinimumSize(new Dimension(600, 500));
		setMaximumSize(new Dimension(600, 500));
		
		NumberFormat CCVFormat = NumberFormat.getInstance();
		CCVFormat.setMaximumIntegerDigits(3);
		CCVFormat.setMinimumIntegerDigits(3);
		CCVFormat.setGroupingUsed(false);
		
		NumberFormat cardNumberFormat = NumberFormat.getInstance();
		cardNumberFormat.setMaximumIntegerDigits(16);
		cardNumberFormat.setMinimumIntegerDigits(16);
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
		
		lastNameLabel = new JLabel("Efternamn:");
		lastNameLabel.setToolTipText("Ditt efternamn");
		lastNameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		firstNameLabel = new JLabel("F\u00F6rnamn:");
		firstNameLabel.setToolTipText("Ditt f\u00F6rnamn");
		firstNameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		addressLabel = new JLabel("Leveransadress:");
		addressLabel.setToolTipText("Din leveransadress");
		addressLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		zipLabel = new JLabel("Postnummer:");
		zipLabel.setToolTipText("Ditt postnummer utan mellanrum");
		zipLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		cityLabel = new JLabel("Stad:");
		cityLabel.setToolTipText("Din postort");
		cityLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		phoneLabel = new JLabel("Telefonnummer:");
		phoneLabel.setToolTipText("Ditt telefonnnummer p\u00E5 valfritt format");
		phoneLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		emailLabel = new JLabel("Email:");
		emailLabel.setToolTipText("Din emailadress");
		emailLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		cardTypeLabel = new JLabel("Korttyp:");
		cardTypeLabel.setToolTipText("Korttyp");
		cardTypeLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		cardNumberLabel = new JLabel("Kortnummer:");
		cardNumberLabel.setToolTipText("Ditt kontokortnummer utan mellanrum eller bindestreck");
		cardNumberLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		ccvLabel = new JLabel("CCV:");
		ccvLabel.setToolTipText("De tre sista siffrorna i koden p\u00E5 baksidan av ditt kontokort");
		ccvLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		validDateLabel = new JLabel("Giltligt till:");
		validDateLabel.setToolTipText("M\u00E5nad och \u00E5r n\u00E4r ditt kontokort g\u00E5r ut");
		validDateLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		lastNameField = new JTextField(customer.getLastName());
		lastNameField.setToolTipText("Ditt efternamn");
		lastNameField.setColumns(10);
		
		firstNameField = new JTextField(customer.getFirstName());
		firstNameField.setToolTipText("Ditt f\u00F6rnamn");
		firstNameField.setColumns(10);
		
		addressField = new JTextField(customer.getAddress());
		addressField.setToolTipText("Din leveransadress");
		addressField.setColumns(10);
		
		zipField = new JFormattedTextField(ZIPFormat);
		zipField.setToolTipText("Ditt postnummer utan mellanrum");
		zipField.setText(customer.getPostCode());
		if(zipField.getText().length()==0){
			zipField.setText("nnnnn");
		}
		cityField = new JTextField(customer.getPhoneNumber());
		cityField.setToolTipText("Din postort");
		cityField.setColumns(10);
		
		phoneNumberField = new JFormattedTextField(customer.getPhoneNumber());
		phoneNumberField.setToolTipText("Ditt telefonnnummer p\u00E5 valfritt format");
		
		emailField = new JTextField(customer.getEmail());
		emailField.setToolTipText("Din emailadress");
		emailField.setColumns(10);
		
		cardNumberField = new JFormattedTextField(cardNumberFormat);
		cardNumberField.setToolTipText("Ditt kontokortnummer utan mellanrum eller bindestreck");
		cardNumberField.setText(creditCard.getCardNumber());
		if(cardNumberField.getText().length()==0){
			cardNumberField.setText("nnnnnnnnnnnnnnnn");
		}
		
		ccvField = new JFormattedTextField(CCVFormat);
		ccvField.setToolTipText("De tre sista siffrorna i koden p\u00E5 baksidan av ditt kontokort");
		ccvField.setText("" +creditCard.getVerificationCode());
		
		monthField = new JFormattedTextField(monthFormat);
		monthField.setToolTipText("M\u00E5nad och \u00E5r n\u00E4r ditt kontokort g\u00E5r ut");
		monthField.setText("" +creditCard.getValidMonth());
		if(monthField.getText().length()==0){
			monthField.setText("mm");
		}
		
		JLabel slashLabel = new JLabel("/");
		slashLabel.setToolTipText("M\u00E5nad och \u00E5r n\u00E4r ditt kontokort g\u00E5r ut");
		slashLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		yearField = new JFormattedTextField(yearFormat);
		yearField.setToolTipText("M\u00E5nad och \u00E5r n\u00E4r ditt kontokort g\u00E5r ut");
		yearField.setText("" + creditCard.getValidYear());
		if(yearField.getText().length()==0){
			yearField.setText("åååå");
		}
		
		cardTypeBox = new JComboBox();
		cardTypeBox.setToolTipText("Korttyp");
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
		
		errorMessageLabel = new JLabel("");
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMinaUppgifter, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(phoneNumberField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(68)
									.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(58)
									.addComponent(cardTypeLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cardTypeBox, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(20)
									.addComponent(cardNumberLabel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cardNumberField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cityField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addComponent(zipField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(41)
									.addComponent(validDateLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(monthField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(slashLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(errorMessageLabel, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAvbryt)
							.addGap(18)
							.addComponent(saveButton)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMinaUppgifter, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(errorMessageLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(87)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveButton)
						.addComponent(btnAvbryt))
					.addGap(51))
		);
		setLayout(groupLayout);
	}
	
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
			errorMessageLabel.setText("<html><body>Var vänlig se över följande information:<br>");
			errorMessageLabel.setVisible(!dataIsOk());
			Set<String> keys = errors.keySet();
			Iterator i =keys.iterator();
			while(i.hasNext()){
				String temp = (String) i.next();
				// TODO switch case, 
				if(errors.get(temp)){
					switch(temp){
					case("firstname"): temp = "Förnamn"; firstNameLabel.setForeground(Color.RED); break;
					case("lastname"): temp = "Efternamn"; lastNameLabel.setForeground(Color.RED); break;
					case("address"): temp = "Address"; addressLabel.setForeground(Color.RED);break;
					case("cardnumber"): temp = "Kortnummer"; cardNumberLabel.setForeground(Color.RED); break;
					case("ccv"): temp = "CCV"; ccvLabel.setForeground(Color.RED); break;
					case("zipcode"): temp = "Postnummer"; zipLabel.setForeground(Color.RED); break;
					case("city"): temp = "Stad"; cityLabel.setForeground(Color.RED);break;
					case("validmonth"): temp = ""; validDateLabel.setForeground(Color.RED);break;
					case("validyear"): temp = ""; validDateLabel.setForeground(Color.RED); break;
					case("phonenumber"): temp = "Telefonnummer"; phoneLabel.setForeground(Color.RED); break;
					}
					errorMessageLabel.setText(errorMessageLabel.getText() + temp +"<br>");
				}
			}
			errorMessageLabel.setText(errorMessageLabel.getText()+"</body></html>");
		}
	}
	private boolean dataIsOk(){
		try{
			Integer.parseInt(zipField.getText());
		} catch (Exception e){
			errors.put("zipcode", true);
		}
		try{
			Integer.parseInt(monthField.getText());
		} catch (Exception e){
			errors.put("validmonth", true);
		}
		try{
			Integer.parseInt(yearField.getText());
		} catch (Exception e){
			errors.put("validyear", true);
		}
		try{
			System.out.println("!"+cardNumberField.getText()+"!");
			Double.parseDouble(cardNumberField.getText());
		} catch (Exception e){
			errors.put("cardnumber", true);
		}
		try{
			Integer.parseInt(ccvField.getText());
		} catch (Exception e){
			errors.put("zipcode", true);
		}
		if(firstNameField.getText().length() == 0){
			errors.put("firstname", true);
		}
		if(lastNameField.getText().length() == 0){
			errors.put("lastname", true);
		}
		if(addressField.getText().length() == 0){
			errors.put("address", true);
		}
		if(phoneNumberField.getText().length() == 0){
			errors.put("phonenumber", true);
		}
		if(cityField.getText().length() == 0){
			errors.put("city", true);
		}
		return !(errors.containsValue(true));
	}
}
