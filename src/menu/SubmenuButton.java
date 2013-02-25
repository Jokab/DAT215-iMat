package menu;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

/**
 * A specialized JButton. 
 * Has the ability to turn active.
 * 
 * When inactive: Transparent, no border, 16pt Segoe UI Light, rgb(184, 184, 184).
 * When active: Transparent, no border, 16pt Segoe UI Bold, rgb(150, 150, 150).
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
			setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			setForeground(new Color(184,184,184));
		} else {
			setFont(new Font("Segoe UI Bold", Font.PLAIN, 14));
			setForeground(new Color(150,150,150));
		}
		isToggled ^= true;
	}
}
