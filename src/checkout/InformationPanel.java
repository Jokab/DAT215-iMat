package checkout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformationPanel extends JPanel {
	
	private JTextField LastNameField;
	private JTextField FirstNameField;
	private JTextField AddressField;
	private JTextField CityField;
	private JFormattedTextField ZIPField;
	private JFormattedTextField MonthField;
	private JFormattedTextField CardNumberField;
	private JFormattedTextField YearField;
	private JTextField EmailField;
	private Session session;

	/**
	 * Create the panel.
	 */
	public InformationPanel() {
		
		session = Session.getInstance();
		
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
		
		NumberFormat phoneFormat = NumberFormat.getInstance();
		phoneFormat.setMaximumIntegerDigits(14);
		phoneFormat.setMinimumIntegerDigits(8);
		phoneFormat.setGroupingUsed(false);
		
		JLabel LastNameLabel = new JLabel("Efternamn:");
		LastNameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel FirstNameLabel = new JLabel("F\u00F6rnamn:");
		FirstNameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel AdressLabel = new JLabel("Leveransadress:");
		AdressLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel ZIPLabel = new JLabel("Postnummer:");
		ZIPLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel CityLabel = new JLabel("Stad:");
		CityLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel CardNumberLabel = new JLabel("Kortnummer:");
		CardNumberLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel CCVLabel = new JLabel("CCV:");
		CCVLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel ValidThroughLabel = new JLabel("Giltligt till:");
		ValidThroughLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel CardTypeLabel = new JLabel("Korttyp:");
		CardTypeLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		LastNameField = new JTextField(session.getValue("lastname"));
		LastNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				session.put("lastname", LastNameField.getText());
			}
		});
		LastNameField.setColumns(10);
		
		FirstNameField = new JTextField(session.getValue("firstname"));
		FirstNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				session.put("firstname", FirstNameField.getText());
			}
		});
		FirstNameField.setColumns(10);
		
		AddressField = new JTextField(session.getValue("address"));
		AddressField.setColumns(10);
		AddressField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				session.put("address", AddressField.getText());
			}
		});
		
		CityField = new JTextField(session.getValue("city"));	
		CityField.setColumns(10);
		CityField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				session.put("city", CityField.getText());
			}
		});
		
		ZIPField = new JFormattedTextField(ZIPFormat);	
		ZIPField.setText(session.getValue("zipcode"));
		ZIPField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
					session.put("zipcode", ZIPField.getText());
				}
		});
		ZIPField.setColumns(10);
		
		MonthField = new JFormattedTextField(monthFormat);
		MonthField.setText(session.getValue("validmonth"));
		MonthField.setColumns(10);
		MonthField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				session.put("validmonth",MonthField.getText());
			}
		});
		
		CardNumberField = new JFormattedTextField(cardNumberFormat);
		CardNumberField.setText(session.getValue("cardnumber"));
		CardNumberField.setColumns(10);
		CardNumberField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				session.put("cardnumber", CardNumberField.getText());
			}
		});
		
		JLabel label = new JLabel("/");
		label.setFont(new Font("Georgia", Font.BOLD, 15));
		
		YearField = new JFormattedTextField(yearFormat);
		YearField.setText(session.getValue("validyear"));
		YearField.setColumns(10);
		YearField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				session.put("validyear", YearField.getText());
			}
		});
		
		
		final JComboBox CardTypeBox = new JComboBox();
		CardTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Visa", "Mecenat", "American Express", "Mastercard"}));
		int index = 0;
		switch (session.getValue("cardtype")){
		case "Visa": index = 0; break;
		case "Mecenat": index=1; break;
		case "American Express": index=2; break;
		case "Mastercard": index = 3; break;
		default: index = 0; break;
		}
		CardTypeBox.setSelectedIndex(index);
		CardTypeBox.setFont(new Font("Georgia", Font.PLAIN, 13));
		CardTypeBox.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e){
				session.put("cardtype", (String) CardTypeBox.getSelectedItem());
			}
		});
		
		JButton BeginVerificationButton = new JButton("G\u00E5 till verifiering");
		BeginVerificationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Load verification panel.
				session.saveSession();
			}
		});
		
		JCheckBox saveMyInfoBox = new JCheckBox("Spara mina uppgifter");
		saveMyInfoBox.setSelected(session.getSaveInfo());
		
		
		
		final JFormattedTextField CCVField = new JFormattedTextField(CCVFormat);
		CCVField.setText(session.getValue("ccv"));
		CCVField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e){
				session.put("ccv", CCVField.getText());
			}
		});
		
		JLabel PhoneNumberLabel = new JLabel("Telefonnummer:");
		PhoneNumberLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		JLabel EmailLabel = new JLabel("Email:");
		EmailLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		final JFormattedTextField PhoneNumberField = new JFormattedTextField(phoneFormat);
		PhoneNumberField.setText(session.getValue("phonenumber"));
		PhoneNumberField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e){
				session.put("phonenumber", PhoneNumberField.getText());
			}
		});
		
		EmailField = new JTextField(session.getValue("email"));
		EmailField.setColumns(10);
		EmailField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e){
				session.put("email", EmailField.getText());
			}
		});
		
		saveMyInfoBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				session.setSaveInfo(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(FirstNameLabel)
								.addComponent(LastNameLabel))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(FirstNameField)
								.addComponent(LastNameField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(ValidThroughLabel)
								.addComponent(EmailLabel)
								.addComponent(CardTypeLabel)
								.addComponent(CardNumberLabel)
								.addComponent(CCVLabel)
								.addComponent(PhoneNumberLabel)
								.addComponent(ZIPLabel)
								.addComponent(CityLabel)
								.addComponent(AdressLabel))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(PhoneNumberField, 176, 176, Short.MAX_VALUE)
								.addComponent(CityField)
								.addComponent(ZIPField)
								.addComponent(AddressField, 176, 176, Short.MAX_VALUE)
								.addComponent(CardTypeBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(EmailField, 176, 176, Short.MAX_VALUE)
								.addComponent(CardNumberField)
								.addComponent(CCVField)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(MonthField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(YearField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addComponent(saveMyInfoBox))))
					.addContainerGap(449, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(681, Short.MAX_VALUE)
					.addComponent(BeginVerificationButton)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(80)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(LastNameLabel)
						.addComponent(LastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(FirstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(FirstNameLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(AddressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AdressLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ZIPField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ZIPLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(CityLabel)
						.addComponent(CityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(PhoneNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PhoneNumberLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(EmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(EmailLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(CardTypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(CardTypeLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(CardNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(CardNumberLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(CCVField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(CCVLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(MonthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(YearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ValidThroughLabel))
					.addGap(18)
					.addComponent(saveMyInfoBox)
					.addGap(17)
					.addComponent(BeginVerificationButton)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
