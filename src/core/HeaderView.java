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
	private int i = 0;
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addGap(664))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(submenuPanelWrapper, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
						.addComponent(menuPanelWrapper, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(38))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addComponent(menuPanelWrapper, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(submenuPanelWrapper, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
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
