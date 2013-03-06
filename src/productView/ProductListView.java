package productView;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.BoxLayout;
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

import ProductSearch.SearchFilterOption;


public class ProductListView extends JPanel {
	private final JPanel viewPanel = new JPanel();
	private final ProductSidePanel productSidePanel;
	private final JComboBox comboBox;
	private final JLabel sortLabel;
	private final JScrollPane scrollPane = new JScrollPane();
	
	/**
	 * Create the panel.
	 */
	public ProductListView(SearchFilterOption[] comboBoxValues) {
		setOpaque(false);
		setBorder(null);
		
		scrollPane.setBorder(null);
		
		productSidePanel = new ProductSidePanel();
		productSidePanel.setBorder(new MatteBorder(0, 0, 0, 1, new Color(225, 225, 225)));
		
		sortLabel = new JLabel("Sortera efter");
		sortLabel.setForeground(new Color(150, 150, 150));
		sortLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		sortLabel.setVisible(true);
		
		comboBox = new JComboBox(comboBoxValues);
		comboBox.setOpaque(false);
		comboBox.setForeground(new Color(150, 150, 150));
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 12));
		comboBox.setBorder(null);
		comboBox.setBackground(Color.WHITE);
		comboBox.setVisible(true);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(productSidePanel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(sortLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
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
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(sortLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE))
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
	
	public JComboBox getComboBox() {
		return this.comboBox;
	}
	
	public JLabel getSortLabel() {
		return this.sortLabel;
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
	
	public void setScrollPaneVertical(int distanceFromTop, int maximumHeight) {
		scrollPane.getVerticalScrollBar().setMaximum(maximumHeight);
		scrollPane.getVerticalScrollBar().setValue(distanceFromTop);
	}
	
	public void revalidateListPanel() {
		viewPanel.revalidate();
	}
}
