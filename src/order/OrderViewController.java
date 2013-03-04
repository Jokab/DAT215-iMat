package order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;

import core.ViewController;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingCart.ShoppingCartAdapter;

public class OrderViewController implements ViewController {

	public OrderListView view;
	private static final IMatDataHandler dm = IMatDataHandler.getInstance();
	private static final ShoppingCartAdapter cart = ShoppingCartAdapter
			.getInstance();
	private OrderEntry activeEntryPanel;

	public OrderViewController() {
		view = new OrderListView();
		view.getAddToCartButton().addActionListener(
				new AddToCartButtonListener());
		updateListView();
	}

	public void updateListView() {
		List<Order> orders = dm.getOrders();
		for (Order order : orders) {
			OrderEntry entry = new OrderEntry(order);
			entry.addEntryMouseListener(new EntryClickedListener());
			view.getPanel().add(entry);
		}
		view.getPanel().revalidate();
	}

	private class EntryClickedListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent evt) {
			activeEntryPanel = (OrderEntry) evt.getSource();
			activeEntryPanel.setActive();
			updateDetailedPanel(activeEntryPanel.getOrder());
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			((OrderEntry) e.getSource()).toggle();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((OrderEntry) e.getSource()).toggle();
		}

	}

	private class AddToCartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (activeEntryPanel != null && activeEntryPanel.getOrder() != null
					&& activeEntryPanel.getOrder().getItems().size() >= 0) {
				List<ShoppingItem> list = activeEntryPanel.getOrder().getItems();
				for (ShoppingItem item : list) {
					cart.addItem(item);
				}
			}
		}
	}

	private void updateDetailedPanel(Order order) {
		view.getDetailedPanel().removeAll();

		updateHeaderText(order);
		JPanel detailedPanel = view.getDetailedPanel();
		for (ShoppingItem item : order.getItems()) {
			detailedPanel.add(new OrderProductPanel(item));
		}
		view.showRightPanel();
		view.getDetailedPanel().revalidate();
	}

	private void updateHeaderText(Order order) {
		if (order == null) {
			view.getHeaderNameLabel().setText("");
		} else {
			view.getHeaderNameLabel().setText(activeEntryPanel.getDate());
		}
	}

	@Override
	public JPanel getView() {
		return view;
	}

}
