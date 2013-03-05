package myAccount;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import components.SidePanelButton;

import menu.MenuButton;
import core.MainController;
import core.ViewController;

public class MyAccountController implements ViewController {
	private final MyAccountSidePanel sidePanel;
	private final ViewController controller;
	private final MainController mainController;
	public MyAccountController(ViewController controller, MyAccountEnum controllerType, MainController mainController) {
		sidePanel = new MyAccountSidePanel(controllerType);
		this.controller = controller;
		this.mainController = mainController;
		
		SidePanelListener l = new SidePanelListener();
		sidePanel.getOrderHistory().addMouseListener(l);
		sidePanel.getSettings().addMouseListener(l);
		sidePanel.getShoppingLists().addMouseListener(l);
	}

	@Override
	public JPanel getView() {
		JPanel j = new JPanel(new BorderLayout());
		j.add(sidePanel, BorderLayout.WEST);
		j.add(controller.getView(), BorderLayout.CENTER);
		j.setOpaque(false);
		return j;
	}
	
	private class SidePanelListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == sidePanel.getOrderHistory()) {
				mainController.initOrderHistoryController();
			} else if(e.getSource() == sidePanel.getShoppingLists()) {
				mainController.initShoppingListController();
			} else if(e.getSource() == sidePanel.getSettings()) {
				mainController.initMyInfoController();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			((SidePanelButton) e.getSource()).toggle();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((SidePanelButton) e.getSource()).toggle();
		}
	}
}
