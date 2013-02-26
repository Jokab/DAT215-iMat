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


public class ProductView extends JPanel implements ActionListener {
	private JTextField nbrOfProducts;
	private static IMatDataHandler dataHandler = IMatDataHandler.getInstance();
	private int nbrOfProd;
	private JLabel lblAntal;
	private JButton addButton = new JButton("+");
	private JButton removeButton = new JButton("-");
	private static JComboBox shoppingListBox;
	private static JLabel starLabel;
	
	/**
	 * Create the panel.
	 */
	public ProductView(Product product) {
		
		/* the start amount of nbrOfProd...*/
		nbrOfProd = 1;
		
		/* Adding the image */
		
		JLabel productPic = new JLabel();
		productPic.setIcon(dataHandler.getImageIcon(product, new Dimension(150, 100)));
		
		JLabel productPrice = new JLabel("Pris: " + product.getPrice() + product.getUnit());
		productPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JLabel productTitle = new JLabel(product.getName());
		productTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		
		/* Create a random number for the weight of the product */
		
		Random rand = new Random();
		int num = rand.nextInt(1000);
		
		JLabel productUnit = new JLabel("" + num + " gram");
		productUnit.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		/**/
		
		lblAntal = new JLabel("Antal: ");
		
		addButton.addActionListener(this);
		addButton.setActionCommand("+");
		addButton.setBackground(new Color(40, 190, 90));
		addButton.setForeground(Color.BLACK);
		
		nbrOfProducts = new JTextField("1");
		nbrOfProducts.setColumns(10);
		
		removeButton.addActionListener(this);
		removeButton.setActionCommand("-");
		removeButton.setBackground(Color.BLACK);
		removeButton.setForeground(Color.RED);
		
		JButton buyButton = new JButton("K\u00F6p");
		buyButton.setBackground(Color.BLACK);
		
		starLabel = new JLabel("*");
		starLabel.setVisible(false);
		
		shoppingListBox = new JComboBox();
		shoppingListBox.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(productPic, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(productTitle)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(productUnit, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(productPrice)
								.addGap(77)))
						.addComponent(lblAntal))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(nbrOfProducts, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buyButton, GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(shoppingListBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(starLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(buyButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(removeButton)
								.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(14))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(productPic, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(productTitle, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addComponent(productUnit, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(productPrice, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(starLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(shoppingListBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nbrOfProducts, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAntal))
					.addGap(16))
		);
		String PicURL = "/.dat215/imat/images/SuperStar.png";
		ImageIcon starIcon = new ImageIcon(PicURL);
		starLabel.setIcon(starIcon);
		setLayout(groupLayout);
	
		
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
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action == "+"){
			productIncrease();
			nbrOfProducts.setText("" + nbrOfProd);
		} else {
			productDecrease();
			nbrOfProducts.setText("" + nbrOfProd);
		}
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
