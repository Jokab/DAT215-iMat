package core;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowListener;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

import checkout.Session;

import SearchBar.AutoCompleteContainer;
import SearchBar.SearchBar;

import java.awt.FlowLayout;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import shoppingCart.ShoppingCartMinimizedView;
import shoppingCart.ShoppingCartSummaryView;
import shoppingCart.ShoppingcartView;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;


public class MainView extends JFrame {

	private JPanel contentPane;
	private final HeaderView headerView = new HeaderView();
	private final ContentView contentView = new ContentView();
	private final JButton exitButton = new JButton();
	private final JButton minimizeButton = new JButton();
	private final JPanel windowbuttonsPanel = new JPanel();
	private final JPanel popupBgPanel;
	private final ShoppingcartView shoppingCartView;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final GridBagConstraints c = new GridBagConstraints();
	private final JPanel popupContent = new JPanel(new GridBagLayout());
	private final SearchBar searchBar = new SearchBar();
	private final AutoCompleteContainer autoCompleteContainer = new AutoCompleteContainer();
	private static final IMatDataHandler dm = IMatDataHandler.getInstance();
	private static final Session session = Session.getInstance();
	
	
	private MainController mainController;
	
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
	public MainView(MainController mainController) {
		this.mainController = mainController;
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1204, 724);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/frameIcon.png"));
		setTitle("MatnyttIT");
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(225, 225, 225)));
		setContentPane(contentPane);
		layeredPane.setBorder(null);
		
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
		
		// Windowbuttons
		windowbuttonsPanel.setBounds(1119, 11, 32, 14);	
		windowbuttonsPanel.setOpaque(false);
		windowbuttonsPanel.setMinimumSize(new Dimension(50, 12));
		windowbuttonsPanel.setLayout(new BorderLayout(7, 0));
		
		minimizeButton.addActionListener(new ActionListener() {
			@Override
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
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shutDown();
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
		contentView.setBounds(64, 200, 1116, 511);
		headerView.setBounds(0, 15, 1180, 184);
		
		shoppingCartView = new ShoppingcartView(mainController);
		shoppingCartView.setBounds(876, 0, 324, 720);
		
		ShoppingCartMinimizedView shoppingCartMinimizedView = new ShoppingCartMinimizedView();
		shoppingCartMinimizedView.setBounds(1075, 0, 125, 720);
		
		ShoppingCartSummaryView shoppingCartSummaryView = new ShoppingCartSummaryView(mainController);
		shoppingCartSummaryView.setBounds(949, 11, 231, 149);
		
		
		// Set detail view of shoppingcart to non-visible.
		shoppingCartView.setVisible(false);
		
		// Add actionlistener for shoppingcart-show event
		ActionListener l = new PopoutListener();
		shoppingCartSummaryView.addPopoutListener(l);
		shoppingCartMinimizedView.addPopoutListener(l);
		shoppingCartView.addPopoutListener(l); 
		
		// Popup: Manipulate program to first paint other components behind JPanel and then the JPanel
		popupBgPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g)
		    {
		        g.setColor( getBackground() );
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		popupBgPanel.setOpaque(false);
		popupBgPanel.setBounds(0, 0, 1200, 720);
		popupBgPanel.setBackground(new Color(0,0,0,60));
		popupBgPanel.setVisible(false);
		popupBgPanel.setLayout(new BorderLayout(0, 0));		
		
		// Handle positioning of window
		headerView.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xPosition = e.getX();
				yPosition = e.getY();
			}
		});
		headerView.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(getLocation().x + (e.getX() - xPosition),
						getLocation().y + (e.getY() - yPosition));
			}
		});
		
		// SearchBar
		searchBar.setBounds(370, 85, 350, 400);
		autoCompleteContainer.setBounds(370, 111, 349, 450);
		
		// Add to layered pane
		layeredPane.add(contentView, new Integer(1));
		layeredPane.add(headerView, new Integer(2));
		layeredPane.add(windowbuttonsPanel, new Integer(7));
		layeredPane.add(shoppingCartSummaryView, new Integer(3));
		layeredPane.add(shoppingCartView, new Integer(4));
		layeredPane.add(popupBgPanel, new Integer(6));
		layeredPane.add(shoppingCartMinimizedView, new Integer(4));
		layeredPane.add(searchBar, new Integer(5));
		layeredPane.add(autoCompleteContainer, new Integer(5));
		
		contentPane.setLayout(gl_contentPane);
		
		setVisible(true);
	}
	
	/**
	 * Saves the state of all things that need to be saved. Is called when the
	 * window closes.
	 */
	protected void shutDown() {
		session.saveSession();
		dm.shutDown();
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
		contentView.repaint();
		contentView.add(panel);
		contentView.revalidate();
	}
	
	/**
	 * Dims the screen and presents the specified JPanel in the middle
	 * @param panel
	 */
	public void setPopup(JPanel panel) {
		popupContent.setOpaque(false);
		popupContent.add(panel, c);
		popupBgPanel.setVisible(true);
		popupBgPanel.add(popupContent, BorderLayout.CENTER);
		popupContent.revalidate();
	}
	
	/**
	 * Removes the popup from the screen
	 */
	public void removePopup() {
		popupBgPanel.setVisible(false);
		popupBgPanel.removeAll();
		
		popupContent.removeAll();
		popupContent.revalidate();
	}
	
	/**
	 * Returns a reference to the search bar.
	 * @return
	 */
	public SearchBar getSearchBar() {
		return searchBar;
	}
	
	/**
	 * Returns a reference to the auto complete result container.
	 * @return
	 */
	public AutoCompleteContainer getAutoCompleteContainer() {
		return autoCompleteContainer;
	}
	
	private class PopoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			shoppingCartView.setVisible(!shoppingCartView.isVisible());
		}
	}
	
	public void addMainViewWindowListener(WindowListener listener) {
		addWindowListener(listener);
	}
	
	public void addBackButtonListener(ActionListener l ){
		headerView.addBackButtonListener(l);
	}
	
	public void setEnableBackButton(boolean value) {
		headerView.setEnableBackButton(value);
	}
}
