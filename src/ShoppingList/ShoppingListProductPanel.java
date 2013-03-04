package ShoppingList;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import se.chalmers.ait.dat215.project.ShoppingItem;

public class ShoppingListProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel amountLabel;
	JLabel productLabel;
	JLabel priceLabel;
	private JPanel buttonPanel;
	private JButton increaseButton;
	private JButton decreaseButton;
	private JButton deleteButton;
	private ShoppingItem item;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public ShoppingListProductPanel(ShoppingItem item) {
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
		
		buttonPanel = new JPanel();
		buttonPanel.setVisible(false);
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(125, 0, 67, 19);
		buttonPanel.setLayout(null);
		ActionListener l = new ShoppingListButtonListener();
		
		increaseButton = new JButton(new ImageIcon("img/plusIcon.png"));
		buttonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonPanel.getParent().getMouseListeners()[0].mouseEntered(e);
			}
			public void mouseExited(MouseEvent e) {
				
			}
		});
		
		increaseButton.setContentAreaFilled(false);
		increaseButton.setBounds(0, 0, 19, 19);
		increaseButton.setBorder(null);
		increaseButton.addActionListener(l);
		buttonPanel.add(increaseButton);
		
		decreaseButton = new JButton(new ImageIcon("img/minusIcon.png"));
		decreaseButton.setContentAreaFilled(false);
		decreaseButton.setBounds(19, 0, 19, 19);
		decreaseButton.setBorder(null);
		decreaseButton.addActionListener(l);
		buttonPanel.add(decreaseButton);
		
		deleteButton = new JButton(new ImageIcon("img/deleteIcon.png"));
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBounds(45, 0, 19, 19);
		deleteButton.setBorder(null);
		deleteButton.addActionListener(l);
		buttonPanel.add(deleteButton);

		setLayout(null);
		add(amountLabel);
		add(productLabel);
		add(buttonPanel);
		add(priceLabel);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonPanel.setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				buttonPanel.setVisible(false);
			}
		});
		increaseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonPanel.getMouseListeners()[0].mouseEntered(e);
			}
		});
		decreaseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonPanel.getMouseListeners()[0].mouseEntered(e);
			}
		});
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonPanel.getMouseListeners()[0].mouseEntered(e);
			}
		});
	}
	
	private class ShoppingListButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == increaseButton) {
				pcs.firePropertyChange("increased", null, item);
			} else if(e.getSource() == decreaseButton) {
				pcs.firePropertyChange("decreased", null, item);
			} else if(e.getSource() == deleteButton) {
				pcs.firePropertyChange("removed", null, item);
			}
		}
	}
	public JButton getDecreaseButton() {
		return decreaseButton;
	}
	public JButton getDeleteButton() {
		return deleteButton;
	}
	public JButton getIncreaseButton() {
		return increaseButton;
	}
	public void addObserver(
			PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
}

