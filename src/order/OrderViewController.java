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

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingcart.ShoppingCartAdapter;
import shoppingcart.ShoppingCartProductPanel;

public class OrderViewController {
	
	public OrderListView view;
	private static final IMatDataHandler dm = IMatDataHandler.getInstance();
	private Dimension separatorPanelSize = new Dimension(200000, 5);
	private OrderEntry activeEntryPanel;
	
	public OrderViewController() {
		view = new OrderListView();
		updateListView();
	}
	
	public void updateListView() {
		ShoppingCartAdapter cart = ShoppingCartAdapter.getInstance();
		
		Product p = dm.getProduct(5);
		Product p1 = dm.getProduct(6);
		
		cart.addProduct(p,5);
		cart.addProduct(p1, 10);
		
		for(int i = 0; i<20; i++)
			dm.placeOrder();
		
		
		List<Order> orders = dm.getOrders();
		
		
		for(Order order : orders) {
			OrderEntry entry = new OrderEntry(order);
			entry.addEntryMouseListener(new EntryClickedListener());
			view.getPanel().add(entry);
			
			JPanel separatorPanel = new JPanel();
			separatorPanel.setPreferredSize(separatorPanelSize);
			separatorPanel.setMaximumSize(separatorPanelSize);
			
			view.getPanel().add(separatorPanel);
		}
		
		view.getPanel().revalidate();
		view.getPanel().repaint();
	}
	
	private class EntryClickedListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent evt) {
			activeEntryPanel = (OrderEntry)evt.getSource();
			checkRemoveButtonEnabled();
			updateDetailedPanel(activeEntryPanel.getOrder());
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {	}
		
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
			detailedPanel.add(new ShoppingCartProductPanel(item));
		}
		
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
	
	private static class Main extends JFrame {
		public Main() {
			OrderViewController controller = new OrderViewController();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setPreferredSize(new Dimension(1190,700));
			
			
			add(controller.view);
			pack();
		}
			
		
		public static void main(String[] args) {
			new Main();
		}
	}
	
}
