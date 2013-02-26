import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.Color;


public class MenuView extends JPanel {
	private final JButton btnNewButton_1 = new JButton("K\u00F6tt");
	private final JButton btnNewButton_2 = new JButton("Frukt&Gr\u00F6nt");
	private final JButton btnNewButton_3 = new JButton("Fisk");
	private final JButton btnNewButton_4 = new JButton("Dryck");
	private final JButton btnNewButton_5 = new JButton("Mejeri");
	private final JButton btnNewButton_6 = new JButton("Ink\u00F6pslistor");
	private final JButton btnNewButton_7 = new JButton("Mitt konto");
	private final JLabel lblNewLabel = new JLabel("New label");
	private final JButton btnSkafferi = new JButton("Skafferi");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final SearchBar searchBar = new SearchBar();

	/**
	 * Create the panel.
	 */
	public MenuView() {
		setBackground(Color.RED);
		buttonGroup.add(btnNewButton_1);
		buttonGroup.add(btnNewButton_2);
		buttonGroup.add(btnNewButton_3);
		buttonGroup.add(btnNewButton_4);
		buttonGroup.add(btnNewButton_5);
		buttonGroup.add(btnNewButton_6);
		buttonGroup.add(btnNewButton_7);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Jakob\\Desktop\\house.png"));
		buttonGroup.add(btnSkafferi);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(120)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(60)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(214)
							.addComponent(btnSkafferi)))
					.addGap(18)
					.addComponent(searchBar, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(60)
							.addComponent(btnNewButton_5)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(88)
							.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addGap(35))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2)
								.addComponent(btnNewButton_3)
								.addComponent(btnNewButton_1)
								.addComponent(btnSkafferi)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_7)
								.addComponent(btnNewButton_6)
								.addComponent(btnNewButton_4)
								.addComponent(btnNewButton_5)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(searchBar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(260, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
