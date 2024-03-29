package productView;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.util.Random;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import shoppingCart.ShoppingCartAdapter;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.FlowLayout;


public class ProductView extends JPanel {
	private final ProductAmountPanel productAmountPanel = new ProductAmountPanel();
	private JButton starButton;
	private Product product;
	private final ProductAddToListButton productAddToListBtn = new ProductAddToListButton();
	private final JLabel showMoreLabel = new JLabel("Visa mer");
	private JLabel showMoreImage = new JLabel(downArrow);
	
	private final static ImageIcon downArrow = new ImageIcon("img/downArrow.png");
	private final static ImageIcon upArrow = new ImageIcon("img/upArrow.png");
	private ProductViewDetail pwd;
	
	protected class ShowMorePanel extends JPanel {
		private boolean isToggled = false;
		public void toggle() {
			showMoreLabel.setFont(new Font("Calibri", isToggled?Font.PLAIN:Font.BOLD, 12));
			isToggled ^= true;
		}
		public Product getProduct() {
			return product;
		}
		public ProductView getProductView() {
			return ProductView.this;
		}
	};
	private ShowMorePanel showMorePanel = new ShowMorePanel();
	
	/**
	 * Create the panel.
	 */
	public ProductView(final Product product) {
		this.product = product;
		setOpaque(false); 
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		setMaximumSize(new Dimension(800, 110));
		setPreferredSize(new Dimension(628, 110));
		setSize(new Dimension(800, 110));
		setBorder(new MatteBorder(0, 0, 1, 0, new Color(225, 225, 225)));
		setMinimumSize(new Dimension(800, 110));
		
		JLabel productPic = new JLabel();
		productPic.setBounds(20, 16, 80, 80);
		productPic.setPreferredSize(new Dimension(96, 96));
		productPic.setMaximumSize(new Dimension(96, 96));
		productPic.setMinimumSize(new Dimension(96, 96));
		productPic.setIcon(dataHandler.getImageIcon(product, new Dimension(96,96)));
		
		JLabel productPrice = new JLabel(product.getPrice() + "");
		productPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		productPrice.setHorizontalTextPosition(SwingConstants.RIGHT);
		productPrice.setForeground(new Color(150, 150, 150));
		productPrice.setBounds(260, 40, 69, 23);
		productPrice.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		
		JLabel productTitle = new JLabel(product.getName());
		productTitle.setForeground(new Color(150, 150, 150));
		productTitle.setBounds(136, 29, 176, 23);
		productTitle.setMaximumSize(new Dimension(70, 14));
		productTitle.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		
		/* Create a random number for the weight of the product */
		
		Random rand = new Random();
		int num = rand.nextInt(1000);
		
		JLabel productWeight = new JLabel(num + "g");
		productWeight.setBounds(136, 52, 104, 23);
		productWeight.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		productWeight.setForeground(new Color(195, 195, 195));
		
		JLabel productUnit = new JLabel(product.getUnit());
		productUnit.setHorizontalTextPosition(SwingConstants.RIGHT);
		productUnit.setHorizontalAlignment(SwingConstants.LEFT);
		productUnit.setForeground(new Color(150, 150, 150));
		productUnit.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		productUnit.setBounds(332, 40, 69, 23);
		
		JButton buyButton = new JButton(new ImageIcon("img/buyButton.png"));
		buyButton.setBorder(null);
		buyButton.setBounds(540, 39, 46, 25);
		buyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShoppingCartAdapter.getInstance().addProduct(product, productAmountPanel.getAmount());
			}
		});
		productAddToListBtn.setBorder(UIManager.getBorder("Button.border"));
		productAddToListBtn.setHorizontalAlignment(SwingConstants.CENTER);
		productAddToListBtn.setFont(new Font("Calibri", Font.PLAIN, 12));
		
		productAddToListBtn.setVisible(false);
		ImageIcon starIcon;
		
		starButton = new JButton();
		starButton.setContentAreaFilled(false);
		starButton.setBounds(594, 1, 30, 32);
		starButton.setBorder(null);
		
		if(dataHandler.isFavorite(product)) {
			starIcon = new ImageIcon("img/starFilled.png");
			starButton.setVisible(true);
		} else {
			starIcon = new ImageIcon("img/starUnfilled.png");
			starButton.setVisible(false);
		}
		starButton.setIcon(starIcon);
		
		setLayout(null);
		add(productPic);
		add(productTitle);
		add(productPrice);
		add(productWeight);
		add(starButton);
		productAmountPanel.setBounds(425, 41, 80, 20);
		add(productAmountPanel);
		add(buyButton);
		add(productUnit);
		productAddToListBtn.setOpaque(false);
		productAddToListBtn.setBounds(425, 5, 159, 25);
		
		add(productAddToListBtn);
		
		showMorePanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) showMorePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		showMorePanel.setBounds(131, 73, 118, 23);
		add(showMorePanel);
		showMorePanel.add(showMoreLabel);
		showMoreLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		showMoreLabel.setForeground(new Color(150, 150, 150));
		showMorePanel.add(showMoreImage);
	
	}
	
	public void addViewMouseListener(MouseListener listener) {
		addMouseListener(listener);
	}
	
	public JButton getStarButton() {
		return starButton;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public double getAmount() {
		return this.productAmountPanel.getAmount();
	}

	public void addActionListener(ActionListener starActionListener) {
		starButton.addActionListener(starActionListener);
		
	}

	public ProductAddToListButton getAddToListButton() {
		return this.productAddToListBtn;
	}
	
	public void addShowMoreMouseListener(MouseListener l) {
		showMorePanel.addMouseListener(l);
	}
	
	public void setActive() {
		setMaximumSize(new Dimension(800, 340));
		setPreferredSize(new Dimension(628, 340));
		setSize(new Dimension(800, 340));
		
		pwd = new ProductViewDetail();
		pwd.setBounds(125, 105, 450, 230);
		
		showMoreLabel.setText("Visa mindre");
		showMoreImage.setIcon(upArrow);
		
		add(pwd);
	}
	
	public void setInactive() {
		setMaximumSize(new Dimension(800, 110));
		setPreferredSize(new Dimension(628, 110));
		setSize(new Dimension(800, 110));
		if(pwd != null) {
			remove(pwd);
		}
		showMoreLabel.setText("Visa mer");
		showMoreImage.setIcon(downArrow);
	}
}
