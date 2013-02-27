package shoppingcart;

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
import java.awt.Font;

public class ShoppingcartView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ShoppingcartView() {
		setOpaque(false);
		setLayout(null);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(103, 0, 220, 720);
		add(contentPanel);
		
		JPanel summaryPanel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel optionPanel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(summaryPanel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
				.addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(summaryPanel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
		);
		
		JLabel totalPriceLabel = new JLabel("99kr");
		totalPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		totalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalPriceLabel.setForeground(new Color(169, 207, 109))
		;
		
		JLabel lblstVaror = new JLabel("20st varor");
		lblstVaror.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblstVaror.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_summaryPanel = new GroupLayout(summaryPanel);
		gl_summaryPanel.setHorizontalGroup(
			gl_summaryPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_summaryPanel.createSequentialGroup()
					.addGroup(gl_summaryPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_summaryPanel.createSequentialGroup()
							.addGap(136)
							.addComponent(totalPriceLabel, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_summaryPanel.createSequentialGroup()
							.addContainerGap(160, Short.MAX_VALUE)
							.addComponent(lblstVaror)))
					.addContainerGap())
		);
		gl_summaryPanel.setVerticalGroup(
			gl_summaryPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_summaryPanel.createSequentialGroup()
					.addGap(24)
					.addComponent(totalPriceLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblstVaror, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		summaryPanel.setLayout(gl_summaryPanel);
		
		JLabel lblLggTillVaror = new JLabel("L\u00E4gg till varor fr\u00E5n");
		
		JComboBox comboBox = new JComboBox();
		
		JButton toCounterButton = new JButton("Till kassa");
		
		JButton saveProductListButton = new JButton("Spara Varukorg");
		saveProductListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		JPanel productListPanel = new JPanel();
		scrollPane.setViewportView(productListPanel);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel listPanel = new JPanel();
		listPanel.setFocusable(false);
		listPanel.setBackground(new Color(236, 236, 236));
		listPanel.setBounds(78, 0, 25, 720);
		add(listPanel);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("img/shoppingcartIcon.png"));
		lblNewLabel.setBounds(42, 39, 61, 61);
		add(lblNewLabel);

	}
}
