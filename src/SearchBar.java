import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import javax.swing.border.LineBorder;


public class SearchBar extends JPanel {
	private final JTextField searchField = new JTextField();
	private final JPanel panel = new JPanel();
	private final JLabel name1 = new JLabel("New label");
	private final JLabel pic1 = new JLabel("New label");
	private final JLabel price1 = new JLabel("New label");
	private final JSeparator separator = new JSeparator();
	private final JLabel pic2 = new JLabel("New label");
	private final JLabel name2 = new JLabel("New label");
	private final JLabel price2 = new JLabel("New label");
	private final JLabel price3 = new JLabel("New label");
	private final JSeparator separator_1 = new JSeparator();
	private final JLabel name3 = new JLabel("New label");
	private final JLabel pic3 = new JLabel("New label");
	
	private final JLabel[][] labelArr = new JLabel[][] {{
			pic1,name1,price1},
			{pic2,name2,price2},
			{pic3,name3,price3}
	};
	
	
	

	/**
	 * Create the panel.
	 */
	public SearchBar() {
		SearchController controller = new SearchController(this);
		setLayout(null);
		searchField.setText("S\u00F6k produkt...");
		searchField.setBounds(208, 11, 253, 20);
		
		add(searchField);
		searchField.setColumns(10);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(208, 30, 253, 153);
		
		add(panel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 247, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(pic3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(name3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(39))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(pic2)
									.addGap(10)
									.addComponent(name2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(pic1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(name1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(price3)
								.addComponent(price2)
								.addComponent(price1))
							.addGap(16)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(price1)
							.addGap(15)
							.addComponent(price2)
							.addGap(15)
							.addComponent(price3))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(pic1)
								.addComponent(name1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(pic2)
								.addComponent(name2))
							.addGap(7)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(pic3)
								.addComponent(name3))))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		panel.setVisible(false);
		

	}


	public void addBarActionPerformedListener(ActionListener listener) {
		searchField.addActionListener(listener);
	}

	public void addFocusGainedListener(FocusListener listener) {
		searchField.addFocusListener(listener);
	}
	
	public JTextField getSearchField() {
		return this.searchField;
	}


	public JPanel getPanel() {
		return panel;
	}

	public JLabel getName1() {
		return name1;
	}


	public JLabel getPic1() {
		return pic1;
	}


	public JLabel getPrice1() {
		return price1;
	}


	public JLabel getPic2() {
		return pic2;
	}


	public JLabel getName2() {
		return name2;
	}


	public JLabel getPrice2() {
		return price2;
	}


	public JLabel getPrice3() {
		return price3;
	}


	public JLabel getName3() {
		return name3;
	}


	public JLabel getPic3() {
		return pic3;
	}


	public JLabel[][] getLabelArr() {
		return labelArr;
	}

}
