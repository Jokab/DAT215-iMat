package ShoppingList;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class ShoppingListView extends JPanel {
	private final JPanel panel = new JPanel();
	private final JSeparator separator = new JSeparator();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JButton newListButton = new JButton("Ny inköpslista");
	private final JLabel lblInkpslistor = new JLabel("Inköpslistor");
	private final JPanel detailedPanel = new JPanel();
	private final JButton removeListButton = new JButton("Ta bort lista");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblNewLabel = new JLabel("");
	private final JButton addToCartButton = new JButton("Lägg till i kundvagn");

	/**
	 * Create the panel.
	 */
	public ShoppingListView() {
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 499, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(removeListButton)
							.addPreferredGap(ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
							.addComponent(addToCartButton))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)))
					.addContainerGap())
				.addComponent(detailedPanel, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel)
					.addGap(26)
					.addComponent(detailedPanel, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeListButton)
						.addComponent(addToCartButton))
					.addContainerGap())
		);
		removeListButton.setEnabled(false);
		detailedPanel.setLayout(new BoxLayout(detailedPanel, BoxLayout.PAGE_AXIS));
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInkpslistor)
					.addContainerGap(166, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(166, Short.MAX_VALUE)
					.addComponent(newListButton)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(3)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
		);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblInkpslistor)
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(newListButton)
					.addContainerGap())
		);
		scrollPane.setSize(new Dimension(200, 400));
		scrollPane.setPreferredSize(new Dimension(200, 400));
		scrollPane.setMinimumSize(new Dimension(200, 400));
		scrollPane.setViewportView(panel_2);
		panel_2.setMaximumSize(new Dimension(32322, 24512));
		panel_2.setSize(new Dimension(200, 400));
		panel_2.setMinimumSize(new Dimension(200, 400));
		panel_2.setPreferredSize(new Dimension(200, 500));
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		lblInkpslistor.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.setLayout(gl_panel);
		separator.setOrientation(SwingConstants.VERTICAL);
		setLayout(groupLayout);
		
		setVisible(true);
	}
	
	public JPanel getPanel() {
		return panel_2;
	}
	public JLabel getHeaderNameLabel() {
		return lblNewLabel;
	}
	public JPanel getDetailedPanel() {
		return detailedPanel;
	}
	
	public void addRemoveButtonActionListener(ActionListener listener) {
		removeListButton.addActionListener(listener);
	}

	public JButton getRemoveButton() {
		return this.removeListButton;
	}
	public JButton getAddToCartButton() {
		return addToCartButton;
	}
}
