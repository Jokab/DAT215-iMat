package ShoppingList;

import java.awt.Dimension;
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

public class ShoppingListViewController {
	
	public ShoppingListView view;
	private static final ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private Dimension separatorPanelSize = new Dimension(200000, 5);
	
	public ShoppingListViewController() {
		view = new ShoppingListView();
		updateListView();
	}
	
	public void updateListView() {
		handler.readLists();
		
		IMatDataHandler dm = IMatDataHandler.getInstance();
		ShoppingCartAdapter cart = new ShoppingCartAdapter();
		
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
		handler.addShoppingList(list);
		handler.addShoppingList(list2);
		handler.addShoppingList(list3);
		handler.addShoppingList(list4);
//		handler.writeLists();
		
		Set<ShoppingList> lists = handler.getShoppingLists();
		System.out.println(lists.size());
		for(ShoppingList l : lists) {
			ShoppingListEntry entry = new ShoppingListEntry(l);
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
			ShoppingListEntry panel = (ShoppingListEntry)evt.getSource();
			view.getHeaderNameLabel().setText(panel.getShoppingList().getName());
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
