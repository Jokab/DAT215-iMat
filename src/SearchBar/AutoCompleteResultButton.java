package SearchBar;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Insets;

public class AutoCompleteResultButton extends JButton {

	public AutoCompleteResultButton() {
		setPreferredSize(new Dimension(350, 40));
		setBorder(null);
		setMargin(new Insets(0, 0, 0, 0));
		setMaximumSize(new Dimension(350, 40));
		setMinimumSize(new Dimension(350, 40));
		setFont(new Font("Calibri", Font.BOLD, 12));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setText("Visa alla resultat ");
		setBackground(new Color(216, 216, 216));
		setForeground(new Color(125, 125, 125));
	}
}
