import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;


public class HeaderView extends JPanel {
	private final JLabel logoPanel = new JLabel();
	private final MenuView menuView = new MenuView();
	/**
	 * Create the panel.
	 */
	public HeaderView() {
		setOpaque(false);
		setBackground(Color.white);		
		
		// To fix click and drag issue
		logoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				e.consume();
			}
		});
		
		// Set logo image
		logoPanel.setIcon(new ImageIcon("img/logo.png"));
		logoPanel.setMinimumSize(new Dimension(259, 50));
		logoPanel.setMaximumSize(new Dimension(259, 50));
		logoPanel.setSize(new Dimension(259, 50));
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("img/greenSquare.png"));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addGap(445))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuView, GroupLayout.PREFERRED_SIZE, 718, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(menuView, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(175, Short.MAX_VALUE))
		);
		
		setLayout(groupLayout);

	}
}
