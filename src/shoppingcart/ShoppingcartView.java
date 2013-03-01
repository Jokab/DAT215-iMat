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

import se.chalmers.ait.dat215.project.ShoppingItem;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import java.awt.Cursor;

public class ShoppingcartView extends JPanel implements PropertyChangeListener {

	private ShoppingCartAdapter model = null;
	
	private JPanel productListPanel;
	private JPanel summaryPanel;
	private JLabel totalPriceLabel;
	private JLabel totalAmountLabel;
	
	private JButton listButton; 
	private JButton shoppingcartIconButton;
	private JButton minimizeLabelButton;
	
	private final Color DEFAULT_BACKGROUND = new Color(251, 251, 251);
	private JLabel lblKundvagn;
	
	/**
	 * Create the panel.
	 */
	public ShoppingcartView() {
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
		totalPriceLabel = new JLabel();
		totalPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		totalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalPriceLabel.setForeground(new Color(169, 207, 109));
		totalAmountLabel = new JLabel();
		totalAmountLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		totalAmountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		productListPanel = new JPanel();
		scrollPane.setViewportView(productListPanel);
		productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
		productListPanel.setBackground(DEFAULT_BACKGROUND);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setOpaque(false);
		JLabel lblLggTillVaror = new JLabel("L\u00E4gg till varor fr\u00E5n");
		JComboBox comboBox = new JComboBox();
		JButton toCounterButton = new JButton("Till kassa");
		JButton saveProductListButton = new JButton("Spara Varukorg");
		saveProductListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
				.addComponent(summaryPanel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(summaryPanel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
		);
		
		lblKundvagn = new JLabel("KUNDVAGN");
		lblKundvagn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKundvagn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblKundvagn.setForeground(new Color(155, 155, 155));
		
		GroupLayout gl_summaryPanel = new GroupLayout(summaryPanel);
		gl_summaryPanel.setHorizontalGroup(
			gl_summaryPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_summaryPanel.createSequentialGroup()
					.addGroup(gl_summaryPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(totalAmountLabel, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
						.addGroup(gl_summaryPanel.createSequentialGroup()
							.addGap(76)
							.addComponent(lblKundvagn, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
						.addComponent(totalPriceLabel, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
					.addGap(29))
		);
		gl_summaryPanel.setVerticalGroup(
			gl_summaryPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_summaryPanel.createSequentialGroup()
					.addGap(44)
					.addComponent(lblKundvagn, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(totalPriceLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(totalAmountLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		summaryPanel.setLayout(gl_summaryPanel);
		
		GroupLayout gl_optionPanel = new GroupLayout(optionPanel);
		gl_optionPanel.setHorizontalGroup(
			gl_optionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_optionPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_optionPanel.createSequentialGroup()
							.addComponent(lblLggTillVaror)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 106, Short.MAX_VALUE))
						.addGroup(gl_optionPanel.createSequentialGroup()
							.addComponent(saveProductListButton, 0, 0, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(toCounterButton)))
					.addContainerGap())
		);
		gl_optionPanel.setVerticalGroup(
			gl_optionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_optionPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLggTillVaror)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(gl_optionPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(toCounterButton)
						.addComponent(saveProductListButton))
					.addContainerGap())
		);
		optionPanel.setLayout(gl_optionPanel);
		
		
		contentPanel.setLayout(gl_contentPanel);
		
		shoppingcartIconButton = new JButton(new ImageIcon("img/shoppingcartIcon.png"));
		shoppingcartIconButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		
		minimizeLabelButton = new JButton(new ImageIcon("img/shoppingcartMaximizedLabel.png"));
		minimizeLabelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		totalAmountLabel.setText(model.getTotalProductsAmount() + "st varor");
		totalPriceLabel.setText(model.getTotal() + "kr");
	}
	
	private void updateItemList() {
		productListPanel.removeAll();
		List<ShoppingItem> tmpList = model.getItems();
		for(ShoppingItem item : tmpList) {
			productListPanel.add(new ShoppingCartProductPanel(item));
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		updateSummary();
		updateItemList();
	}
	
	public void addListListener(ActionListener l) {
		shoppingcartIconButton.addActionListener(l);
		listButton.addActionListener(l);
		minimizeLabelButton.addActionListener(l);
	}
}
