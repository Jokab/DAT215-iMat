package productView;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import se.chalmers.ait.dat215.project.ProductCategory;
import javax.swing.BoxLayout;

public class ProductSidePanel extends JPanel {

	private JButton categoryButton;
	private JPanel subcategoriesPanel;
	/**
	 * Create the panel.
	 */
	public ProductSidePanel() {
		setOpaque(false);
		categoryButton = new JButton("New button");
		categoryButton.setContentAreaFilled(false);
		categoryButton.setOpaque(false);
		categoryButton.setBorder(null);
		categoryButton.setFont(new Font("Calibri", Font.BOLD, 16));
		categoryButton.setForeground(new Color(150, 150 ,150));
		
		subcategoriesPanel = new JPanel();
		subcategoriesPanel.setOpaque(false);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(subcategoriesPanel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(categoryButton))
					.addContainerGap(278, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(categoryButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(subcategoriesPanel, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		subcategoriesPanel.setLayout(new BoxLayout(subcategoriesPanel, BoxLayout.PAGE_AXIS));
	}
	
	public void setCategory(String category) {
		categoryButton.setText(category.toUpperCase());
	}
	
	/**
	 * Creates buttons for each subcategory in map, in the sidepanel.
	 * @param subcategories
	 * @param listener
	 */
	public void setSubcategories(Map<ProductCategory, String> subcategories, MouseListener listener){
		setSubcategories(subcategories, listener, null);
	}
	
	/**
	 * Creates buttons for each subcategory in map, in the sidepanel.
	 * @param subcategories
	 * @param listener
	 * @param activeSubcategory
	 */
	public void setSubcategories(Map<ProductCategory, String> subcategories, MouseListener listener, ProductCategory activeSubcategory){
		subcategoriesPanel.removeAll();
		for(Entry<ProductCategory, String> entry : subcategories.entrySet()) {
			ProductSidePanelButton btn = new ProductSidePanelButton(entry.getValue(), entry.getKey());
			btn.addMouseListener(listener);
			// Set button active
			if(entry.getKey() == activeSubcategory) {
				btn.setActive();
			}
			subcategoriesPanel.add(btn);
			subcategoriesPanel.add(Box.createRigidArea(new Dimension(0,5)));
		}
	}
}
