package myAccount;

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
		JPanel j = new JPanel();
		j.setLayout(new BoxLayout(j, BoxLayout.X_AXIS));
		j.add(sidePanel);
		j.add(controller.getView());
		j.setOpaque(false);
		return j;
	}
	
}
