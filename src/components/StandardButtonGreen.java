package components;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Insets;

public class StandardButtonGreen extends JButton {
	
	public StandardButtonGreen() {
		super();
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setBackground(new Color(177,211,114));
		setForeground(Color.white);
	}
	public StandardButtonGreen(String text) {
		this();
		setText(text);
	}
	public StandardButtonGreen(Icon icon) {
		this();
		setIcon(icon);
	}
	
	@Override
	public void setEnabled(boolean setEnabled) {
		setForeground(setEnabled? Color.white: new Color(180, 180, 180));
		setBackground(setEnabled?new Color(177,211,114): Color.white);
		super.setEnabled(setEnabled);
	}

}
