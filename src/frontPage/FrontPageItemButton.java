package frontPage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import se.chalmers.ait.dat215.project.Product;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class FrontPageItemButton extends JButton {
	private Product p;
	
	public FrontPageItemButton(Product p, String imageSrc) {
		super(new ImageIcon(imageSrc));
		setContentAreaFilled(false);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(225, 225, 225)));
		this.p = p;
	}
	
	public Product getProduct() {
		return p;
	}
	
	
}
