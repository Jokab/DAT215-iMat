package productView;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

public class ProductViewDetail extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProductViewDetail() {
		setLayout(null);
		setOpaque(false);
		JLabel lblBeskrivning = new JLabel("Beskrivning");
		lblBeskrivning.setBounds(10, 11, 58, 16);
		lblBeskrivning.setFont(new Font("Calibri", Font.BOLD, 12));
		lblBeskrivning.setForeground(new Color(150, 150, 150));
		add(lblBeskrivning);
		
		JTextArea txtrAsd = new JTextArea();
		txtrAsd.setLineWrap(true);
		txtrAsd.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrAsd.setOpaque(false);
		txtrAsd.setBorder(null);
		txtrAsd.setEditable(false);
		txtrAsd.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed luctus mattis cursus. Ut nisi est, molestie at vulputate sit amet, pellentesque at dolor. Praesent dignissim turpis condimentum ipsum pellentesque viverra. Mauris rutrum ultrices massa ut lobortis. Aliquam in luctus nisi. ");
		txtrAsd.setBounds(10, 30, 430, 83);
		txtrAsd.setForeground(new Color(150, 150, 150));
		add(txtrAsd);
		
		JLabel label = new JLabel(new ImageIcon("img/gda.png"));
		label.setBounds(10, 110, 180, 84);
		add(label);

	}
}
