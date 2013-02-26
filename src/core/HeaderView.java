package core;
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

import menu.MenuPanel;
import menu.SubmenuPanel;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Timer;


public class HeaderView extends JPanel {
	private final JLabel logoPanel = new JLabel();
	private SubmenuPanel submenuPanel = new SubmenuPanel();
	private final JPanel menuPanelWrapper = new JPanel();
	private final JPanel submenuPanelWrapper = new JPanel();

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
					.addGap(82)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(menuPanelWrapper, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(submenuPanelWrapper, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(104, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(menuPanelWrapper, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(submenuPanelWrapper, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(120, Short.MAX_VALUE))
		);
		FlowLayout flowLayout = (FlowLayout) menuPanelWrapper.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		menuPanelWrapper.setOpaque(false);
		submenuPanelWrapper.setOpaque(false);
		submenuPanelWrapper.setLayout(new GridLayout(0, 1, 0, 0));
		
		setLayout(groupLayout);

	}
	
	public void setMenuPanel(JPanel menuPanel) {
		menuPanelWrapper.removeAll();
		menuPanelWrapper.add(menuPanel);
		menuPanelWrapper.validate();
	}
	
	public void setSubmenuPanel(JPanel submenuPanel) {
		submenuPanelWrapper.removeAll();
		submenuPanelWrapper.add(submenuPanel);
		submenuPanelWrapper.validate();
		
	}
}
