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
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.border.MatteBorder;
import java.awt.Component;
import javax.swing.Box;


public class ProductView extends JPanel {
	private static IMatDataHandler dataHandler = IMatDataHandler.getInstance();
	private int nbrOfProd;
	private static JComboBox shoppingListBox;
	private static JLabel starLabel;
	private final Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
	private final ProductAmountPanel productAmountPanel = new ProductAmountPanel();
	private final ProductAmountPanel productAmountPanel_1 = new ProductAmountPanel();
	private final Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
	
	/**
	 * Create the panel.
	 */
	public ProductView(Product product) {
		setMaximumSize(new Dimension(800, 150));
		setPreferredSize(new Dimension(800, 150));
		setSize(new Dimension(800, 150));
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setMinimumSize(new Dimension(800, 150));
		
		/* the start amount of nbrOfProd...*/
		nbrOfProd = 1;
		
		/* Adding the image */
		
		JLabel productPic = new JLabel();
		productPic.setLocation(12, 13);
		productPic.setPreferredSize(new Dimension(96, 96));
		productPic.setMaximumSize(new Dimension(96, 96));
		productPic.setMinimumSize(new Dimension(96, 96));
		productPic.setSize(new Dimension(119, 113));
		productPic.setIcon(dataHandler.getImageIcon(product, new Dimension(96,96)));
		
		JLabel productPrice = new JLabel("Pris: " + product.getPrice() + product.getUnit());
		productPrice.setBounds(143, 53, 134, 23);
		productPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JLabel productTitle = new JLabel(product.getName());
		productTitle.setBounds(141, 7, 135, 34);
		productTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		
		/* Create a random number for the weight of the product */
		
		Random rand = new Random();
		int num = rand.nextInt(1000);
		
		JLabel productUnit = new JLabel("" + num + " gram");
		productUnit.setBounds(288, 16, 104, 23);
		productUnit.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JButton buyButton = new JButton("K\u00F6p");
		buyButton.setLocation(614, 92);
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buyButton.setSize(new Dimension(163, 38));
		buyButton.setBackground(Color.BLACK);
		
		starLabel = new JLabel("*");
		starLabel.setBounds(728, 13, 49, 26);
		starLabel.setVisible(false);
		
		shoppingListBox = new JComboBox();
		shoppingListBox.setBounds(583, 14, 133, 24);
		shoppingListBox.setVisible(false);

		String PicURL = "/.dat215/imat/images/SuperStar.png";
		ImageIcon starIcon = new ImageIcon(PicURL);
		starLabel.setIcon(starIcon);
		setLayout(null);
		add(productPic);
		add(productTitle);
		add(productUnit);
		add(productPrice);
		productAmountPanel.setBounds(776, 73, 1, 1);
		add(productAmountPanel);
		add(shoppingListBox);
		add(starLabel);
		productAmountPanel_1.setBounds(398, 86, 210, 50);
		add(productAmountPanel_1);
		add(buyButton);
		rigidArea_1.setBounds(0, 13, 793, 123);
		
		add(rigidArea_1);
	
		
		/* The hover effect */
		addMouseListener(new java.awt.event.MouseAdapter() {
			
			
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	shoppingListBox.setVisible(true);
		    	starLabel.setVisible(true);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(ProductView.this.getBounds().contains(evt.getPoint())){
		    		return;
		    	}else {
		    		shoppingListBox.setVisible(false);
		    		starLabel.setVisible(false);
		    	}
		    }
		});
	}
	
	public void productIncrease(){
		nbrOfProd++;
	}
	
	public void productDecrease(){
		nbrOfProd -= (nbrOfProd == 0)? 0: 1;
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List<Product> pers = dataHandler.getProducts();
		ProductView pv = new ProductView(pers.get(0));
		jf.getContentPane().add(pv);	
	}	
}
