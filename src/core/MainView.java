package core;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;


public class MainView extends JFrame {

	private JPanel contentPane;
	private final HeaderView headerView = new HeaderView();
	private final ContentView contentView = new ContentView();
	private final JButton exitButton = new JButton();
	private final JButton maximizeButton = new JButton();
	private final JButton minimizeButton = new JButton();
	private final JPanel windowbuttonsPanel = new JPanel();
	/**
	 * X-position of window
	 */
	
	private int xPosition;
	/**
	 * Y-position of window
	 */
	private int yPosition;
	private final JLayeredPane layeredPane = new JLayeredPane();

	/**
	 * Create the frame.
	 */
	public MainView() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1200, 720);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(layeredPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		// Windowbuttons
		windowbuttonsPanel.setBounds(this.getWidth() - 80, 15, 52, 14);	
		windowbuttonsPanel.setOpaque(false);
		windowbuttonsPanel.setMinimumSize(new Dimension(50, 12));
		windowbuttonsPanel.setLayout(new BorderLayout(7, 0));
		
		minimizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		minimizeButton.setToolTipText("Minimera");
		minimizeButton.setSize(new Dimension(12, 12));
		minimizeButton.setContentAreaFilled(false);
		minimizeButton.setBorder(null);
		minimizeButton.setIcon(new ImageIcon("img/minimizeIcon.png"));
		windowbuttonsPanel.add(minimizeButton, BorderLayout.WEST);
		
		maximizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(Frame.MAXIMIZED_BOTH);
			}
		});
		maximizeButton.setToolTipText("Maximera");
		maximizeButton.setSize(new Dimension(12, 12));
		maximizeButton.setContentAreaFilled(false);
		maximizeButton.setBorder(null);
		maximizeButton.setIcon(new ImageIcon("img/maximizeIcon.png"));
		windowbuttonsPanel.add(maximizeButton, BorderLayout.CENTER);
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		exitButton.setToolTipText("St\u00E4ng");
		exitButton.setSize(new Dimension(12, 12));
		exitButton.setContentAreaFilled(false);
		exitButton.setBorder(null);
		windowbuttonsPanel.add(exitButton, BorderLayout.EAST);
		exitButton.setIcon(new ImageIcon("img/exitIcon.png"));
		
		
		contentView.setBounds(0, 184, 1180, 459);
		headerView.setBounds(0, 0, 1180, 184);
		
		layeredPane.add(contentView, new Integer(1));
		layeredPane.add(headerView, new Integer(2));
		layeredPane.add(windowbuttonsPanel, new Integer(5));
		
		// Handle positioning of window
		headerView.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xPosition = e.getX();
				yPosition = e.getY();
			}
		});
		headerView.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				setLocation(getLocation().x + (e.getX() - xPosition),
						getLocation().y + (e.getY() - yPosition));
			}
		});
		
		
		contentPane.setLayout(gl_contentPane);
		
		setVisible(true);
	}
	
	public HeaderView getHeaderView() {
		return this.headerView;
	}
}
