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
	private final JButton exitButton = new JButton();
	private final JButton maximizeButton = new JButton();
	private final JPanel windowButtonsPanel = new JPanel();
	private final JButton minimizeButton = new JButton();
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 1099, Short.MAX_VALUE)
							.addComponent(windowButtonsPanel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(22))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
									.addGap(435))
								.addComponent(menuView, GroupLayout.PREFERRED_SIZE, 718, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(windowButtonsPanel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(menuView, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(141, Short.MAX_VALUE))
		);
		
		// Set options for window buttons
		windowButtonsPanel.setLayout(new BorderLayout(7, 0));
		windowButtonsPanel.setMinimumSize(new Dimension(50,12));
		windowButtonsPanel.setOpaque(false);
		
		minimizeButton.setToolTipText("Minimera");
		minimizeButton.setSize(new Dimension(12, 12));
		minimizeButton.setContentAreaFilled(false);
		minimizeButton.setBorder(null);
		minimizeButton.setIcon(new ImageIcon("img/minimizeIcon.png"));
		windowButtonsPanel.add(minimizeButton, BorderLayout.WEST);
		
		maximizeButton.setSize(new Dimension(12, 12));
		maximizeButton.setContentAreaFilled(false);
		maximizeButton.setToolTipText("Maximera");
		maximizeButton.setBorder(null);
		maximizeButton.setIcon(new ImageIcon("img/maximizeIcon.png"));
		windowButtonsPanel.add(maximizeButton, BorderLayout.CENTER);
		
		exitButton.setSize(new Dimension(12, 12));
		exitButton.setContentAreaFilled(false);
		exitButton.setBorder(null);
		exitButton.setToolTipText("St\u00E4ng");
		exitButton.setIcon(new ImageIcon("img/exitIcon.png"));
		windowButtonsPanel.add(exitButton, BorderLayout.EAST);
		
		setLayout(groupLayout);

	}
}
