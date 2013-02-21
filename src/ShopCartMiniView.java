import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;


public class ShopCartMiniView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ShopCartMiniView() {
		setBackground(Color.GREEN);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 267, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 167, Short.MAX_VALUE)
		);
		setLayout(groupLayout);

	}

}
