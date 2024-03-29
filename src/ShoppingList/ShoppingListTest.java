package ShoppingList;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingCart.ShoppingCartAdapter;



public class ShoppingListTest {
	
	public static void main(String[] args) {
		IMatDataHandler dm = IMatDataHandler.getInstance();
		ShoppingCartAdapter cart = ShoppingCartAdapter.getInstance();

		Product p = dm.getProduct(5);
		Product p1 = dm.getProduct(6);
		
		cart.addProduct(p,5);
		cart.addProduct(p1, 10);
		List<ShoppingItem> listShop = new LinkedList<ShoppingItem>();
		listShop.add(new ShoppingItem(p));
		listShop.add(new ShoppingItem(p1));
		
		
		
		System.out.println("\n\nSTARTING READING ---------------------");

		ShoppingList list = new ShoppingList(new LinkedList<ShoppingItem>(), "Vardag");
		ShoppingList list2 = new ShoppingList(new LinkedList<ShoppingItem>(), "Lordag");
		ShoppingList list3 = new ShoppingList(new LinkedList<ShoppingItem>(), "Sondag");
		ShoppingList list4 = new ShoppingList(new LinkedList<ShoppingItem>(), "Fest");
		
		ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
//		
//		handler.readLists(); 
//		handler.addShoppingList(list);
//		handler.addShoppingList(list2);
//		handler.addShoppingList(list3);
//		handler.addShoppingList(list4);
//		handler.writeLists();
		
		Set<ShoppingList> readLists = handler.getShoppingLists();
		
		for(ShoppingList daList : readLists) {
			System.out.println(daList.getName());
//			System.out.println(daList.getItems().get(0).getProduct().getName());
		}
		
//		if(handler.removeShoppingList("Vardag")) {
//			System.out.println("Delete successful.");
//		}
//		handler.writeLists();
	}
}
