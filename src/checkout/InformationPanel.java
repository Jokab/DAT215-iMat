package checkout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import se.chalmers.ait.dat215.project.ShoppingCart;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFormattedTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import net.sourceforge.jdatepicker.DateModel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InformationPanel extends JPanel {
	
	private JTextField LastNameField;
	private JTextField FirstNameField;
	private JTextField AddressField;
	private JTextField CityField;
	private JFormattedTextField PhoneNumberField;
	private JFormattedTextField ZIPField;
	private JFormattedTextField MonthField;
	private JFormattedTextField CardNumberField;
	private JFormattedTextField YearField;
	private JFormattedTextField CCVField;
	private JTextField EmailField;
	private JButton BeginVerificationButton;
	private JLabel FirstNameLabel;
	private Session session;
	private UtilDateModel dateModel;
	private final JButton cancelButton;
	private JLabel errorLabel;
	private JLabel deliveryDateLabel;
	private JLabel LastNameLabel;
	private JLabel AdressLabel;
	private JLabel ZIPLabel;
	private JLabel CityLabel;
	private JLabel CardNumberLabel;
	private JLabel CCVLabel;
	private JLabel ValidThroughLabel;
	private JLabel PhoneNumberLabel;
	private JLabel lblSiffror;
	
	private final Font DEFAULT_FONT = new Font("Calibri", Font.PLAIN, 12);
	private final Color DEFAULT_COLOR = new Color(150,150,150);
	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color CANCEL_TEXT_COLOR = new Color(144,144,144);
	/**
	 * Create the panel.
	 */
	public InformationPanel() {
		setOpaque(false);
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		final ShoppingCart shoppingCart = dataHandler.getShoppingCart();
		setPreferredSize(new Dimension(800, 500));
		setMinimumSize(new Dimension(800, 500));
		setMaximumSize(new Dimension(800, 500));
		
		session = Session.getInstance();
		
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
		monthFormat.setMinimumIntegerDigits(1);
		monthFormat.setGroupingUsed(false);
		dateModel = new UtilDateModel();
		final JDatePanelImpl deliveryDateChooser = new JDatePanelImpl(dateModel);
		deliveryDateChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				session.put("deliveryday", "" + dateModel.getDay());
				session.put("deliverymonth", "" + dateModel.getMonth());
				session.put("deliveryyear", "" + dateModel.getYear());
				if(session.getErrorMessages().get("deliverydate")){
					deliveryDateLabel.setForeground(Color.RED);
				} else {
					deliveryDateLabel.setForeground(DEFAULT_COLOR);
				}
			}
		});
		deliveryDateChooser.setToolTipText("Det datum som du vill att dina varor ska levereras");
		
		
		LastNameLabel = new JLabel("Efternamn:");
		LastNameLabel.setToolTipText("Ditt efternamn");
		LastNameLabel.setFont(DEFAULT_FONT);
		LastNameLabel.setForeground(DEFAULT_COLOR);
		
		FirstNameLabel = new JLabel("F\u00F6rnamn:");
		FirstNameLabel.setFont(DEFAULT_FONT);
		FirstNameLabel.setForeground(DEFAULT_COLOR);
		
		AdressLabel = new JLabel("Leveransadress:");
		AdressLabel.setFont(DEFAULT_FONT);
		AdressLabel.setForeground(DEFAULT_COLOR);
		
		ZIPLabel = new JLabel("Postnummer:");
		ZIPLabel.setToolTipText("Ditt postnummer utan mellanrum");
		ZIPLabel.setFont(DEFAULT_FONT);
		ZIPLabel.setForeground(DEFAULT_COLOR);
		
		CityLabel = new JLabel("Stad:");
		CityLabel.setFont(DEFAULT_FONT);
		CityLabel.setForeground(DEFAULT_COLOR);
		
		CardNumberLabel = new JLabel("Kortnummer:");
		CardNumberLabel.setToolTipText("Ditt kortnummer utan mellanrum och bindestreck");
		CardNumberLabel.setFont(DEFAULT_FONT);
		CardNumberLabel.setForeground(DEFAULT_COLOR);
		
		CCVLabel = new JLabel("CCV:");
		CCVLabel.setToolTipText("De tre sista siffrorna i koden som st\u00E5r p\u00E5 baksidan av ditt kreditkort");
		CCVLabel.setFont(DEFAULT_FONT);
		CCVLabel.setForeground(DEFAULT_COLOR);
		
		ValidThroughLabel = new JLabel("Giltligt till:");
		ValidThroughLabel.setToolTipText("M\u00E5nad och \u00E5r d\u00E5 ditt kreditkort g\u00E5r ut. Anges p\u00E5 formatet mm/\u00E5\u00E5 eller mm/\u00E5\u00E5\u00E5\u00E5");
		ValidThroughLabel.setFont(DEFAULT_FONT);
		ValidThroughLabel.setForeground(DEFAULT_COLOR);
		
		JLabel CardTypeLabel = new JLabel("Korttyp:");
		CardTypeLabel.setFont(DEFAULT_FONT);
		CardTypeLabel.setForeground(DEFAULT_COLOR);
		
		LastNameField = new JTextField(session.getValue("lastname"));
		LastNameField.setToolTipText("Ditt efternamn");
		LastNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				session.put("lastname", LastNameField.getText());
				if(session.getErrorMessages().get("lastname")){
					LastNameLabel.setForeground(Color.RED);
				} else {
					LastNameLabel.setForeground(DEFAULT_COLOR);
				}
			}
		});
		LastNameField.setColumns(10);
		
		FirstNameField = new JTextField(session.getValue("firstname"));
		FirstNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				session.put("firstname", FirstNameField.getText());
				if(session.getErrorMessages().get("firstname")){
					FirstNameLabel.setForeground(Color.RED);
				} else {
					FirstNameLabel.setForeground(DEFAULT_COLOR);
				}
			}
		});
		FirstNameField.setColumns(10);
		
		AddressField = new JTextField(session.getValue("address"));
		AddressField.setColumns(10);
		AddressField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				session.put("address", AddressField.getText());
				if(session.getErrorMessages().get("address")){
					AdressLabel.setForeground(Color.RED);
				}  else {
					AdressLabel.setForeground(DEFAULT_COLOR);
				}
			}
		});
		
		CityField = new JTextField(session.getValue("city"));	
		CityField.setColumns(10);
		CityField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				session.put("city", CityField.getText());
				if(session.getErrorMessages().get("city")){
					CityLabel.setForeground(Color.RED);
				}  else {
					CityLabel.setForeground(DEFAULT_COLOR);
				}
			}
		});
		
		ZIPField = new JFormattedTextField(ZIPFormat);	
		ZIPField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar() >= 48 && arg0.getKeyChar()<= 57){
					
				} else {
					arg0.consume();
				}
				if(ZIPField.getText().length() == 5 ){
					ValidThroughLabel.setForeground(Color.GREEN);
				} else {
					ValidThroughLabel.setForeground(Color.RED);
				}
			}
		});
		ZIPField.setToolTipText("Ditt postnummer utan mellanrum");
		ZIPField.setText(session.getValue("zipcode"));
		if(ZIPField.getText().length()==0){
			ZIPField.setText("nnnnn");
		}
		ZIPField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
					session.put("zipcode", ZIPField.getText());
					//TODO:
					if(session.getErrorMessages().get("zipcode")){
						ZIPLabel.setForeground(Color.RED);
					} else if(ZIPLabel.getText().length() == 5){
						ZIPLabel.setForeground(Color.GREEN);
					} else {
						ZIPLabel.setForeground(DEFAULT_COLOR);
					}
				}
		});
		ZIPField.setColumns(10);
		
		MonthField = new JFormattedTextField(monthFormat);
		MonthField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar() >= 48 && arg0.getKeyChar()<= 57){
					
				} else {
					arg0.consume();
				}
				if((MonthField.getText().length()==2 || MonthField.getText().length()==1) && (YearField.getText().length()==4 || YearField.getText().length()==2) ){
					ValidThroughLabel.setForeground(Color.GREEN);
				} else {
					ValidThroughLabel.setForeground(Color.RED);
				}
			}
		});
		MonthField.setToolTipText("M\u00E5nad och \u00E5r d\u00E5 ditt kreditkort g\u00E5r ut. Anges p\u00E5 formatet mm/\u00E5\u00E5 eller mm/\u00E5\u00E5\u00E5\u00E5");
		MonthField.setText(session.getValue("validmonth"));
		if(MonthField.getText().length()==0){
			MonthField.setText("mm");
		}
		MonthField.setColumns(10);
		MonthField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				session.put("validmonth",MonthField.getText());
				if(session.getErrorMessages().get("validmonth")){
					ValidThroughLabel.setForeground(Color.RED);
				} else if ((MonthField.getText().length()==2 || MonthField.getText().length()==1) && (YearField.getText().length()==4 || YearField.getText().length()==2) ){
					ValidThroughLabel.setForeground(Color.GREEN);
				} else {
					ValidThroughLabel.setForeground(DEFAULT_COLOR);
				}
			}
		});
		
		CardNumberField = new JFormattedTextField(cardNumberFormat);
		CardNumberField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar() >= 48 && arg0.getKeyChar()<= 57){
					
				} else {
					arg0.consume();
				}
				if(CardNumberField.getText().length()==16){
					CardNumberLabel.setForeground(Color.GREEN);
				} else {
					CardNumberLabel.setForeground(Color.BLACK);
				}
			}
		});
		CardNumberField.setToolTipText("Ditt kortnummer utan mellanrum och bindestreck");
		CardNumberField.setText(session.getValue("cardnumber"));
		if(CardNumberField.getText().length()==0){
			CardNumberField.setText("nnnnnnnnnnnnnnnn");
		}
		CardNumberField.setColumns(10);
		CardNumberField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				session.put("cardnumber", CardNumberField.getText());
				if(session.getErrorMessages().get("cardnumber")){
					CardNumberLabel.setForeground(Color.RED);
				} else if (CardNumberField.getText().length() == 16){
					CardNumberLabel.setForeground(Color.GREEN);
				} else {
					CardNumberLabel.setForeground(DEFAULT_COLOR);
				}
			}
		});
		
		JLabel label = new JLabel("/");
		label.setToolTipText("M\u00E5nad och \u00E5r d\u00E5 ditt kreditkort g\u00E5r ut. Anges p\u00E5 formatet mm/\u00E5\u00E5 eller mm/\u00E5\u00E5\u00E5\u00E5");
		label.setFont(new Font("Georgia", Font.BOLD, 15));
		
		YearField = new JFormattedTextField(yearFormat);
		YearField.setToolTipText("M\u00E5nad och \u00E5r d\u00E5 ditt kreditkort g\u00E5r ut. Anges p\u00E5 formatet mm/\u00E5\u00E5 eller mm/\u00E5\u00E5\u00E5\u00E5");
		YearField.setText(session.getValue("validyear"));
		if(YearField.getText().length()==0){
			YearField.setText("����");
		}
		YearField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar() >= 48 && arg0.getKeyChar()<= 57){
					
				} else {
					arg0.consume();
				}
				if((MonthField.getText().length()==2 || MonthField.getText().length()==1) && (YearField.getText().length()==4 || YearField.getText().length()==2) ){
					ValidThroughLabel.setForeground(Color.GREEN);
				} else {
					ValidThroughLabel.setForeground(Color.RED);
				}
			}
		});
		YearField.setColumns(10);
		YearField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				session.put("validyear", YearField.getText());
				if(session.getErrorMessages().get("validyear")){
					ValidThroughLabel.setForeground(Color.RED);
				} else if ((MonthField.getText().length()==2 || MonthField.getText().length()==1) && (YearField.getText().length()==4 || YearField.getText().length()==2) ){
					ValidThroughLabel.setForeground(Color.GREEN);
				} else {
					ValidThroughLabel.setForeground(DEFAULT_COLOR);
				}
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
		
		BeginVerificationButton = new JButton("Nästa");
		BeginVerificationButton.setForeground(SELECTED_TEXT_COLOR);
		BeginVerificationButton.setBackground(SELECTED_BG_COLOR);
		BeginVerificationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((InformationPanel) BeginVerificationButton.getParent()).displayErrorMessage();
			}
