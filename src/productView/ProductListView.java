package productView;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import se.chalmers.ait.dat215.project.ProductCategory;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

import ProductSearch.OrderByNameAscending;
import ProductSearch.OrderByNameDescending;
import ProductSearch.OrderByPriceAscending;
import ProductSearch.OrderByPriceDescending;
import ProductSearch.SearchFilterOption;


public class ProductListView extends JPanel {
	private final JPanel viewPanel = new JPanel();
	private final ProductSidePanel productSidePanel;
	private final JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public ProductListView(SearchFilterOption[] comboBoxValues) {
		setOpaque(false);
		setBorder(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		productSidePanel = new ProductSidePanel();
		productSidePanel.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(225, 225, 225)));
		
		JLabel label = new JLabel("Sotera efter");
		label.setForeground(new Color(150, 150, 150));
		label.setFont(new Font("Calibri", Font.PLAIN, 12));
		
		comboBox = new JComboBox(comboBoxValues);
		comboBox.setOpaque(false);
		comboBox.setForeground(new Color(150, 150, 150));
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 12));
		comboBox.setBorder(null);
		comboBox.setBackground(Color.WHITE);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(productSidePanel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 669, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(productSidePanel, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)))
					.addContainerGap())
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
	
	public void setSelectedIndexComboBox(int i) {
		comboBox.setSelectedIndex(i);
	}
	
	public void addComboBoxListener(ActionListener l) {
		comboBox.addActionListener(l);
	}
}
