package myAccount;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import core.ViewController;

public class MyAccountController implements ViewController {
	private final MyAccountSidePanel sidePanel;
	private final ViewController controller;
	public MyAccountController(ViewController controller, MyAccountEnum controllerType) {
		sidePanel = new MyAccountSidePanel(controllerType);
		this.controller = controller;
	}

	@Override
	public JPanel getView() {
		JPanel j = new JPanel(new BorderLayout());
		j.add(sidePanel, BorderLayout.WEST);
		j.add(controller.getView(), BorderLayout.CENTER);
		j.setOpaque(false);
		return j;
	}
	
}
