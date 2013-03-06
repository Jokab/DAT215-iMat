package components;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Insets;

public class StandardButton extends JButton {
	
	public StandardButton() {
		super();
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setBackground(new Color(245, 245, 245));
		setForeground(new Color(80, 80, 80));
	}
	public StandardButton(String text) {
		this();
		setText(text);
	}
	public StandardButton(Icon icon) {
		this();
		setIcon(icon);
	}
	
	@Override
	public void setEnabled(boolean setEnabled) {
		setForeground(setEnabled? new Color(80, 80, 80): new Color(180, 180, 180));
		setBackground(setEnabled?new Color(247, 247, 247): Color.white);
		super.setEnabled(setEnabled);
	}

}
