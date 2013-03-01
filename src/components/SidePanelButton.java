package components;

import javax.swing.JButton;

import se.chalmers.ait.dat215.project.ProductCategory;

import java.awt.Color;
import java.awt.Font;
import java.util.Locale;

public class SidePanelButton extends JButton {

	private boolean isActive = false;
	private boolean isHover = false;
	public SidePanelButton(String title) {
		setContentAreaFilled(false);
		setBorder(null);
		setText(title.charAt(0) + title.substring(1).toLowerCase());
		setFont(new Font("Calibri Light", Font.PLAIN, 12));
		setForeground(new Color(150, 150, 150));
		setOpaque(false);
	}
	
	public void toggle() {
		if(isActive) {
			return;
		}
		setForeground(isHover? new Color(150, 150, 150): new Color(81, 81, 81));
		setFont(isHover? new Font("Calibri Light", Font.PLAIN, 12): new Font("Calibri", Font.BOLD, 12));
		
		isHover ^= true;
	}
	
	public void setActive() {
		isActive = true;
		setForeground(new Color(81, 81, 81));
		setFont(new Font("Calibri", Font.BOLD, 12));
	}
}
