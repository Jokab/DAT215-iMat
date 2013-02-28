package ShoppingList;


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
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingcart.ShoppingCartAdapter;
import shoppingcart.ShoppingCartProductPanel;

public class ShoppingListViewController {
	
	private ShoppingListView view;
	private static final ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private Dimension separatorPanelSize = new Dimension(200000, 5);
	private ShoppingListEntry activeEntryPanel;
	private ShoppingCartAdapter cart = ShoppingCartAdapter.getInstance();
	
	public ShoppingListViewController() {
		view = new ShoppingListView();
		view.addRemoveButtonActionListener(new RemoveButtonListener());
		updateListView();
	}
	
	private void updateListView() {
		
		view.getPanel().removeAll();
		handler.readLists();
		System.out.println("removed all panels");
		view.getPanel().repaint();
		
		IMatDataHandler dm = IMatDataHandler.getInstance();
		
		Product p = dm.getProduct(5);
		Product p1 = dm.getProduct(6);
		
		cart.addProduct(p,5);
		cart.addProduct(p1, 10);
		List<ShoppingItem> listShop = new LinkedList<ShoppingItem>();
		listShop.add(new ShoppingItem(p));
		listShop.add(new ShoppingItem(p1));
		
		ShoppingList list = new ShoppingList(listShop, "Vardag");
		ShoppingList list2 = new ShoppingList(listShop, "Lordag");
		ShoppingList list3 = new ShoppingList(listShop, "Sondag");
		ShoppingList list4 = new ShoppingList(listShop, "Fest");
		
		ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
		
//		handler.readLists(); 
//		handler.addShoppingList(list);
//		handler.addShoppingList(list2);
//		handler.addShoppingList(list3);
//		handler.addShoppingList(list4);
//		handler.writeLists();
		System.out.println("read lists");
		
		Set<ShoppingList> lists = handler.getShoppingLists();

		for(ShoppingList l : lists) {
			ShoppingListEntry entry = new ShoppingListEntry(l);
			entry.addEntryMouseListener(new EntryClickedListener());
			view.getPanel().add(entry);
			
			JPanel separatorPanel = new JPanel();
			separatorPanel.setPreferredSize(separatorPanelSize);
			separatorPanel.setMaximumSize(separatorPanelSize);
			
			view.getPanel().add(separatorPanel);
		}
		System.out.println("added panels");
		
		view.getPanel().revalidate();
		view.getPanel().repaint();
		System.out.println("revalidated");
	}
	
	private class EntryClickedListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent evt) {
			ShoppingListViewController.this.activeEntryPanel = (ShoppingListEntry)evt.getSource();
			checkRemoveButtonEnabled();
			updateDetailedPanel(ShoppingListViewController.this.activeEntryPanel.getShoppingList());
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
			ShoppingList list = ShoppingListViewController.this.activeEntryPanel.getShoppingList();
			handler.removeShoppingList(list.getName());
			handler.writeLists();		// necessary to be able to retrieve lists for repaint

			updateListView();
			updateHeaderText(null);
			view.getRemoveButton().setEnabled(false);
			view.getDetailedPanel().removeAll();
			view.getDetailedPanel().repaint();
		}
		
	}
	
	private class AddToCartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			addListToCart();
		}
		
		private void addListToCart() {
			List<ShoppingItem> list = activeEntryPanel.getShoppingList().getItems();
			for(ShoppingItem item : list) {
				cart.addItem(item);
			}
		}
	}
	
	private void updateDetailedPanel(ShoppingList shoppingList) {
		view.getDetailedPanel().removeAll();
		
		updateHeaderText(shoppingList);
		JPanel detailedPanel = view.getDetailedPanel();
		for(ShoppingItem item : shoppingList.getItems()) {
			detailedPanel.add(new ShoppingCartProductPanel(item));
		}
		
		view.getDetailedPanel().revalidate();
	}
	
	private void checkRemoveButtonEnabled() {
		System.out.println(view.getPanel().getComponentCount());
		if(!(view.getPanel().getComponentCount() <= 1)) {
			view.getRemoveButton().setEnabled(true);
		} else {
			view.getRemoveButton().setEnabled(false);
		}
	}

	private void updateHeaderText(ShoppingList list) {
		if(list == null) {
			view.getHeaderNameLabel().setText("");
		} else {
			view.getHeaderNameLabel().setText(ShoppingListViewController.this.activeEntryPanel.getShoppingList().getName());
		}
	}
	
	private static class Main extends JFrame {
		public Main() {
			ShoppingListViewController controller = new ShoppingListViewController();
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
