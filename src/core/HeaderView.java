package core;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import menu.SubmenuPanel;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import menu.MenuButton;
import javax.swing.SwingConstants;


public class HeaderView extends JPanel {
	private final JButton logoButton = new JButton();
	private SubmenuPanel submenuPanel = new SubmenuPanel();
	private final JPanel menuPanelWrapper = new JPanel();
	private final JPanel submenuPanelWrapper = new JPanel();
	private final MenuButton myAccountButton;
	private final MenuButton shoppingListsButton;
	private final JButton backButton; 
	private final ImageIcon backButtonEnabled = new ImageIcon("img/backButton.png");
	private final ImageIcon backButtonDisabled = new ImageIcon("img/backButtonDeactivated.png");
	
	/**
	 * Create the panel.
	 */
	public HeaderView() {
		setOpaque(false);
		setBackground(Color.white);		
		logoButton.setContentAreaFilled(false);
		logoButton.setBorder(null);
		logoButton.setHorizontalAlignment(SwingConstants.LEFT);

		// Set logo image
		logoButton.setIcon(new ImageIcon("img/logo.png"));
		logoButton.setMinimumSize(new Dimension(259, 50));
		logoButton.setMaximumSize(new Dimension(259, 50));
		logoButton.setSize(new Dimension(259, 50));
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("img/greenSquare.png"));
		
		myAccountButton = new MenuButton((String) null);
		myAccountButton.setText("mitt konto");
		myAccountButton.addMouseListener(new HoverLinkListener());
		
		shoppingListsButton = new MenuButton((String) null);
		shoppingListsButton.setText("ink\u00F6pslistor");
		shoppingListsButton.addMouseListener(new HoverLinkListener());
		
		backButton = new JButton(backButtonDisabled);
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setContentAreaFilled(false);
		backButton.setBorder(null);
		backButton.setToolTipText("�terv�nd till f�reg�ende sida");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addComponent(submenuPanelWrapper, GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(logoButton, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
								.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addGap(75))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(menuPanelWrapper, GroupLayout.PREFERRED_SIZE, 581, GroupLayout.PREFERRED_SIZE)
							.addGap(87)
							.addComponent(myAccountButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(shoppingListsButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(17, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(logoButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(menuPanelWrapper, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(shoppingListsButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(myAccountButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)))
					.addComponent(submenuPanelWrapper, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(120))
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
		if(submenuPanel != null) {
			submenuPanelWrapper.add(submenuPanel);
			submenuPanelWrapper.setVisible(true);
		} else {
			submenuPanelWrapper.setVisible(false);
		}
		submenuPanelWrapper.validate();
		
	}
	
	public void addActionListenerMyAccountButton(ActionListener l) {
		myAccountButton.addActionListener(l);
	}
	
	public void addActionListenerShoppingListsButton(ActionListener l) {
		shoppingListsButton.addActionListener(l);
	}
	
	public void addBackButtonListener(ActionListener l) {
		backButton.addActionListener(l);
	}
	
	public void addHomeButtonListener(ActionListener l) {
		logoButton.addActionListener(l);
	}
	
	public void setEnableBackButton(boolean value) {
		backButton.setIcon(value?backButtonEnabled:backButtonDisabled);
	}
	
	private class HoverLinkListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			((MenuButton) e.getSource()).toggle();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((MenuButton) e.getSource()).toggle();
		}
	}
}
