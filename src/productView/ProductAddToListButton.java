package productView;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ProductAddToListButton extends JButton {
	
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);
	
	/**
	 * Create the panel.
	 */
	public ProductAddToListButton() {
		setBorder(null);
		setBackground(NORMAL_BG_COLOR);
		setForeground(NORMAL_TEXT_COLOR);
		setIcon(new ImageIcon("img/popup_icon.gif"));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		setHorizontalAlignment(SwingConstants.LEFT);
		setText("Lägg till i inköpslista");
		

	}
	
	public ProductView getButtonParent() {
		return (ProductView) this.getParent();
	}
}
