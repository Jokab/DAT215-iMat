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
import shoppingCart.ShoppingCartAdapter;
import shoppingcart.ShoppingCartProductPanel;

public class ShoppingListViewController {
	
	public ShoppingListView view;
	private static final ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private Dimension separatorPanelSize = new Dimension(200000, 5);
	private ShoppingListEntry activePanel;
	
	public ShoppingListViewController() {
		view = new ShoppingListView();
		view.addRemoveButtonActionListener(new RemoveButtonListener());
		updateListView();
	}
	
	public void updateListView() {
		
		view.getPanel().removeAll();
		handler.readLists();
		System.out.println("removed all panels");
		view.getPanel().repaint();
		
		IMatDataHandler dm = IMatDataHandler.getInstance();
		ShoppingCartAdapter cart = ShoppingCartAdapter.getInstance();
		
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
		
		handler.readLists(); 
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
			ShoppingListViewController.this.activePanel = (ShoppingListEntry)evt.getSource();
			view.getRemoveButton().setEnabled(true);
			updateDetailedPanel(ShoppingListViewController.this.activePanel.getShoppingList());
		}

		private void updateDetailedPanel(ShoppingList shoppingList) {
			view.getDetailedPanel().removeAll();
			
			view.getHeaderNameLabel().setText(ShoppingListViewController.this.activePanel.getShoppingList().getName());
			JPanel detailedPanel = view.getDetailedPanel();
			for(ShoppingItem item : shoppingList.getItems()) {
				detailedPanel.add(new ShoppingCartProductPanel(item));
			}
			
			view.getDetailedPanel().revalidate();
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
			
			ShoppingList list = ShoppingListViewController.this.activePanel.getShoppingList();
			handler.removeShoppingList(list.getName());
			handler.writeLists();
			handler.getShoppingLists().size();
			updateListView();
			System.out.println("UPDATED (IN BUTTON LISTENER)");
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
