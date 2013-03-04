package productView;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
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
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;


public class ProductView extends JPanel {
	private int nbrOfProd;
	private final ProductAmountPanel productAmountPanel = new ProductAmountPanel();
	private JButton starButton;
	private Product product;
	private final ProductAddToListButton productAddToListBtn = new ProductAddToListButton();
	
	/**
	 * Create the panel.
	 */
	public ProductView(final Product product) {
		setOpaque(false); 
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		
		setMaximumSize(new Dimension(800, 150));
		setPreferredSize(new Dimension(628, 110));
		setSize(new Dimension(800, 150));
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(225, 225, 225)));
		setMinimumSize(new Dimension(800, 150));
		
		/* the start amount of nbrOfProd...*/
		nbrOfProd = 1;
		
		/* Adding the image */
		
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
			public void actionPerformed(ActionEvent e) {
				ShoppingCartAdapter.getInstance().addProduct(product, productAmountPanel.getAmount());
			}
		});
		
		productAddToListBtn.setVisible(false);
		ImageIcon starIcon;
		
		if(dataHandler.isFavorite(product)) {
			starIcon = new ImageIcon("img/starButton.png");
		} else {
			starIcon = new ImageIcon("img/starButtonunFiled.png");
		}
		
		starButton = new JButton();
		starButton.setContentAreaFilled(false);
		starButton.setBounds(594, 5, 30, 32);
		starButton.setBorder(null);
		starButton.setIcon(starIcon);
		/*default invisible*/
		starButton.setVisible(false);
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
		productAddToListBtn.setBounds(395, 5, 173, 25);
		
		add(productAddToListBtn);
	
		
		/* The hover effect */
		
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
}