//				session.put("deliveryday", "" + dateModel.getDay());
//				session.put("deliverymonth", "" + dateModel.getMonth());
//				session.put("deliveryyear", "" + dateModel.getYear());
//				//TODO: Load verification panel.
//				if (session.infoIsOk() && !shoppingCart.getItems().isEmpty()){ 
//					session.saveSession();
//				} else {
//					System.out.println("error");
//					JOptionPane.showMessageDialog(new JFrame("Ajd�"), "Det verkar ha blivit n�got fel med de uppgifter du matat in.\n Var god se �ver dessa och f�rs�k igen.");
//					//JDialog errorMessage = new JDialog(new JDialog(), "Det verkar vara n�got fel med de uppgifter som du matat in");
//					//TODO: Pop up som informerar anv�ndaren om att denna �r dum i huvudet
//				}
//			}
		});
		
		cancelButton = new JButton("Avbryt");
		cancelButton.setFont(DEFAULT_FONT);
		cancelButton.setBackground(CANCEL_TEXT_COLOR);
		cancelButton.setForeground(Color.WHITE);
		
		JCheckBox saveMyInfoBox = new JCheckBox("Spara mina uppgifter");
		saveMyInfoBox.setSelected(session.getSaveInfo());
		saveMyInfoBox.setFont(DEFAULT_FONT);
		saveMyInfoBox.setForeground(DEFAULT_COLOR);
		
		
		
		CCVField = new JFormattedTextField(CCVFormat);
		CCVField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar() >= 48 && arg0.getKeyChar()<= 57){	
				} else {
					arg0.consume();
				}
				if(CCVField.getText().length()==3){
					CCVLabel.setForeground(Color.GREEN);
				} else {
					CCVLabel.setForeground(Color.BLACK);
				}
			}
		});
		CCVField.setToolTipText("De tre sista siffrorna i koden som st\u00E5r p\u00E5 baksidan av ditt kreditkort");
		CCVField.setText(session.getValue("ccv"));
		if(CCVField.getText().length()==0){
			CCVField.setText("nnn");
		}
		CCVField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e){
				session.put("ccv", CCVField.getText());
				if(session.getErrorMessages().get("ccv")){
					CCVLabel.setForeground(Color.RED);
				} else {
					CCVLabel.setForeground(Color.GREEN);
					CCVLabel.setForeground(DEFAULT_COLOR);
				}
			}
		});
		
		PhoneNumberLabel = new JLabel("Telefonnummer:");
		PhoneNumberLabel.setToolTipText("Ditt telefonnummer p\u00E5 valfritt format.");
		PhoneNumberLabel.setFont(DEFAULT_FONT);
		PhoneNumberLabel.setForeground(DEFAULT_COLOR);
		
		
		JLabel EmailLabel = new JLabel("Email:");
		EmailLabel.setFont(DEFAULT_FONT);
		EmailLabel.setForeground(DEFAULT_COLOR);
		
		PhoneNumberField = new JFormattedTextField();
		PhoneNumberField.setToolTipText("Ditt telefonnummer p\u00E5 valfritt format.");
		PhoneNumberField.setText(session.getValue("phonenumber"));
		PhoneNumberField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e){
				session.put("phonenumber", PhoneNumberField.getText());
				if(session.getErrorMessages().get("phonenumber")){
					PhoneNumberLabel.setForeground(Color.RED);
				} else {
					PhoneNumberLabel.setForeground(DEFAULT_COLOR);
				}
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
		
		
		
		deliveryDateLabel = new JLabel("Leveransdatum");
		deliveryDateLabel.setToolTipText("Det datum som du vill att dina varor ska levereras");
		deliveryDateLabel.setFont(DEFAULT_FONT);
		deliveryDateLabel.setForeground(DEFAULT_COLOR);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(DEFAULT_COLOR);
		
		lblSiffror = new JLabel("(16 siffror)");
		lblSiffror.setFont(DEFAULT_FONT);
		lblSiffror.setForeground(DEFAULT_COLOR);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(FirstNameLabel)
						.addComponent(ValidThroughLabel)
						.addComponent(EmailLabel)
						.addComponent(CardTypeLabel)
						.addComponent(CardNumberLabel)
						.addComponent(CCVLabel)
						.addComponent(PhoneNumberLabel)
						.addComponent(ZIPLabel)
						.addComponent(CityLabel)
						.addComponent(AdressLabel)
						.addComponent(LastNameLabel))
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
							.addComponent(YearField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addComponent(FirstNameField)
						.addComponent(LastNameField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addComponent(saveMyInfoBox))
					.addGap(18)
					.addComponent(lblSiffror)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(deliveryDateLabel)
								.addComponent(deliveryDateChooser, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(cancelButton)
									.addGap(18)
									.addComponent(BeginVerificationButton))
								.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))))
					.addGap(85))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(LastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(LastNameLabel)
						.addComponent(deliveryDateLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
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
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(CardNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblSiffror))
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
							.addGap(58)
							.addComponent(saveMyInfoBox))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(deliveryDateChooser, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(BeginVerificationButton)
						.addComponent(cancelButton))
					.addGap(39))
		);
		setLayout(groupLayout);

	}
	
	public Session getSession() {
		return this.session;
	}
	
	public void addCancelButtonListener(ActionListener l) {
		this.cancelButton.addActionListener(l);
	}
	
	public void addNextButtonListener(ActionListener l) {
		this.BeginVerificationButton.addActionListener(l);
	}
	/**
	 * Displays an error message if any of the fields lacked the correct information
	 */
	public void displayErrorMessage() {
		Map<String,Boolean> errors = session.getErrorMessages();
		Set<String> keys = errors.keySet();
		
		errorLabel.setText("<html><body>Var v�nlig se �ver f�ljande information:<br>");
		errorLabel.setVisible(!session.infoIsOk());
		
		Iterator i =keys.iterator();
		while(i.hasNext()){
			String temp = (String) i.next();
			// TODO switch case, 
			if(errors.get(temp)){
				switch(temp){
				case("firstname"): temp = "F�rnamn"; FirstNameLabel.setForeground(Color.RED); break;
				case("lastname"): temp = "Efternamn"; LastNameLabel.setForeground(Color.RED); break;
				case("address"): temp = "Address"; AdressLabel.setForeground(Color.RED);break;
				case("cardnumber"): temp = "Kortnummer"; CardNumberLabel.setForeground(Color.RED); break;
				case("ccv"): temp = "CCV"; CCVLabel.setForeground(Color.RED); break;
				case("zipcode"): temp = "Postnummer"; ZIPLabel.setForeground(Color.RED); break;
				case("city"): temp = "Stad"; CityLabel.setForeground(Color.RED);break;
				case("validmonth"): temp = ""; ValidThroughLabel.setForeground(Color.RED);break;
				case("validyear"): temp = ""; ValidThroughLabel.setForeground(Color.RED); break;
				case("deliverydate"): temp = "Leveransdatum"; deliveryDateLabel.setForeground(Color.RED); break;
				case("phonenumber"): temp = "Telefonnummer"; PhoneNumberLabel.setForeground(Color.RED); break;
				}
				errorLabel.setText(errorLabel.getText() + temp +"<br>");
			}
		}
		errorLabel.setText(errorLabel.getText()+"</body></html>");
	}
}
