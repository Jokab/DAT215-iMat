package ShoppingList;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;
import shoppingCart.ShoppingCartAdapter;
import shoppingCart.shoppingCartTest;


public class ShoppingListTest {
	
	public static void main(String[] args) {
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
		
		
		
		System.out.println("STARTING READING --------------------- \n \n");
		ShoppingList list1 = new ShoppingList("Vardag");
		ShoppingList list2 = new ShoppingList("Lördag");
		ShoppingList list3 = new ShoppingList("Söndag");
		ShoppingList list4 = new ShoppingList("Fest");
		
		ShoppingListHandler handler = new ShoppingListHandler();
		List<ShoppingList> readList = handler.getShoppingLists();
		
		for(ShoppingList daList : readList) {
			System.out.println(daList.getName());
		}
		handler.deleteFile("Vardag");

		
//		System.out.println("Total price: " + list.getTotal() + "\tExpected: 250");
//		System.out.println("Number of items:" + list.getNbrItems()+ "\tExpected: 2");
//		dm.shutDown();
	}
}
