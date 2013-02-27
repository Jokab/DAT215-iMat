package productView;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

public class ProductAmountPanel extends JPanel {
	private final JLabel label = new JLabel("Antal: ");
	private final JFormattedTextField textField;
	private final JButton button = new JButton("+");
	private final JButton button_1 = new JButton("-");

	/**
	 * Create the panel.
	 */
	public ProductAmountPanel() {
		setPreferredSize(new Dimension(210, 50));
		setMaximumSize(new Dimension(200, 50));
		setSize(new Dimension(200, 50));
		setMinimumSize(new Dimension(200, 50));
		
		NumberFormat amountFormat = NumberFormat.getInstance();
		amountFormat.setMaximumIntegerDigits(3);
		amountFormat.setMinimumIntegerDigits(1);
		amountFormat.setMaximumFractionDigits(2);
		amountFormat.setMinimumFractionDigits(0);
		amountFormat.setGroupingUsed(false);
		textField = new JFormattedTextField(amountFormat);
		textField.setValue(1);
		textField.setSize(new Dimension(15, 15));
		textField.setMaximumSize(new Dimension(15, 15));
		textField.setPreferredSize(new Dimension(10, 20));
		textField.setMinimumSize(new Dimension(15, 15));
		textField.setColumns(10);
		
		button.setForeground(Color.BLACK);
		button.setBackground(new Color(40, 190, 90));
		button.setActionCommand("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increaseAmount();
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decreaseAmount();
			}
		});
		button_1.setPreferredSize(new Dimension(44, 25));
		button_1.setForeground(Color.RED);
		button_1.setBackground(Color.BLACK);
		button_1.setActionCommand("-");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(209))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(label))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(button)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	public void increaseAmount() {
		double curr = (double) textField.getValue();
		curr += 1;
		this.textField.setValue(curr);
	}
	
	public void decreaseAmount() {
		double curr = (double) textField.getValue();
		if ((curr - 1) > 0) {
			curr -= 1;
			this.textField.setValue(curr);
		}
	}
	
	/**
	 * Returns the integer in the amount text field.
	 * @return amount
	 */
	public double getAmount() {
		return (double) textField.getValue();
	}

}
