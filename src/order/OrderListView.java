package order;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;

public class OrderListView extends JPanel {
	private final JPanel panel = new JPanel();
	private final JPanel rightPanel = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JLabel headerLabel = new JLabel("Orderhistorik");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel listNameLabel = new JLabel();
	private final JButton addToCartButton = new JButton(new ImageIcon("img/addShoppingListToCartButton.png"));
	private final JPanel detaildPanel;
	
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);

	/**
	 * Create the panel.
	 */
	public OrderListView() {
		
		headerLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		headerLabel.setForeground(new Color(150, 150 ,150));
		
		setBackground(Color.WHITE);
		listNameLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		listNameLabel.setForeground(new Color(150, 150 ,150));
		panel.setBorder(new MatteBorder(0, 0, 0, 2, new Color(225, 225, 225)));
		panel.setBackground(Color.WHITE);
		rightPanel.setBorder(new MatteBorder(0, 0, 0, 1, new Color(225, 225, 225)));
		rightPanel.setOpaque(false);
		rightPanel.setVisible(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(listNameLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(301, Short.MAX_VALUE))
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addGap(278)
					.addComponent(addToCartButton, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addComponent(listNameLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(addToCartButton, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addContainerGap())
		);
		addToCartButton.setContentAreaFilled(false);
		addToCartButton.setBorder(null);
		
		detaildPanel = new JPanel();
		detaildPanel.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(detaildPanel);
		detaildPanel.setLayout(new BoxLayout(detaildPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(gl_rightPanel);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(headerLabel)
					.addContainerGap(194, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(3)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(headerLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setSize(new Dimension(200, 400));
		scrollPane.setPreferredSize(new Dimension(200, 400));
		scrollPane.setMinimumSize(new Dimension(200, 400));
		scrollPane.setViewportView(panel_2);
		
		headerLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		panel.setLayout(gl_panel);
		add(panel);
		add(rightPanel);
		
		setVisible(true);
	}
	
	public JPanel getPanel() {
		return panel_2;
	}
	public JLabel getHeaderNameLabel() {
		return listNameLabel;
	}
	public JPanel getDetailedPanel() {
		return detaildPanel;
	}
	
	public void showRightPanel() {
		rightPanel.setVisible(true);
	}
	

	public void addNewAddToCartButtonListener(ActionListener l) {
		addToCartButton.addActionListener(l);
	}
	public JPanel getRightPanel() {
		return rightPanel;
	}
	public JButton getAddToCartButton() {
		return addToCartButton;
	}
}
