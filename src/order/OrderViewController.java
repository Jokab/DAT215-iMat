package order;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import ShoppingList.ShoppingListEntry;

import core.ViewController;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingCart.ShoppingCartAdapter;
import shoppingCart.ShoppingCartProductPanel;

public class OrderViewController implements ViewController {
	
	public OrderListView view;
	private static final IMatDataHandler dm = IMatDataHandler.getInstance();
	private OrderEntry activeEntryPanel;
	
	public OrderViewController() {
		view = new OrderListView();
		updateListView();
	}
	
	public void updateListView() {
		ShoppingCartAdapter cart = ShoppingCartAdapter.getInstance();
		
		List<Order> orders = dm.getOrders();
		for(Order order : orders) {
			OrderEntry entry = new OrderEntry(order);
			entry.addEntryMouseListener(new EntryClickedListener());
			view.getPanel().add(entry);
		}
		view.getPanel().revalidate();
	}
	
	private class EntryClickedListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent evt) {
			activeEntryPanel = (OrderEntry)evt.getSource();
			activeEntryPanel.setActive();
			updateDetailedPanel(activeEntryPanel.getOrder());
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			((OrderEntry)e.getSource()).toggle();
		}
		@Override
		public void mouseExited(MouseEvent e) {
			((OrderEntry)e.getSource()).toggle();
		}
		
	}
	
	private class RemoveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
//			Order order = dm.this.activeEntryPanel.getOrder();
//			handler.removeShoppingList(list.getName());
//			dm.remove
//			handler.writeLists();		// necessary to be able to retrieve lists for repaint
//
//			updateListView();
//			updateHeaderText(null);
//			checkRemoveButtonEnabled();
//			view.getDetailedPanel().removeAll();
//			view.getDetailedPanel().repaint();
		}
		
	}
	
	private void updateDetailedPanel(Order order) {
		view.getDetailedPanel().removeAll();
		
		updateHeaderText(order);
		JPanel detailedPanel = view.getDetailedPanel();
		for(ShoppingItem item  : order.getItems()) {
			detailedPanel.add(new OrderProductPanel(item));
		}
		view.showRightPanel();
		view.getDetailedPanel().revalidate();
	}
	
	public void checkRemoveButtonEnabled() {
		System.out.println(view.getPanel().getComponentCount());
		if(!(view.getPanel().getComponentCount() <= 1)) {
			view.getRemoveButton().setEnabled(true);
		} else {
			view.getRemoveButton().setEnabled(false);
		}
	}

	private void updateHeaderText(Order order) {
		if(order == null) {
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
