package shoppingCart;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import java.awt.Cursor;
import javax.swing.JSeparator;

import core.MainController;
import javax.swing.ScrollPaneConstants;

public class ShoppingcartView extends JPanel implements PropertyChangeListener {

	private ShoppingCartAdapter model = null;

	private JPanel productListPanel;
	private JPanel summaryPanel;
	private JLabel totalPriceLabel;
	private JLabel totalAmountLabel;

	private JButton listButton;
	private JButton shoppingcartIconButton;
	private JButton minimizeLabelButton;
	private JButton toCounterButton;
	private JButton saveProductListButton;
	private JButton addToCartButton;

	private final Color DEFAULT_BACKGROUND = new Color(253, 253, 253);
	private JLabel lblKundvagn;
	private MainController mainController;

	
	

	/**
	 * Create the panel.
	 * @param mainController 
	 */
	public ShoppingcartView(MainController mainController) {
		this.mainController = mainController;
		setOpaque(false);
		setLayout(null);

		model = ShoppingCartAdapter.getInstance();
		model.addListener(this);

		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(91, 0, 232, 720);
		contentPanel.setBackground(DEFAULT_BACKGROUND);
		add(contentPanel);

		// Create panels in contentPanel
		summaryPanel = new JPanel();
		summaryPanel.setOpaque(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);

		productListPanel = new JPanel();
		scrollPane.setViewportView(productListPanel);
		productListPanel.setLayout(new BoxLayout(productListPanel,
				BoxLayout.Y_AXIS));
		productListPanel.setBackground(DEFAULT_BACKGROUND);
		JPanel optionPanel = new JPanel();
		optionPanel.setOpaque(false);
		addToCartButton = new JButton();
		addToCartButton.addActionListener(new AddListToCartListener());
		addToCartButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addToCartButton.setText("L\u00E4gg till fr\u00E5n ink\u00F6pslista");
		toCounterButton = new JButton("Till kassa");
		toCounterButton.setEnabled(false);
		toCounterButton.addActionListener(new CheckoutButtonListener());
		
		this.saveProductListButton = new JButton("Spara Varukorg");
		saveProductListButton.setEnabled(false);
		saveProductListButton.addActionListener(new SaveProductButtonListener());

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel
				.createParallelGroup(Alignment.LEADING)
				.addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, 220,
						Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 220,
						Short.MAX_VALUE)
				.addComponent(summaryPanel, GroupLayout.DEFAULT_SIZE, 220,
						Short.MAX_VALUE));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addComponent(summaryPanel, GroupLayout.PREFERRED_SIZE,
								124, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								471, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(optionPanel, GroupLayout.DEFAULT_SIZE,
								113, Short.MAX_VALUE)));

		// Components in summaryPanel
		totalPriceLabel = new JLabel("0");
		totalPriceLabel.setBounds(0, 70, 189, 22);
		totalPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		totalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalPriceLabel.setForeground(new Color(169, 207, 109));
		totalAmountLabel = new JLabel("Inga produkter");
		totalAmountLabel.setBounds(0, 97, 203, 16);
		totalAmountLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		totalAmountLabel.setForeground(new Color(155, 155, 155));
		totalAmountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		lblKundvagn = new JLabel("KUNDVAGN");
		lblKundvagn.setBounds(76, 44, 127, 25);
		lblKundvagn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKundvagn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblKundvagn.setForeground(new Color(155, 155, 155));

		JLabel priceUnitLabel = new JLabel("kr");
		priceUnitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceUnitLabel.setForeground(new Color(155, 155, 155));
		priceUnitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		priceUnitLabel.setBounds(189, 76, 14, 16);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(225, 225, 225));
		separator.setBackground(new Color(236, 236, 236));
		separator.setBounds(10, 122, 212, 2);

		summaryPanel.setLayout(null);
		summaryPanel.add(totalAmountLabel);
		summaryPanel.add(lblKundvagn);
		summaryPanel.add(totalPriceLabel);
		summaryPanel.add(priceUnitLabel);
		summaryPanel.add(separator);

		GroupLayout gl_optionPanel = new GroupLayout(optionPanel);
		gl_optionPanel
				.setHorizontalGroup(gl_optionPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_optionPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_optionPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																addToCartButton,
																GroupLayout.DEFAULT_SIZE,
																212,
																Short.MAX_VALUE)
														.addGroup(
																gl_optionPanel
																		.createSequentialGroup()
																		.addComponent(
																				saveProductListButton,
																				0,
																				0,
																				Short.MAX_VALUE)
																		.addGap(18)
																		.addComponent(
																				toCounterButton)))
										.addContainerGap()));
		gl_optionPanel.setVerticalGroup(gl_optionPanel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						Alignment.TRAILING,
						gl_optionPanel
								.createSequentialGroup()
								.addComponent(addToCartButton,
										GroupLayout.PREFERRED_SIZE, 34,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										45, Short.MAX_VALUE)
								.addGroup(
										gl_optionPanel
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(toCounterButton)
												.addComponent(
														saveProductListButton))
								.addContainerGap()));
		optionPanel.setLayout(gl_optionPanel);

		contentPanel.setLayout(gl_contentPanel);

		shoppingcartIconButton = new JButton(new ImageIcon(
				"img/shoppingcartIcon.png"));
		shoppingcartIconButton.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		shoppingcartIconButton.setContentAreaFilled(false);
		shoppingcartIconButton.setBorder(null);
		shoppingcartIconButton.setBounds(30, 39, 61, 61);

		listButton = new JButton();
		listButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listButton.setRolloverEnabled(false);
		listButton.setRequestFocusEnabled(false);
		listButton.setFocusPainted(false);
		listButton.setFocusable(false);
		listButton.setBorder(null);
		listButton.setBackground(new Color(236, 236, 236));
		listButton.setBounds(65, 0, 26, 720);

		minimizeLabelButton = new JButton(new ImageIcon(
				"img/shoppingcartMaximizedLabel.png"));
		minimizeLabelButton.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizeLabelButton.setRolloverEnabled(false);
		minimizeLabelButton.setRequestFocusEnabled(false);
		minimizeLabelButton.setOpaque(false);
		minimizeLabelButton.setContentAreaFilled(false);
		minimizeLabelButton.setBorder(null);
		minimizeLabelButton.setBounds(69, 283, 16, 154);

		add(shoppingcartIconButton);
		add(minimizeLabelButton);
		add(listButton);
	}

	private void updateSummary() {
		totalAmountLabel.setText(model.getTotalProductsAmount()
				+ "st produkter");
		totalPriceLabel.setText(model.getTotal() + "");
	}

	private void updateItemList() {
		productListPanel.removeAll();
		List<ShoppingItem> tmpList = model.getItems();
		for (ShoppingItem item : tmpList) {
			productListPanel.add(new ShoppingCartProductPanel(item));
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		updateSummary();
		updateItemList();
		updateBottomButtons();
	}

	private void updateBottomButtons() {
		if(model.getItems() == null || model.getItems().isEmpty()) {
			saveProductListButton.setEnabled(false);
			toCounterButton.setEnabled(false);
		} else {
			saveProductListButton.setEnabled(true);
			toCounterButton.setEnabled(true);
		}
	}

	public void addPopoutListener(ActionListener l) {
		shoppingcartIconButton.addActionListener(l);
		listButton.addActionListener(l);
		minimizeLabelButton.addActionListener(l);
	}
	
	public void addCounterButtonListener(ActionListener l) {
		toCounterButton.addActionListener(l);
	}
	

	private class CheckoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			toCheckout();
		}

	}
	
	private class SaveProductButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			createSaveAsListPopup();
		}
		
	}
	
	private class AddListToCartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			createAddListToCartPopup();
		}
		
	}

	public void toCheckout() {
		this.mainController.initCheckoutController();
	}

	public void createAddListToCartPopup() {
		AddListToCartPopupController controller = new AddListToCartPopupController(mainController);
	}

	public void createSaveAsListPopup() {
		SaveAsShoppingListPopUpController controller = new SaveAsShoppingListPopUpController(mainController);
	}
}
