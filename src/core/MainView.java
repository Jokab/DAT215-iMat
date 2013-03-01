package core;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;

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
import shoppingCart.ShoppingcartView;
import java.awt.FlowLayout;
import shoppingCart.ShoppingCartMinimizedView;
import shoppingCart.ShoppingCartSummaryView;


public class MainView extends JFrame {

	private JPanel contentPane;
	private final HeaderView headerView = new HeaderView();
	private final ContentView contentView = new ContentView();
	private final JButton exitButton = new JButton();
	private final JButton maximizeButton = new JButton();
	private final JButton minimizeButton = new JButton();
	private final JPanel windowbuttonsPanel = new JPanel();
	private final JPanel popupBgPanel;
	private final ShoppingcartView shoppingCartView;
	private final JLayeredPane layeredPane = new JLayeredPane();
	/**
	 * X-position of window
	 */
	
	private int xPosition;
	/**
	 * Y-position of window
	 */
	private int yPosition;

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
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(layeredPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layeredPane.setBorder(null);
		
		// Windowbuttons
		windowbuttonsPanel.setBounds(1099, 11, 52, 14);	
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
		FlowLayout flowLayout = (FlowLayout) contentView.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentView.setOpaque(false);
		
		
		contentView.setBounds(64, 184, 1116, 521);
		headerView.setBounds(0, 0, 1180, 184);
		
		shoppingCartView = new ShoppingcartView();
		shoppingCartView.setBounds(876, 0, 324, 720);
		
		// Manipulate program to first paint other components behind JPanel and then the JPanel
		popupBgPanel = new JPanel() {
			protected void paintComponent(Graphics g)
		    {
		        g.setColor( getBackground() );
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		
		ShoppingCartMinimizedView shoppingCartMinimizedView = new ShoppingCartMinimizedView();
		shoppingCartMinimizedView.setBounds(1075, 0, 125, 720);
		
		ShoppingCartSummaryView shoppingCartSummaryView = new ShoppingCartSummaryView();
		shoppingCartSummaryView.setBounds(949, 11, 231, 149);
		
		popupBgPanel.setOpaque(false);
		popupBgPanel.setBounds(0, 0, 1200, 720);
		popupBgPanel.setBackground(new Color(0,0,0,60));
		popupBgPanel.setVisible(false);
		popupBgPanel.setLayout(new BorderLayout(0, 0));
		
		// Set detail view of shoppingcart to non-visible.
		shoppingCartView.setVisible(false);
		
		// Add actionlistener for shoppingcart-show event
		ActionListener l = new PopoutListener();
		shoppingCartSummaryView.addPopoutListener(l);
		shoppingCartMinimizedView.addPopoutListener(l);
		shoppingCartView.addPopoutListener(l); 
		
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
		
		// Add to layered pane
		layeredPane.add(contentView, new Integer(1));
		layeredPane.add(headerView, new Integer(2));
		layeredPane.add(windowbuttonsPanel, new Integer(7));
		layeredPane.add(shoppingCartSummaryView, new Integer(3));
		layeredPane.add(shoppingCartView, new Integer(4));
		layeredPane.add(popupBgPanel, new Integer(5));
		layeredPane.add(shoppingCartMinimizedView);
		
		contentPane.setLayout(gl_contentPane);
		
		setVisible(true);
	}
	
	/**
	 * Returns a reference to the <code>HeaderView</code>
	 * @return HeaderView
	 */
	public HeaderView getHeaderView() {
		return this.headerView;
	}
	
	/**
	 * Sets the content of the contentView.
	 * @param panel
	 */
	public void setContentView(JPanel panel) {
		contentView.removeAll();
		contentView.add(panel);
		contentView.revalidate();
	}
	
	/**
	 * Dims the screen and presents the specified JPanel in the middle
	 * @param panel
	 */
	public void setPopup(JPanel panel) {
		popupBgPanel.setVisible(true);
		popupBgPanel.add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * Removes the popup from the screen
	 */
	public void removePopup() {
		popupBgPanel.setVisible(false);
		popupBgPanel.removeAll();
	}
	
	private class PopoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			shoppingCartView.setVisible(!shoppingCartView.isVisible());
		}
		
	}
}
