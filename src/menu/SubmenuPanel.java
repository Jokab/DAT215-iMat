package menu;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 * A Specialized menu panel
 * @author Sebastian
 *
 */
public class SubmenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SubmenuPanel() {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
	}
	
	/** 
	 * Add a button to the submenu panel
	 * @param button
	 */
	public void addButton(SubmenuButton button) {
		add(button);
	}
}
