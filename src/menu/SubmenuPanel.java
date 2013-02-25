package menu;

import java.util.Map;

import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.ProductCategory;

public class SubmenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SubmenuPanel() {
		
	}
	
	/** 
	 * Add a button to the submenu panel
	 * @param button
	 */
	public void addButton(SubmenuButton button) {
		add(button);
	}
}
