package frontPage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrontPageView extends JPanel {

	/**
	 * Create the panel.
	 */
	public FrontPageView(ActionListener l) {
		setOpaque(false);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		
		FrontPageItemButton frontPageItemButton = new FrontPageItemButton(IMatDataHandler.getInstance().getProduct(80), "img/frontpage/milk.png");
		frontPageItemButton.setBounds(10, 0, 481, 318);
		panel.add(frontPageItemButton);
		frontPageItemButton.addActionListener(l);
		
		FrontPageItemButton frontPageItemButton_1 = new FrontPageItemButton(IMatDataHandler.getInstance().getProduct(134), "img/frontpage/chips.png");
		frontPageItemButton_1.setText("");
		frontPageItemButton_1.setBounds(10, 329, 234, 153);
		frontPageItemButton_1.addActionListener(l);
		panel.add(frontPageItemButton_1);
		
		FrontPageItemButton frontPageItemButton_2 = new FrontPageItemButton(IMatDataHandler.getInstance().getProduct(34), "img/frontpage/cola.png");
		frontPageItemButton_2.setText("");
		frontPageItemButton_2.setBounds(254, 329, 234, 153);
		frontPageItemButton_2.addActionListener(l);
		panel.add(frontPageItemButton_2);
		
		FrontPageItemButton frontPageItemButton_3 = new FrontPageItemButton(IMatDataHandler.getInstance().getProduct(22), "img/frontpage/pears.png");
		frontPageItemButton_3.setText("");
		frontPageItemButton_3.setBounds(501, 0, 234, 153);
		frontPageItemButton_3.addActionListener(l);
		panel.add(frontPageItemButton_3);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1087, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		FrontPageItemButton frontPageItemButton_4 = new FrontPageItemButton(IMatDataHandler.getInstance().getProduct(71), "img/frontpage/pytt.png");
		frontPageItemButton_4.setText("");
		frontPageItemButton_4.setBounds(745, 0, 234, 153);
		frontPageItemButton_4.addActionListener(l);
		panel.add(frontPageItemButton_4);
		
		FrontPageItemButton frontPageItemButton_5 = new FrontPageItemButton(IMatDataHandler.getInstance().getProduct(44), "img/frontpage/kiwi.png");
		frontPageItemButton_5.setText("");
		frontPageItemButton_5.setBounds(745, 164, 234, 153);
		frontPageItemButton_5.addActionListener(l);
		panel.add(frontPageItemButton_5);
		
		FrontPageItemButton frontPageItemButton_6 = new FrontPageItemButton(IMatDataHandler.getInstance().getProduct(22), "img/frontpage/oranges.png");
		frontPageItemButton_6.setText("");
		frontPageItemButton_6.setBounds(745, 329, 234, 153);
		frontPageItemButton_6.addActionListener(l);
		panel.add(frontPageItemButton_6);
		
		FrontPageItemButton frontPageItemButton_7 = new FrontPageItemButton(null, "img/frontpage/paperbag.png");
		frontPageItemButton_7.setText("");
		frontPageItemButton_7.setBounds(501, 164, 234, 317);
		frontPageItemButton_7.addActionListener(l);
		panel.add(frontPageItemButton_7);
		setLayout(groupLayout);

	}
}
