package ShoppingList;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CopyOfShoppingListProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel amountLabel;
	private JLabel productLabel;
	private JLabel priceLabel;
	private JPanel panel;
	private JButton increaseButton;
	private JButton decreaseButton;
	private JButton deleteButton;
	private ShoppingItem item;
	
	private final Color NORMAL_TEXT_COLOR = new Color(140,140,140);

	public CopyOfShoppingListProductPanel(ShoppingItem item) {
		this.item = item;
		
		setOpaque(false);
		setPreferredSize(new Dimension(446, 38));
		setMaximumSize(new Dimension(460, 38));
		setMinimumSize(new Dimension(460, 38));
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(225, 225, 225)));
		
		double amount = this.item.getAmount();
		amountLabel = new JLabel(amount + " " + this.item.getProduct().getUnit().substring(3));
		amountLabel.setBounds(10, 13, 57, 14);
		amountLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		amountLabel.setForeground(NORMAL_TEXT_COLOR);
		
		productLabel = new JLabel(this.item.getProduct().getName());
		productLabel.setBounds(77, 13, 74, 14);
		productLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		productLabel.setForeground(NORMAL_TEXT_COLOR);
		
		priceLabel = new JLabel(amount * this.item.getProduct().getPrice() + "kr");
		priceLabel.setBounds(396, 13, 41, 14);
		priceLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLabel.setForeground(NORMAL_TEXT_COLOR);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(256, 11, 106, 19);
		panel.setLayout(null);
		
		ActionListener l = new ShoppingCartButtonListener();
		
		increaseButton = new JButton(new ImageIcon("img/plusIcon.png"));
		increaseButton.setContentAreaFilled(false);
		increaseButton.setBounds(0, 0, 19, 19);
		increaseButton.setBorder(null);
		increaseButton.addActionListener(l);
		panel.add(increaseButton);
		
		decreaseButton = new JButton(new ImageIcon("img/minusIcon.png"));
		decreaseButton.setContentAreaFilled(false);
		decreaseButton.setBounds(19, 0, 19, 19);
		decreaseButton.setBorder(null);
		decreaseButton.addActionListener(l);
		panel.add(decreaseButton);
		
		deleteButton = new JButton(new ImageIcon("img/deleteIcon.png"));
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBounds(87, 0, 19, 19);
		deleteButton.setBorder(null);
		deleteButton.addActionListener(l);
		panel.add(deleteButton);
		
		panel.setVisible(true);
		
		setLayout(null);
		add(amountLabel);
		add(productLabel);
		add(panel);
		add(priceLabel);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setVisible(true);
			}
			@Override 
			public void mouseExited(MouseEvent e) {
				panel.setVisible(false);
			}
		});
	}
	
	private class ShoppingCartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == increaseButton) {
			} else if(e.getSource() == decreaseButton) {
			} else if(e.getSource() == deleteButton) {
			}
			
		}
		
	}
}
