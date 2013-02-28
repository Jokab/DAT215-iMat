package productView;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;


public class ProductListView extends JPanel {
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel viewPanel = new JPanel();
	

	/**
	 * Create the panel.
	 */
	public ProductListView() {
		setOpaque(false);
		setBorder(null);
		setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scrollPane);
		viewPanel.setBackground(Color.WHITE);
		viewPanel.setBorder(null);
		
		scrollPane.setViewportView(viewPanel);
		viewPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
	}
	
	/**
	 * Returns the JPanel actually used to display the results.
	 * 
	 * @return The results panel.
	 */
	public JPanel getViewPanel() {
		return this.viewPanel;
	}

}
