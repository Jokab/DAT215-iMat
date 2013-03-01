package shoppingCart;

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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShoppingCartProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel amountLabel;
	JLabel productLabel;
	JLabel priceLabel;
	private JPanel panel;
	private JButton increaseButton;
	private JButton decreaseButton;
	private JButton deleteButton;
	private ShoppingItem item;
	
	public ShoppingCartProductPanel(ShoppingItem item) {
		this.item = item;
		
		setOpaque(false);
		setSize(new Dimension(220, 30));
		setPreferredSize(new Dimension(235, 25));
		setMinimumSize(new Dimension(220, 30));
		setMaximumSize(new Dimension(220, 30));
		
		double amount = this.item.getAmount();
		amountLabel = new JLabel(amount + " " + this.item.getProduct().getUnit().substring(3));
		amountLabel.setBounds(0, 2, 41, 14);
		amountLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		productLabel = new JLabel(this.item.getProduct().getName());
		productLabel.setBounds(41, 2, 74, 14);
		productLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		priceLabel = new JLabel(amount * this.item.getProduct().getPrice() + "kr");
		priceLabel.setBounds(189, 2, 41, 14);
		priceLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(125, 0, 67, 19);
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
		deleteButton.setBounds(45, 0, 19, 19);
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
				ShoppingCartAdapter.getInstance().increaseItemAmount(item);
			} else if(e.getSource() == decreaseButton) {
				ShoppingCartAdapter.getInstance().decreaseItemAmount(item);
			} else if(e.getSource() == deleteButton) {
				ShoppingCartAdapter.getInstance().removeItem(item);
			}
			
		}
		
	}
}
