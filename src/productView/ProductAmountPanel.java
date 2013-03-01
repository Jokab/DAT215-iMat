package productView;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ProductAmountPanel extends JPanel {
	private final JFormattedTextField textField;
	private final JButton increaseButton = new JButton(new ImageIcon(
			"img/plusIcon.png"));
	private final JButton decreaseButton = new JButton(new ImageIcon(
			"img/minusIcon.png"));

	/**
	 * Create the panel.
	 */
	public ProductAmountPanel() {
		setOpaque(false);
		setPreferredSize(new Dimension(80, 20));
		NumberFormat amountFormat = NumberFormat.getInstance();
		amountFormat.setMaximumIntegerDigits(3);
		amountFormat.setMinimumIntegerDigits(1);
		amountFormat.setMaximumFractionDigits(2);
		amountFormat.setMinimumFractionDigits(0);
		amountFormat.setGroupingUsed(false);
		textField = new JFormattedTextField(amountFormat);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		textField.setLocation(0, 0);
		textField.setValue(1);
		textField.setSize(new Dimension(38, 19));
		textField.setMaximumSize(new Dimension(15, 15));
		textField.setPreferredSize(new Dimension(10, 20));
		textField.setMinimumSize(new Dimension(15, 15));
		textField.setColumns(10);
		increaseButton.setBorder(null);
		increaseButton.setBounds(38, 0, 19, 19);

		increaseButton.setActionCommand("+");
		increaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increaseAmount();
			}
		});
		decreaseButton.setBorder(null);

		decreaseButton.setBounds(57, 0, 19, 19);
		decreaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decreaseAmount();
			}
		});

        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < 49 || e.getKeyChar() > 57) {
                    e.consume();
                }
            }
        });

		setLayout(null);
		add(textField);
		add(increaseButton);
		add(decreaseButton);

	}

	public void increaseAmount() {
		double curr = Double.parseDouble(textField.getText());
		curr += 1;
		this.textField.setValue(curr);
	}

	public void decreaseAmount() {
		double curr = Double.parseDouble(textField.getText());
		if ((curr - 1) > 0) {
			curr -= 1;
			this.textField.setValue(curr);
		}
	}

	/**
	 * Returns the integer in the amount text field.
	 * 
	 * @return amount
	 */
	public double getAmount() {
		return Double.parseDouble(textField.getText());
	}

}
