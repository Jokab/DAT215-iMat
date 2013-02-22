import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;


public class MenuView extends JPanel {
	/**
	 * Create the panel.
	 */
	public MenuView() {
		setOpaque(false);
		
		// Create and add menu buttons
		MenuButton meat = new MenuButton("kött");
		MenuButton fish = new MenuButton("fisk");
		MenuButton vegetables = new MenuButton("frukt & grönt");
		MenuButton pantry = new MenuButton("skafferi");
		MenuButton drinks = new MenuButton("dryck");
		MenuButton dairy = new MenuButton("mejeri");
		setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
		
		add(meat);
		add(fish);
		add(vegetables);
		add(pantry);
		add(drinks);
		add(dairy);
	}
}
