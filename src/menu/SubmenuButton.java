package menu;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

/**
 * A specialized JButton. 
 * Has the ability to turn active.
 * 
 * When inactive: Transparent, no border, 11pt Segoe UI Light, rgb(144, 144, 144).
 * When active: Segoe UI Bold, rgb(62, 62, 62).
 * 
 * @author Sebastian Blomberg
 *
 */
public class SubmenuButton extends JButton {

	private boolean isToggled = true;
	
	/**
	 * Creates a new menu button
	 * @param text displayed on button
	 */
	public SubmenuButton (String text) {
		setText(text);
		setContentAreaFilled(false);
		setBorder(null);
		toggle();
	}
	
	/**
	 * Toggles the button to be active/inactive.
	 */
	public void toggle() {
		if(isToggled) {
			setFont(new Font("Segoe UI", Font.PLAIN, 11));
			setForeground(new Color(144,144,144));
		} else {
			setFont(new Font("Segoe UI Bold", Font.PLAIN, 11));
			setForeground(new Color(62, 62, 62));
		}
		isToggled ^= true;
	}
}
