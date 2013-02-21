import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class ContentView extends JPanel {
	private final SearchBar searchBar = new SearchBar();

	/**
	 * Create the panel.
	 */
	public ContentView() {
		setBackground(Color.ORANGE);
		setLayout(null);
		searchBar.setBounds(130, 85, 612, 99);
		
		add(searchBar);

	}
}
