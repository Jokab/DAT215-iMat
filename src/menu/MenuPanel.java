package menu;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * A Specialized menu panel
 * @author Sebastian
 *
 */
public class MenuPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
	}
	
	/**
	 * Add a button to the menu panel
	 * @param button
	 */
	public void addButton(MenuButton button) {
		add(button);
	}
}
