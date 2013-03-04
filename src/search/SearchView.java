package search;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import se.chalmers.ait.dat215.project.Product;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;

import productView.ProductView;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;

public class SearchView extends JPanel {

	private final JPanel resultList;
	private final JLabel headerLabel;
	private final JComboBox comboBox;
	
	/**
	 * Create the panel.
	 */
	public SearchView(SearchFilterOption[] filters) {
		setOpaque(false);
		
		comboBox = new JComboBox(filters);
		comboBox.setSelectedIndex(1);
		
		JPanel resultContainer = new JPanel();
		resultContainer.setOpaque(false);
		resultContainer.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(225, 225, 225)));
		
		resultList = new JPanel();
		resultList.setBackground(Color.WHITE);
		
		headerLabel = new JLabel("S\u00F6kresultat");
		headerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		headerLabel.setForeground(new Color(150, 150, 150));
		
		JLabel lblSoteraEfter = new JLabel("Sotera efter");
		lblSoteraEfter.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSoteraEfter.setForeground(new Color(150, 150, 150));
		
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 12));
		comboBox.setForeground(new Color(150, 150, 150));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBorder(null);
		comboBox.setOpaque(false);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(resultContainer, GroupLayout.PREFERRED_SIZE, 749, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSoteraEfter)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addComponent(headerLabel))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(headerLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSoteraEfter)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(resultContainer, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		GroupLayout gl_resultContainer = new GroupLayout(resultContainer);
		gl_resultContainer.setHorizontalGroup(
			gl_resultContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resultContainer.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 749, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_resultContainer.setVerticalGroup(
			gl_resultContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resultContainer.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
		);
	
		scrollPane.setViewportView(resultList);
		resultList.setLayout(new BoxLayout(resultList, BoxLayout.Y_AXIS));
		resultContainer.setLayout(gl_resultContainer);
		setLayout(groupLayout);

	}
	
	public void setHeaderLabel(String searchString) {
		headerLabel.setText("Sökresultat för \"" + searchString + "\"");
	}
	
	public void renderList(List<Product> products) {
		for (Product p : products) {
			resultList.add(new ProductView(p)); 
		}
	}
	
	public void setSelectedIndexComboBox(int i) {
		comboBox.setSelectedIndex(i);
	}
	
	public void addComboBoxListener(ActionListener l) {
		comboBox.addActionListener(l);
	}
	
}
