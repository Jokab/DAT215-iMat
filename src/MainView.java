import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setBackground(Color.WHITE);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 720);
		
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
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		
		windowbuttonsPanel.setOpaque(false);
		windowbuttonsPanel.setMinimumSize(new Dimension(50, 12));
		windowbuttonsPanel.setLayout(new BorderLayout(7, 0));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(headerView, GroupLayout.PREFERRED_SIZE, 1180, Short.MAX_VALUE)
						.addComponent(contentView, GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE))
					.addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(1111, Short.MAX_VALUE)
					.addComponent(windowbuttonsPanel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(windowbuttonsPanel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(headerView, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(contentView, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE))
		);
		
		// Windowbuttons
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
				setState(Frame.MAXIMIZED_BOTH);
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
		panel.setLayout(null);
		exitButton.setIcon(new ImageIcon("img/exitIcon.png"));
		
		
		contentPane.setLayout(gl_contentPane);
	}
}
