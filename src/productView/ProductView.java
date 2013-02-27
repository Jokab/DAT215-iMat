package productView;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.util.List;
import java.util.Random;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import java.awt.Component;
import javax.swing.Box;


public class ProductView extends JPanel {
	private int nbrOfProd;
	private static JComboBox shoppingListBox;
	private final ProductAmountPanel productAmountPanel = new ProductAmountPanel();
	private final ProductAmountPanel productAmountPanel_1 = new ProductAmountPanel();
	private JButton starButton;
	private Product product;
	
	/**
	 * Create the panel.
	 */
	public ProductView(Product product) {
		this.product =product;  
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		setMaximumSize(new Dimension(800, 150));
		setPreferredSize(new Dimension(800, 150));
		setSize(new Dimension(800, 150));
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setMinimumSize(new Dimension(800, 150));
		
		/* the start amount of nbrOfProd...*/
		nbrOfProd = 1;
		
		/* Adding the image */
		
		JLabel productPic = new JLabel();
		productPic.setPreferredSize(new Dimension(96, 96));
		productPic.setMaximumSize(new Dimension(96, 96));
		productPic.setMinimumSize(new Dimension(96, 96));
		productPic.setIcon(dataHandler.getImageIcon(product, new Dimension(96,96)));
		
		JLabel productPrice = new JLabel("Pris: " + product.getPrice() + product.getUnit());
		productPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JLabel productTitle = new JLabel(product.getName());
		productTitle.setMaximumSize(new Dimension(70, 14));
		productTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		
		/* Create a random number for the weight of the product */
		
		Random rand = new Random();
		int num = rand.nextInt(1000);
		
		JLabel productUnit = new JLabel("" + num + " gram");
		productUnit.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JButton buyButton = new JButton("K\u00F6p");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buyButton.setBackground(Color.BLACK);
		
		shoppingListBox = new JComboBox();
		shoppingListBox.setVisible(false);

		String PicURL = "/.dat215/imat/images/SuperStarOfylld.png";
		ImageIcon starIcon = new ImageIcon(System.getProperty("user.home") + PicURL);
		
		starButton = new JButton();
		starButton.setBorder(null);
		starButton.setIcon(starIcon);
		/*default invisible*/
		starButton.setVisible(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(productPic, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(productTitle, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
								.addComponent(productPrice, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addComponent(productUnit, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addGap(98)
							.addComponent(shoppingListBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(95)
									.addComponent(productAmountPanel, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(35)
									.addComponent(starButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(productAmountPanel_1, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buyButton, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(productPic, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addComponent(productAmountPanel, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6, 6, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(productTitle, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(productUnit, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(shoppingListBox, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(productPrice, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(starButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(buyButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(productAmountPanel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		setLayout(groupLayout);
	
		
		/* The hover effect */
		
	}
	
	public void addViewMouseListener(MouseListener listener) {
		addMouseListener(listener);
	}
	
	public void productIncrease(){
		nbrOfProd++;
	}
	
	public void productDecrease(){
		nbrOfProd -= (nbrOfProd == 0)? 0: 1;
	}
	
	public JComboBox getShoppingListBox() {
		return shoppingListBox;
	}
	
	public JButton getStarButton() {
		return starButton;
	}
	
	public Product getProduct(){
		return product;
	}

	public void addActionListener(ActionListener starActionListener) {
		starButton.addActionListener(starActionListener);
		
	}
}
