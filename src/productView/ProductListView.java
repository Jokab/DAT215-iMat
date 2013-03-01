package productView;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import se.chalmers.ait.dat215.project.ProductCategory;
import javax.swing.border.MatteBorder;


public class ProductListView extends JPanel {
	private final JPanel viewPanel = new JPanel();
	private final ProductSidePanel productSidePanel;

	/**
	 * Create the panel.
	 */
	public ProductListView() {
		setOpaque(false);
		setBorder(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		productSidePanel = new ProductSidePanel();
		productSidePanel.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(225, 225, 225)));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(productSidePanel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 669, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(productSidePanel, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(124, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
		);
		scrollPane.setViewportView(viewPanel);
		viewPanel.setBackground(Color.WHITE);
		viewPanel.setBorder(null);
		viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.PAGE_AXIS));
		setLayout(groupLayout);
	}
	
	/**
	 * Returns the JPanel actually used to display the results.
	 * 
	 * @return The results panel.
	 */
	public JPanel getViewPanel() {
		return this.viewPanel;
	}
	
	public void setCurrentCategory(String category) {
		productSidePanel.setCategory(category);
	}
	
	public void setSubcategories(Map<ProductCategory, String> subcategories, MouseListener listener) {
		productSidePanel.setSubcategories(subcategories, listener);
	}
	
	public void setSubcategories(Map<ProductCategory, String> subcategories, MouseListener listener, ProductCategory activeSubcategory) {
		productSidePanel.setSubcategories(subcategories, listener, activeSubcategory);
	}
}
